package io.codiga.analyzer.ast.languages.python;

import io.codiga.analyzer.ast.vm.ExecutionEnvironment;
import io.codiga.analyzer.ast.vm.ExecutionEnvironmentBuilder;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.EntityChecked;
import io.codiga.model.RuleType;
import io.codiga.model.ast.FunctionCall;
import io.codiga.model.ast.FunctionDefinition;
import io.codiga.model.error.Violation;
import io.codiga.parser.python.gen.PythonParser;
import io.codiga.parser.python.gen.PythonParserBaseVisitor;
import org.graalvm.polyglot.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.ExprToFunctionCall.transformExprToFunctionCall;
import static io.codiga.analyzer.ast.languages.python.FuncDefToFunctionDefinition.transformFuncDefToFunctionDefinition;
import static io.codiga.analyzer.ast.languages.python.PythonAstUtils.isFunctionCall;
import static io.codiga.analyzer.ast.vm.VmUtils.buildExecutableCode;
import static io.codiga.analyzer.ast.vm.VmUtils.createContextForJavaScriptExecution;

public class CodigaVisitor extends PythonParserBaseVisitor<List<Violation>> {

    List<Violation> violations;
    private AnalyzerRule analyzerRule;
    private PythonParser.RootContext root;
    private StringBuffer output;
    private boolean logOutput;
    private String code;
    private Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);

    public CodigaVisitor(AnalyzerRule rule, String code, boolean logOutput) {
        this.analyzerRule = rule;
        this.root = null;
        this.logOutput = logOutput;
        this.code = code;
        this.violations = new ArrayList<>();
        this.output = new StringBuffer();
    }

    public String getOutput() {
        String finalString = this.output.toString();
        if (finalString.isEmpty()) {
            return null;
        }
        return finalString;
    }

    public List<Violation> getViolations() {
        return this.violations;
    }

    @Override
    public List<Violation> visitRoot(PythonParser.RootContext ctx) {
        this.root = ctx;
        return visitChildren(ctx);
    }


    @Override
    public List<Violation> visitFuncdef(PythonParser.FuncdefContext ctx) {
        if (analyzerRule.ruleType() == RuleType.AST_CHECK && analyzerRule.entityChecked() == EntityChecked.FUNCTION_DEFINITION) {
            Optional<FunctionDefinition> functionDefinitionOptional = transformFuncDefToFunctionDefinition(ctx, this.root);
            if (functionDefinitionOptional.isPresent()) {
                ExecutionEnvironment executionEnvironment = new ExecutionEnvironmentBuilder()
                    .setCode(code)
                    .setRootObject(functionDefinitionOptional.get())
                    .setLogOutput(logOutput)
                    .createExecutionEnvironment();

                Context context = createContextForJavaScriptExecution(executionEnvironment);
                String finalCode = buildExecutableCode(this.analyzerRule.code());
                context.eval("js", finalCode);
                violations.addAll(executionEnvironment.errorReporting.getErrors());
                String executionOutput = executionEnvironment.getOutput();
                if (executionOutput != null) {
                    this.output.append(executionOutput);
                }
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public List<Violation> visitExpr(PythonParser.ExprContext ctx) {
        if (analyzerRule.ruleType() == RuleType.AST_CHECK && analyzerRule.entityChecked() == EntityChecked.FUNCTION_CALL && isFunctionCall(ctx)) {
            Optional<FunctionCall> functionCallOptional = transformExprToFunctionCall(ctx, this.root);
            if (functionCallOptional.isPresent()) {
                ExecutionEnvironment executionEnvironment = new ExecutionEnvironmentBuilder()
                    .setCode(code)
                    .setRootObject(functionCallOptional.get())
                    .setLogOutput(logOutput)
                    .createExecutionEnvironment();

                Context context = createContextForJavaScriptExecution(executionEnvironment);
                String finalCode = buildExecutableCode(this.analyzerRule.code());
                context.eval("js", finalCode);
                violations.addAll(executionEnvironment.errorReporting.getErrors());
                String executionOutput = executionEnvironment.getOutput();
                if (executionOutput != null) {
                    this.output.append(executionOutput);
                }
            }
        }

        return visitChildren(ctx);
    }

}
