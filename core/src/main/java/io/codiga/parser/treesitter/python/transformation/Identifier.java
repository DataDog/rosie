package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.common.context.ParserContext;
import io.codiga.parser.common.context.ParserContextTreeSitter;
import io.codiga.parser.treesitter.python.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

public class Identifier {

    private static final Logger LOGGER = Logger.getLogger(Identifier.class.getName());


    public static Optional<AstString> transformIdentifierToAstString(Node node, TreeSitterParsingContext parsingContext) {
        if (!node.getType().equalsIgnoreCase(TreeSitterPythonTypes.IDENTIFIER)) {
            return Optional.empty();
        }


        String value = parsingContext.getStringForNode(node);
        if (value != null) {
            ParserContext parserContext = ParserContextTreeSitter.builder().code(parsingContext.getCode()).root(parsingContext.getRootNode()).node(node).build();
            return Optional.of(new AstString(value, parserContext));
        }

        return Optional.empty();
    }


}
