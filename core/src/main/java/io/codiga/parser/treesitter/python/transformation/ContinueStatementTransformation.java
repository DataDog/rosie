package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.Continue;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.CONTINUE_STATEMENT;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class ContinueStatementTransformation {

    private static final Logger LOGGER = Logger.getLogger(ContinueStatementTransformation.class.getName());

    public static Optional<Continue> transformContinue(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != CONTINUE_STATEMENT) {
            return Optional.empty();
        }

        return Optional.of(new Continue(parsingContext.getParserContextForNode(node)));
    }


}
