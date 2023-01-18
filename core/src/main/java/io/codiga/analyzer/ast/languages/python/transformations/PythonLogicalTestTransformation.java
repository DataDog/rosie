package io.codiga.analyzer.ast.languages.python.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.transformations.PythonComparisonTransformation.transformComparisonTestToAstElement;

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
