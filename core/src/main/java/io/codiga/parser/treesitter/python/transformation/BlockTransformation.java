package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Sequence;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeChildrenContent;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class BlockTransformation {

    private static final Logger LOGGER = Logger.getLogger(BlockTransformation.class.getName());

    public static Optional<io.codiga.model.ast.common.Sequence> transformBlock(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != TreeSitterPythonTypes.BLOCK) {
            return Optional.empty();
        }

        List<AstElement> childrenElements = getNodeChildrenContent(node, parsingContext);

        return Optional.of(new Sequence(childrenElements, parsingContext.getParserContextForNode(node)));
    }
}
