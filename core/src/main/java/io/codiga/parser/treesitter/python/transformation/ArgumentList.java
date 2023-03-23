package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import com.google.common.collect.ImmutableSet;
import io.codiga.model.ast.common.FunctionCallArgument;
import io.codiga.model.ast.common.FunctionCallArguments;
import io.codiga.parser.common.context.ParserContextTreeSitter;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.Identifier.transformNodeToFunctionArgument;
import static io.codiga.parser.treesitter.python.transformation.KeywordArgument.keywordArgumentToFunctionCallArgument;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class ArgumentList {

    public static final Set<TreeSitterPythonTypes> ARGUMENTS_TYPES =
        ImmutableSet.of(TreeSitterPythonTypes.IDENTIFIER, TreeSitterPythonTypes.INTEGER, TreeSitterPythonTypes.STRING);

    private static final Logger LOGGER = java.util.logging.Logger.getLogger(ArgumentList.class.getName());

    public static Optional<FunctionCallArguments> transformArgumentListToFunctionCallArguments(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != TreeSitterPythonTypes.ARGUMENT_LIST || node.getChildCount() < 2) {
            return Optional.empty();
        }
        List<FunctionCallArgument> functionArguments = new ArrayList<>();

        for (int i = 0; i < node.getChildCount(); i++) {
            Node child = node.getChild(i);
            Optional<FunctionCallArgument> argumentOptional = Optional.empty();
            TreeSitterPythonTypes childType = getNodeType(child);
            /**
             * For the arguments that can be simply converted as a string, do it
             */
            if (ARGUMENTS_TYPES.contains(childType)) {
                argumentOptional = transformNodeToFunctionArgument(child, parsingContext);

            }

            /**
             * If this is a keyword argument (e.g. foo=bar), handle it differently than a simple value.
             */
            if (childType == TreeSitterPythonTypes.KEYWORD_ARGUMENT) {
                argumentOptional = keywordArgumentToFunctionCallArgument(child, parsingContext);
            }

            if (argumentOptional.isPresent()) {
                functionArguments.add(argumentOptional.get());
            }
        }
        ParserContextTreeSitter parserContext = parsingContext.getParserContextForNode(node);
        parserContext.setStartByte(node.getChild(0).getEndByte());

        return Optional.of(new FunctionCallArguments(functionArguments, parserContext));
    }


}
