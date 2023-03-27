package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.python.PythonList;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.TreeSitterPythonParser.parse;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeChildren;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class PatternListTransformation {

    private static final Logger LOGGER = Logger.getLogger(PatternListTransformation.class.getName());

    public static Optional<PythonList> transformPatternList(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != TreeSitterPythonTypes.PATTERN_LIST) {
            return Optional.empty();
        }

        List<AstElement> listContent = getNodeChildren(node).stream()
            .map(n -> parse(n, parsingContext)).filter(e -> e.isPresent()).map(e -> e.get()).toList();

        return Optional.of(new PythonList(listContent, parsingContext.getParserContextForNode(node)));
    }
}
