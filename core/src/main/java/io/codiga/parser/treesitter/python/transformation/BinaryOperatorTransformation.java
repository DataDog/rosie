package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.Operation;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class BinaryOperatorTransformation {

    private static final Logger LOGGER = Logger.getLogger(BinaryOperatorTransformation.class.getName());

    public static Optional<Operation> transformBinaryOperator(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != TreeSitterPythonTypes.BINARY_OPERATOR || node.getChildCount() != 3) {
            return Optional.empty();
        }

        Optional<AstElement> leftElement = TreeSitterPythonParser.parse(node.getChild(0), parsingContext);
        AstString operator = new AstString(node.getChild(1).getType(), parsingContext.getParserContextForNode(node.getChild(1)));
        Optional<AstElement> rightelement = TreeSitterPythonParser.parse(node.getChild(2), parsingContext);

        if (leftElement.isPresent() && rightelement.isPresent()) {
            return Optional.of(
                new Operation(
                    leftElement.get(),
                    operator,
                    rightelement.get(),
                    parsingContext.getParserContextForNode(node)));

        }
        return Optional.empty();
    }
}
