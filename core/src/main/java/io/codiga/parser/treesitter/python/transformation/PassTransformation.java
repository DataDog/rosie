package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.PythonPass;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.PASS_STATEMENT;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class PassTransformation {

    private static final Logger LOGGER = Logger.getLogger(PassTransformation.class.getName());

    public static Optional<PythonPass> transformPassStatement(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != PASS_STATEMENT) {
            return Optional.empty();
        }

        return Optional.of(new PythonPass(parsingContext.getParserContextForNode(node)));
    }


}
