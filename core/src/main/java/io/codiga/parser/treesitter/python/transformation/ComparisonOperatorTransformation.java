package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.PythonComparison;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.COMPARISON_OPERATOR;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class ComparisonOperatorTransformation {

    private static final Logger LOGGER = Logger.getLogger(ComparisonOperatorTransformation.class.getName());

    public static Optional<PythonComparison> transformComparisonOperator(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != COMPARISON_OPERATOR || node.getChildCount() < 3) {
            return Optional.empty();
        }
        
        return Optional.of(new PythonComparison(
            TreeSitterPythonParser.parse(node.getChild(0), parsingContext).orElse(null), // left
            node.getChild(1).getType(),
            TreeSitterPythonParser.parse(node.getChild(2), parsingContext).orElse(null), // right
            null,
            parsingContext.getParserContextForNode(node)
        ));
    }


}
