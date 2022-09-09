package io.codiga.model.ast.python;

import io.codiga.model.ast.AstString;
import io.codiga.model.ast.FunctionCall;
import io.codiga.model.ast.FunctionCallArguments;
import io.codiga.model.common.Position;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class PythonFunctionCall extends FunctionCall {

    private static final Logger logger = LoggerFactory.getLogger(PythonFunctionCall.class);

    public PythonFunctionCall(AstString moduleOrObject,
                              AstString functionName,
                              FunctionCallArguments arguments,
                              Position start,
                              Position end,
                              ParserRuleContext parserRuleContext,
                              ParserRuleContext root) {
        super(moduleOrObject, functionName, arguments, parserRuleContext, root);
    }

    public ImportStatement[] getImports() {
        ImportStatement[] importsArray;
        List<ImportStatement> imports = new ArrayList<>();
        List<ParseTree> importsStatement = getNodes(PythonParser.Import_stmtContext.class);

        for (ParseTree element : importsStatement) {
            PythonParser.Import_stmtContext importStatement = (PythonParser.Import_stmtContext) element;
            String packageName = importStatement.dotted_as_names().getText();
            imports.add(new ImportStatement(packageName));
        }
        importsArray = new ImportStatement[imports.size()];
        importsArray = imports.toArray(importsArray);
        return importsArray;
    }

}
