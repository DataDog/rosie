package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.PythonNone;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class NoneTransformation {

    private static final Logger LOGGER = Logger.getLogger(NoneTransformation.class.getName());

    public static Optional<PythonNone> transformNone(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != TreeSitterPythonTypes.NONE) {
            return Optional.empty();
        }


        return Optional.of(new PythonNone(parsingContext.getParserContextForNode(node)));
    }
}
