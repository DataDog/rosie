package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.VariableIndex;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.SUBSCRIPT;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class SubscriptTransformation {

    private static final Logger LOGGER = Logger.getLogger(SubscriptTransformation.class.getName());

    public static Optional<VariableIndex> transformSubscript(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != SUBSCRIPT || node.getChildCount() != 4) {
            return Optional.empty();
        }

        // we have then attribute[something]
        if (node.getChildCount() == 4 && node.getChild(1).getType().equalsIgnoreCase("[") && node.getChild(3).getType().equalsIgnoreCase("]")) {
            var varname = TreeSitterPythonParser.parse(node.getChild(0), parsingContext);
            var index = TreeSitterPythonParser.parse(node.getChild(2), parsingContext);
            if (varname.isPresent() && index.isPresent()) {
                return Optional.of(new VariableIndex(varname.get(), index.get(), parsingContext.getParserContextForNode(node)));
            }

        }

        return Optional.empty();
    }


}
