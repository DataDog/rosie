package io.codiga.analyzer.ast.languages.python.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Return;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.transformations.ExprStmtTransformation.transformExprStmtToAstElement;
import static io.codiga.analyzer.ast.languages.python.transformations.PythonTestListTransformation.transformTestlistToAstElement;
import static io.codiga.analyzer.ast.languages.utils.Conversions.flattenAstElement;

public class SmallStmtToAstElement {

    private static final Logger logger = LoggerFactory.getLogger(SmallStmtToAstElement.class);


    public static Optional<? extends AstElement> transformSmallStatementToAstElement(PythonParser.Small_stmtContext ctx, PythonParser.RootContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx instanceof PythonParser.Expr_stmtContext expr_stmtContext) {
            return transformExprStmtToAstElement(expr_stmtContext, root);
        }

        if (ctx instanceof PythonParser.Return_stmtContext return_stmtContext) {
            return Optional.of(new Return(flattenAstElement(transformTestlistToAstElement(return_stmtContext.testlist(), root)).orElse(null),
                return_stmtContext, root));
        }
        return Optional.empty();
    }
}
