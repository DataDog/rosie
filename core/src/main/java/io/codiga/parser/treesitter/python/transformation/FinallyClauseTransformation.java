package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.FinallyClause;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.BlockTransformation.transformBlock;
import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.FINALLY_CLAUSE;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeChild;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;
import static io.codiga.utils.Conversions.convertToAstElement;

public class FinallyClauseTransformation {

    private static final Logger LOGGER = Logger.getLogger(FinallyClauseTransformation.class.getName());

    public static Optional<FinallyClause> transformFinallyClause(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != FINALLY_CLAUSE) {
            return Optional.empty();
        }
        
        var blockOptional = getNodeChild(node, TreeSitterPythonTypes.BLOCK).flatMap(b -> transformBlock(b, parsingContext));

        return Optional.of(new FinallyClause(convertToAstElement(blockOptional).orElse(null), parsingContext.getParserContextForNode(node)));

    }
}
