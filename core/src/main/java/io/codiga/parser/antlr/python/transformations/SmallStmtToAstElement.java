package io.codiga.parser.antlr.python.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Break;
import io.codiga.model.ast.common.Continue;
import io.codiga.model.ast.common.Return;
import io.codiga.model.ast.python.PythonPass;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.utils.Conversions.flattenAstElement;
import static io.codiga.parser.antlr.python.transformations.ExprStmtTransformation.transformExprStmtToAstElement;
import static io.codiga.parser.antlr.python.transformations.PythonTestListTransformation.transformTestlistToAstElement;

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

        if (ctx instanceof PythonParser.Continue_stmtContext continue_stmtContext) {
            return Optional.of(new Continue(continue_stmtContext, root));
        }


        if (ctx instanceof PythonParser.Break_stmtContext break_stmtContext) {
            return Optional.of(new Break(break_stmtContext, root));
        }

        if (ctx instanceof PythonParser.Pass_stmtContext pass_stmtContext) {
            return Optional.of(new PythonPass(pass_stmtContext, root));
        }
        return Optional.empty();
    }
}
