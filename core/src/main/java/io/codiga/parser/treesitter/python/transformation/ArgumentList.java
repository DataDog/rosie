package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.FunctionCallArgument;
import io.codiga.model.ast.common.FunctionCallArguments;
import io.codiga.parser.common.context.ParserContext;
import io.codiga.parser.common.context.ParserContextTreeSitter;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToFunctionCallArgument;
import static io.codiga.parser.treesitter.python.transformation.KeywordArgument.keywordArgumentToFunctionCallArgument;

public class ArgumentList {

    private static final Logger LOGGER = java.util.logging.Logger.getLogger(ArgumentList.class.getName());

    public static Optional<FunctionCallArguments> transformArgumentListToFunctionCallArguments(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null) {
            return Optional.empty();
        }
        List<FunctionCallArgument> functionArguments = new ArrayList<>();

        for (int i = 0; i < node.getChildCount(); i++) {
            Node child = node.getChild(i);
            LOGGER.info(child.getType());
            Optional<FunctionCallArgument> argumentOptional = Optional.empty();

            if (child.getType().equalsIgnoreCase(TreeSitterPythonTypes.IDENTIFIER.label)) {
                argumentOptional = transformIdentifierToFunctionCallArgument(child, parsingContext);

            }
            if (child.getType().equalsIgnoreCase(TreeSitterPythonTypes.KEYWORD_ARGUMENT.label)) {
                argumentOptional = keywordArgumentToFunctionCallArgument(child, parsingContext);
            }

            if (argumentOptional.isPresent()) {
                functionArguments.add(argumentOptional.get());
            }
        }
        ParserContext parserContext = ParserContextTreeSitter.builder().code(parsingContext.getCode()).root(parsingContext.getRootNode()).node(node).build();

        return Optional.of(new FunctionCallArguments(functionArguments, parserContext));
    }


}
