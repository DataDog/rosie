package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionCallArgument;
import io.codiga.parser.common.context.ParserContext;
import io.codiga.parser.common.context.ParserContextTreeSitter;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.TreeSitterPythonTypes.KEYWORD_ARGUMENT;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstString;

public class KeywordArgument {

    private static final Logger LOGGER = Logger.getLogger(KeywordArgument.class.getName());

    public static Optional<FunctionCallArgument> keywordArgumentToFunctionCallArgument(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || !node.getType().equalsIgnoreCase(KEYWORD_ARGUMENT) || node.getChildCount() != 2) {
            return Optional.empty();
        }

        Node name = node.getChild(0);
        Node value = node.getChild(1);

        Optional<AstString> nameOptional = transformIdentifierToAstString(name, parsingContext);
        Optional<AstElement> valueOptional = TreeSitterPythonParser.parse(value, parsingContext);

        if (nameOptional.isPresent() && valueOptional.isPresent()) {
            ParserContext parserContext = ParserContextTreeSitter.builder().code(parsingContext.getCode()).root(parsingContext.getRootNode()).node(node).build();

            return Optional.of(new FunctionCallArgument(nameOptional.orElse(null), valueOptional.orElse(null), parserContext));
        }
        return Optional.empty();
    }


}
