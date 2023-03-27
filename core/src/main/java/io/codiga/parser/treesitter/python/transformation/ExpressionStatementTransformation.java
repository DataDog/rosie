package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class ExpressionStatementTransformation {

    private static final Logger LOGGER = Logger.getLogger(ExpressionStatementTransformation.class.getName());

    public static Optional<AstElement> transformExpressionStatement(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != TreeSitterPythonTypes.EXPRESSION_STATEMENT) {
            return Optional.empty();
        }

        if (node.getChildCount() == 1) {
            return TreeSitterPythonParser.parse(node.getChild(0), parsingContext);
        }

        return Optional.empty();
    }
}
