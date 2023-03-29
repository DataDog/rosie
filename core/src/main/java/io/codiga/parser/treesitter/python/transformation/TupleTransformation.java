package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.PythonTuple;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeChildren;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class TupleTransformation {

    private static final Logger LOGGER = Logger.getLogger(TupleTransformation.class.getName());

    public static Optional<PythonTuple> transformTuple(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != TreeSitterPythonTypes.TUPLE) {
            return Optional.empty();
        }
        var elements = getNodeChildren(node)
            .stream()
            .map(n -> TreeSitterPythonParser.parse(n, parsingContext))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());

        return Optional.of(new PythonTuple(elements, parsingContext.getParserContextForNode(node)));
    }
}
