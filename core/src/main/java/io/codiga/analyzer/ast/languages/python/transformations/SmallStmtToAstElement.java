package io.codiga.analyzer.ast.languages.python.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.transformations.ExprStmtTransformation.transformExprStmtToAstElement;

public class SmallStmtToAstElement {

    private static final Logger logger = LoggerFactory.getLogger(SmallStmtToAstElement.class);


    public static Optional<AstElement> transformSmallStatementToAstElement(PythonParser.Small_stmtContext ctx, PythonParser.RootContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx instanceof PythonParser.Expr_stmtContext) {
            return transformExprStmtToAstElement((PythonParser.Expr_stmtContext) ctx, root);
        }
        return Optional.empty();
    }
}
