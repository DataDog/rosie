package io.codiga.analyzer.ast.languages.python.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class PythonComparisonTransformation {

    private static final Logger logger = LoggerFactory.getLogger(PythonComparisonTransformation.class);


    public static Optional<? extends AstElement> transformComparisonTestToAstElement(PythonParser.ComparisonContext ctx, PythonParser.RootContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.expr() != null) {

        }

        return Optional.empty();
    }

}
