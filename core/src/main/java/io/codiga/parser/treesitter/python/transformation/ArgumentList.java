package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import com.google.common.collect.ImmutableSet;
import io.codiga.model.ast.common.FunctionCallArgument;
import io.codiga.model.ast.common.FunctionCallArguments;
import io.codiga.parser.common.context.ParserContext;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.Identifier.transformNodeToFunctionArgument;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class ArgumentList {

    public static final Set<TreeSitterPythonTypes> ARGUMENTS_TYPES =
        ImmutableSet.of(TreeSitterPythonTypes.IDENTIFIER, TreeSitterPythonTypes.INTEGER, TreeSitterPythonTypes.KEYWORD_ARGUMENT, TreeSitterPythonTypes.STRING);

    private static final Logger LOGGER = java.util.logging.Logger.getLogger(ArgumentList.class.getName());

    public static Optional<FunctionCallArguments> transformArgumentListToFunctionCallArguments(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null) {
            return Optional.empty();
        }
        List<FunctionCallArgument> functionArguments = new ArrayList<>();

        for (int i = 0; i < node.getChildCount(); i++) {
            Node child = node.getChild(i);
            Optional<FunctionCallArgument> argumentOptional = Optional.empty();

            /**
             * For the arguments that can be simply converted as a string, do it
             */
            if (ARGUMENTS_TYPES.contains(getNodeType(child))) {
                argumentOptional = transformNodeToFunctionArgument(child, parsingContext);

            }

            if (argumentOptional.isPresent()) {
                functionArguments.add(argumentOptional.get());
            }
        }
        ParserContext parserContext = parsingContext.getParserContextForNode(node);

        return Optional.of(new FunctionCallArguments(functionArguments, parserContext));
    }


}
