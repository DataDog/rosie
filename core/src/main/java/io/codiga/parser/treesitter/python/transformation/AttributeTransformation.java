package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstStringWithoutCheck;
import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.ATTRIBUTE;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;
import static io.codiga.utils.Conversions.convertToAstElement;

public class AttributeTransformation {

    private static final Logger LOGGER = Logger.getLogger(AttributeTransformation.class.getName());

    public static Optional<AstElement> transformAttribute(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != ATTRIBUTE) {
            return Optional.empty();
        }

        return convertToAstElement(transformIdentifierToAstStringWithoutCheck(node, parsingContext));
    }
}
