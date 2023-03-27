package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstStringWithoutCheck;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;
import static io.codiga.utils.Conversions.convertToAstElement;

public class TypeTransformation {

    private static final Logger LOGGER = Logger.getLogger(TypeTransformation.class.getName());

    public static Optional<AstElement> transformType(Node node, TreeSitterParsingContext parsingContext) {
        if (getNodeType(node) == TreeSitterPythonTypes.TYPE) {
            return convertToAstElement(transformIdentifierToAstStringWithoutCheck(node, parsingContext));
        }
        return Optional.empty();
    }
}
