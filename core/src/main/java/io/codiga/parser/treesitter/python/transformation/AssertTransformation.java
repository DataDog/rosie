package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.PythonAssertStatement;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class AssertTransformation {

    private static final Logger LOGGER = Logger.getLogger(AssertTransformation.class.getName());

    public static Optional<PythonAssertStatement> transformAssert(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != TreeSitterPythonTypes.ASSERT_STATEMENT || node.getChildCount() < 2) {
            return Optional.empty();
        }
        var element = TreeSitterPythonParser.parse(node.getChild(1), parsingContext);

        return Optional.of(new PythonAssertStatement(element.orElse(null), parsingContext.getParserContextForNode(node)));
    }


}
