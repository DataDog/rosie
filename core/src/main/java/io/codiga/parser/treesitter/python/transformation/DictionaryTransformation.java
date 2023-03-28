package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.PythonDictionary;
import io.codiga.model.ast.python.PythonDictionaryElement;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.DICTIONARY;
import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.PAIR;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeChildren;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class DictionaryTransformation {

    private static final Logger LOGGER = Logger.getLogger(DictionaryTransformation.class.getName());


    private static Optional<PythonDictionaryElement> transformPairToDictionaryElement(Node node, TreeSitterParsingContext parsingContext) {
        var key = Optional.ofNullable(node.getChildByFieldName("key")).flatMap(n -> TreeSitterPythonParser.parse(n, parsingContext));
        var value = Optional.ofNullable(node.getChildByFieldName("value")).flatMap(n -> TreeSitterPythonParser.parse(n, parsingContext));

        if (key.isPresent() && value.isPresent()) {
            return Optional.of(new PythonDictionaryElement(key.get(), value.get(), parsingContext.getParserContextForNode(node)));
        }
        return Optional.empty();
    }


    public static Optional<PythonDictionary> transformDictionary(Node node, TreeSitterParsingContext parsingContext) {
        if (getNodeType(node) != DICTIONARY) {
            return Optional.empty();
        }

        var elements = getNodeChildren(node, PAIR).stream()
            .map(e -> transformPairToDictionaryElement(e, parsingContext))
            .filter(v -> v.isPresent())
            .map(v -> v.get())
            .collect(Collectors.toList());

        return Optional.of(new PythonDictionary(elements, parsingContext.getParserContextForNode(node)));

    }


}
