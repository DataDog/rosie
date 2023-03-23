package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.python.ExceptClause;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.codiga.parser.treesitter.python.transformation.BlockTransformation.transformBlock;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstString;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.*;

public class ExceptClauseTransformation {

    private static final Logger LOGGER = Logger.getLogger(ExceptClauseTransformation.class.getName());

    public static Optional<ExceptClause> transformExceptClause(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != TreeSitterPythonTypes.EXCEPT_CLAUSE) {
            return Optional.empty();
        }

        var tuple = getNodeChild(node, TreeSitterPythonTypes.TUPLE);
        List<AstString> exceptionsNames = List.of();
        if (tuple.isPresent()) {
            exceptionsNames = getNodeChildren(tuple.get(), TreeSitterPythonTypes.IDENTIFIER)
                .stream()
                .map(n -> transformIdentifierToAstString(n, parsingContext))
                .filter(n -> n.isPresent())
                .map(n -> n.get())
                .collect(Collectors.toList());
        } else {
            exceptionsNames = getNodeChildren(node, TreeSitterPythonTypes.IDENTIFIER)
                .stream()
                .map(n -> transformIdentifierToAstString(n, parsingContext))
                .filter(n -> n.isPresent())
                .map(n -> n.get())
                .collect(Collectors.toList());
        }

        var block = getNodeChild(node, TreeSitterPythonTypes.BLOCK).flatMap(b -> transformBlock(b, parsingContext));

        return Optional.of(new ExceptClause(block.orElse(null), exceptionsNames, null, parsingContext.getParserContextForNode(node)));

    }
}
