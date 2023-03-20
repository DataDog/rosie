package io.codiga.parser.antlr.python.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.parser.antlr.python.transformations.PythonTestListTransformation.transformTestlistToAstElement;

public class PythonTestListStarExprTransformation {

    private static final Logger logger = LoggerFactory.getLogger(PythonTestListStarExprTransformation.class);


    public static Optional<? extends AstElement> transformTestListStarExprToAstElement(PythonParser.Testlist_star_exprContext ctx, PythonParser.RootContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.testlist() != null) {
            return transformTestlistToAstElement(ctx.testlist(), root);
        }
        return Optional.empty();
    }

}
