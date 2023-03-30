package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.PythonNot;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class NotOperatorTransformation {

    private static final Logger LOGGER = Logger.getLogger(NotOperatorTransformation.class.getName());

    public static Optional<PythonNot> transformNot(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != TreeSitterPythonTypes.NOT_OPERATOR) {
            return Optional.empty();
        }

        return Optional.of(
            new PythonNot(
                TreeSitterPythonParser.parse(node.getChildByFieldName("argument"), parsingContext).orElse(null),
                parsingContext.getParserContextForNode(node)));
    }
}
