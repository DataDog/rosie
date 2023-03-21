package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.FunctionCallArguments;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

public class ArgumentList {

    private static final Logger LOGGER = java.util.logging.Logger.getLogger(ArgumentList.class.getName());

    public static Optional<FunctionCallArguments> transformArgumentListToFunctionCallArguments(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null) {
            return Optional.empty();
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            Node child = node.getChild(i);
            LOGGER.info(child.getType());
        }

        return Optional.empty();
    }


}
