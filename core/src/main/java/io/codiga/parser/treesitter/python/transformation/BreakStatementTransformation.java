package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.Break;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.BREAK_STATEMENT;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class BreakStatementTransformation {

    private static final Logger LOGGER = Logger.getLogger(BreakStatementTransformation.class.getName());

    public static Optional<Break> transformBreak(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != BREAK_STATEMENT) {
            return Optional.empty();
        }

        return Optional.of(new Break(parsingContext.getParserContextForNode(node)));
    }


}
