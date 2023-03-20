package io.codiga.parser.antlr.python.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.parser.antlr.python.transformations.PythonComparisonTransformation.transformComparisonTestToAstElement;

public class PythonLogicalTestTransformation {

    private static final Logger logger = LoggerFactory.getLogger(PythonLogicalTestTransformation.class);


    public static Optional<? extends AstElement> transformLogicalTestToAstElement(PythonParser.Logical_testContext ctx, PythonParser.RootContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.comparison() != null) {
            return transformComparisonTestToAstElement(ctx.comparison(), root);
        }

        return Optional.empty();
    }

}
