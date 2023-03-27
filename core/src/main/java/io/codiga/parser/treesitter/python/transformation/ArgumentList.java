package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import com.google.common.collect.ImmutableSet;
import io.codiga.model.ast.common.FunctionCallArgument;
import io.codiga.model.ast.common.FunctionCallArguments;
import io.codiga.model.ast.python.PythonArgument;
import io.codiga.model.common.Position;
import io.codiga.parser.common.context.ParserContextTreeSitter;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstString;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformNodeToFunctionArgument;
import static io.codiga.parser.treesitter.python.transformation.KeywordArgument.keywordArgumentToFunctionCallArgument;
import static io.codiga.parser.treesitter.python.transformation.KeywordArgument.keywordArgumentToPythonArgument;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeChildren;
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
            if (childType != TreeSitterPythonTypes.UNKNOWN && childType != TreeSitterPythonTypes.KEYWORD_ARGUMENT) {
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
        var openingParenthesisPosition = node.getChild(0).getEndPosition();
        parserContext.setStartPosition(new Position(openingParenthesisPosition.row + 1, openingParenthesisPosition.column + 1));

        return Optional.of(new FunctionCallArguments(functionArguments, parserContext));
    }


    private static Optional<PythonArgument> transformNodeListToPythonArguments(Node node, TreeSitterParsingContext parsingContext) {
        if (getNodeType(node) == TreeSitterPythonTypes.KEYWORD_ARGUMENT) {
            return keywordArgumentToPythonArgument(node, parsingContext);
        }
        if (getNodeType(node) == TreeSitterPythonTypes.IDENTIFIER) {
            var id = transformIdentifierToAstString(node, parsingContext);
            if (id.isPresent()) {
                return Optional.of(new PythonArgument(null, id.get(), parsingContext.getParserContextForNode(node)));
            }
        }
        return Optional.empty();
    }

    public static List<PythonArgument> transformArgumentListToPythonArguments(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != TreeSitterPythonTypes.ARGUMENT_LIST) {
            return List.of();
        }
        List<PythonArgument> res = new ArrayList<>();


        return getNodeChildren(node)
            .stream()
            .map(n -> transformNodeListToPythonArguments(n, parsingContext))
            .filter(n -> n.isPresent())
            .map(n -> n.get())
            .toList();
    }


}
