package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.TryStatement;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.codiga.parser.treesitter.python.transformation.BlockTransformation.transformBlock;
import static io.codiga.parser.treesitter.python.transformation.ExceptClauseTransformation.transformExceptClause;
import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.TRY_STATEMENT;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.*;

public class TryStatementTransformation {

    private static final Logger LOGGER = Logger.getLogger(TryStatementTransformation.class.getName());

    public static Optional<TryStatement> transformTryStatement(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != TRY_STATEMENT || node.getChildCount() != 4) {
            return Optional.empty();
        }

        getNodeChildren(node).forEach(n -> System.out.println(n.getType()));

        var blockOptional = getNodeChild(node, TreeSitterPythonTypes.BLOCK).flatMap(b -> transformBlock(b, parsingContext));

        var exceptClauses = getNodeChildren(node, TreeSitterPythonTypes.EXCEPT_CLAUSE)
            .stream()
            .map(n -> transformExceptClause(n, parsingContext))
            .filter(n -> n.isPresent())
            .map(n -> n.get())
            .collect(Collectors.toList());


        if (blockOptional.isPresent()) {
            return Optional.of(
                new TryStatement(
                    blockOptional.get(),
                    exceptClauses,
                    null,
                    parsingContext.getParserContextForNode(node)));
        }
        return Optional.empty();
    }


}
