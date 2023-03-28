package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.PythonList;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeChildren;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class ListTransformation {

    private static final Logger LOGGER = Logger.getLogger(ListTransformation.class.getName());

    public static Optional<PythonList> transformList(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != TreeSitterPythonTypes.LIST) {
            return Optional.empty();
        }
        var elements = getNodeChildren(node)
            .stream()
            .map(n -> TreeSitterPythonParser.parse(n, parsingContext))
            .filter(n -> n.isPresent())
            .map(n -> n.get())
            .collect(Collectors.toList());

        return Optional.of(new PythonList(elements, parsingContext.getParserContextForNode(node)));
    }
}
