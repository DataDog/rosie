package io.codiga.model.ast.python;

import io.codiga.model.ast.AstElement;
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
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.ImportFromToFromStatement.transformFromStmtToFromStatement;
import static io.codiga.analyzer.ast.languages.python.ImportStmtToImportStatement.transformImportStmtToImportStatement;


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

    public AstElement[] getImports() {
        AstElement[] returnedElements;
        List<AstElement> returnedElementsAsList = new ArrayList<>();

        List<ParseTree> importsStatement = getNodes(PythonParser.Import_stmtContext.class);

        for (ParseTree element : importsStatement) {
            PythonParser.Import_stmtContext importStatement = (PythonParser.Import_stmtContext) element;
            Optional<ImportStatement> importStatementOptional = transformImportStmtToImportStatement(importStatement, null);
            importStatementOptional.ifPresent(returnedElementsAsList::add);
        }

        List<ParseTree> fromStatements = getNodes(PythonParser.From_stmtContext.class);

        for (ParseTree element : fromStatements) {
            PythonParser.From_stmtContext fromStatement = (PythonParser.From_stmtContext) element;
            Optional<FromStatement> fromStatementOptional = transformFromStmtToFromStatement(fromStatement, null);
            fromStatementOptional.ifPresent(returnedElementsAsList::add);
        }

        returnedElements = new AstElement[returnedElementsAsList.size()];
        returnedElements = returnedElementsAsList.toArray(returnedElements);
        return returnedElements;
    }

}
