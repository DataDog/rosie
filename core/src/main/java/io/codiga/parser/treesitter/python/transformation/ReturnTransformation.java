package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.Return;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.RETURN_STATEMENT;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class ReturnTransformation {

    private static final Logger LOGGER = Logger.getLogger(ReturnTransformation.class.getName());

    public static Optional<Return> transformReturn(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != RETURN_STATEMENT) {
            return Optional.empty();
        }

        if (node.getChildCount() != 2) {
            return Optional.empty();
        }

        return Optional.of(
            new Return(
                TreeSitterPythonParser.parse(node.getChild(1), parsingContext).orElse(null),
                parsingContext.getParserContextForNode(node)));
    }


}
