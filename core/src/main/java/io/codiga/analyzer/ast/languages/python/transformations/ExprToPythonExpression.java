package io.codiga.analyzer.ast.languages.python.transformations;

import io.codiga.model.ast.python.PythonExpression;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.transformations.AtomTransformation.transformAtomToPythonString;
import static io.codiga.model.ast.python.PythonExpression.PYTHON_EXPRESSION_TYPE_ATOM;

public class ExprToPythonExpression {

    private static final Logger logger = LoggerFactory.getLogger(ExprToPythonExpression.class);


    public static Optional<PythonExpression> transformExprToPythonExpression(PythonParser.ExprContext ctx, PythonParser.RootContext root) {
        if (ctx.atom() != null) {
            return transformAtomToPythonString(ctx.atom(), root).map(a -> new PythonExpression(a, PYTHON_EXPRESSION_TYPE_ATOM, ctx.atom(), root));

        }
        return Optional.empty();
    }
}
