package io.codiga.analyzer.ast.vm;

import datadog.trace.api.Trace;
import io.codiga.analyzer.ast.common.AnalyzerContext;
import io.codiga.analyzer.ast.common.ErrorReporting;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.Language;
import io.codiga.model.error.Violation;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.ResourceLimits;
import org.graalvm.polyglot.Source;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VmContext {
    private static final Logger logger = LoggerFactory.getLogger(VmContext.class);
    private final static int MAX_OUTPUT_SIZE = 1024 * 1024;
    private static final HostAccess SANDBOX = HostAccess.newBuilder()
        .allowPublicAccess(true)
        .allowArrayAccess(true)
        .allowListAccess(true)
        .allowAllImplementations(true)
        .denyAccess(Class.class)
        .denyAccess(Method.class)
        .denyAccess(Field.class)
        .denyAccess(Proxy.class)
        .denyAccess(Object.class, false)
        .build();
    private static final int MAX_STATEMENTS = 1000000;
    private final ErrorReporting errorReporting;

  private final String[] helperFunctions =
      new String[] {
        "function printObject(object) {\n"
            + "  for (const property in object) {\n"
            + "    console.log(`${property}: ${JSON.stringify(object[property])}`);\n"
            + "  }\n"
            + "}\n",
          """
            function getCode(start, end, code) {
              const lines = code.split("\\n");
              const startLine = start.line - 1;
              const startCol = start.col - 1;
              const endLine = end.line - 1;
              const endCol = end.col - 1;
              
              var startChar = 0;
              for (var i = 0 ; i < startLine ; i++) {
                startChar = startChar + lines[i].length + 1;
              }
              startChar = startChar + startCol;
              
              var endChar = 0;
              for (var i = 0 ; i < startLine ; i++) {
                endChar = endChar + lines[i].length + 1;
              }
              endChar = endChar + endCol;
              
              return code.substring(startChar, endChar);
            };"""
      };

    private final String[] PYTHON_HELPER_FUNCTIONS = new String[]{
        """
  function useModule(imports, moduleName) {
    return imports && imports.filter(i => i.astType === "importstatement")
      .flatMap(i => i.packages)
      .filter(p => p.name && p.name.value === moduleName).length > 0;
  }
    """
        ,
        """
  function useImportFrom(imports, package, element) {
    return imports
		  .filter(i => i.astType === "fromstatement")
			.filter(r => r.pkg && r.pkg.value === package && r.elements && r.elements.filter(e => e.name).map(e => e.name.value).includes(element))
			.length > 0;
	}
""",
        """
  function findVariableFromFunctionCall(assignments, module, functionName) {
    const filtered = assignments
			.filter(a => a.right.astType === "functioncall" && a.right.moduleOrObject.value === module && a.right.functionName.value === functionName);
    if (filtered.length > 0) {
      return filtered[0];
    }
    return null;
  }
""",
        """
	function hasDecorator(node, decoratorName) {
		if (!node) {
			return false;
		}
		if(node.astType !== "functiondefinition") {
			return false;
		}
		return node.decorators && node.decorators.filter(d => d.name && d.name.value === decoratorName).length > 0;
	}
"""
    };

    private final String initializationCode =
        "reportError = addError.addError; " +
            "buildFix = addError.buildFix; " +
            "buildError = addError.buildViolation; " +
            "buildEdit = addError.buildEdit; " +
            "buildEditAdd = addError.buildEditAdd; " +
            "buildEditUpdate = addError.buildEditUpdate; " +
            "buildEditRemove = addError.buildEditRemove; " +
            "addError = addError.addViolation; " +
            String.join(";", helperFunctions) + ";";
    private final AnalyzerContext analyzerContext;
    Source executeSource;
    private OutputStream outputStream;
    private InputStream inputStream;
    private Context context = null;

    public VmContext(AnalyzerContext analyzerContext) {
        this.errorReporting = new ErrorReporting();
        this.analyzerContext = analyzerContext;

        if (analyzerContext.isLogOutput()) {
            PipedOutputStream pipedOutputStream = new PipedOutputStream();
            this.outputStream = pipedOutputStream;
            try {
                this.inputStream = new PipedInputStream(pipedOutputStream, MAX_OUTPUT_SIZE);
            } catch (IOException ioException) {
                logger.error("Cannot create streams", ioException);
                this.outputStream = null;
                this.inputStream = null;
            }
        } else {
            this.outputStream = null;
            this.inputStream = null;
        }

        Context.Builder contextBuilder = Context
            .newBuilder("js")
            .allowHostAccess(SANDBOX)
            .allowExperimentalOptions(true)
            .allowValueSharing(true)
            .allowIO(false)
            .resourceLimits(ResourceLimits.newBuilder().statementLimit(MAX_STATEMENTS, null).build())
            .logHandler(OutputStream.nullOutputStream());

        if (outputStream != null) {
            contextBuilder.out(outputStream);
        }
        context = contextBuilder.build();

        // the main source used for each invocation
        String toExecute = "root.forEach(r => {visit(r, filename, code)});";
        executeSource = Source.create("js", toExecute);
    }

    public String[] getLanguageInitializationCode(Language language) {
        if (language == Language.PYTHON) {
            return PYTHON_HELPER_FUNCTIONS;
        }
        return new String[]{};
    }

    private final String getInitializationCode() {
        return String.join(";", new String[]{
            String.join(";", getLanguageInitializationCode(this.analyzerContext.getLanguage())),
            initializationCode
        });
    }

    public String getOutput() {
        if (this.outputStream == null || this.inputStream == null) {
            return null;
        }
        try {
            int nBytesAvailable = this.inputStream.available();
            byte[] buffer = new byte[nBytesAvailable];
            this.inputStream.read(buffer);
            this.outputStream.close();
            this.inputStream.close();
            return new String(buffer);

        } catch (IOException ioException) {
            logger.error("impossible to read the data");
            return null;
        }
    }

    /**
     * Initialize the rule code once
     *
     * @param analyzerRule
     */
    public void initializeRule(AnalyzerRule analyzerRule) {

        context.eval("js", analyzerRule.code());
    }

    @Trace(operationName = "VmContext.prepareForExecution")
    public void prepareForExecution(AnalyzerContext analyzerContext, Object rootObject) {
        context.resetLimits();
        context.getBindings("js").putMember("root", rootObject);
        context.getBindings("js").putMember("addError", this.errorReporting);
        context.getBindings("js").putMember("code", analyzerContext.getCode());

        context.getBindings("js").putMember("filename", analyzerContext.getFilename());

        Source source = Source.create("js", getInitializationCode());
        context.eval(source);
    }

    @Trace(operationName = "VmContext.execute")
    public void execute(AnalyzerRule analyzerRule) {
        context.eval(executeSource);
    }

    public List<Violation> getViolations() {
        return this.errorReporting.getErrors();
    }

    public void shutdown() {
        context.close();
        try {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException ioException) {
            logger.error("cannot close outputstream");
        }
    }
}
