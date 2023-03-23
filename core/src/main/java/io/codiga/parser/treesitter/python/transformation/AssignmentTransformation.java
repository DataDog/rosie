package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.TreeSitterPythonParser.parse;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class AssignmentTransformation {

    private static final Logger LOGGER = Logger.getLogger(AssignmentTransformation.class.getName());

    public static Optional<io.codiga.model.ast.common.Assignment> transformAssignment(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != TreeSitterPythonTypes.ASSIGNMENT || node.getChildCount() != 3) {
            return Optional.empty();
        }

        Optional<AstElement> leftNode = parse(node.getChild(0), parsingContext);
        Optional<AstElement> rightNode = parse(node.getChild(2), parsingContext);

        if (leftNode.isPresent() && rightNode.isPresent()) {
            return Optional.of(new io.codiga.model.ast.common.Assignment(leftNode.get(), rightNode.get(), parsingContext.getParserContextForNode(node)));
        }

        return Optional.empty();
    }


}
