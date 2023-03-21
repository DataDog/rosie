package io.codiga.parser.treesitter.python;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static io.codiga.parser.treesitter.python.transformation.ArgumentList.transformArgumentListToFunctionCallArguments;
import static io.codiga.parser.treesitter.python.transformation.ExprToFunctionCall.transformExprToFunctionCall;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstString;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstStringWithoutCheck;
import static io.codiga.parser.treesitter.python.transformation.KeywordArgument.keywordArgumentToFunctionCallArgument;
import static io.codiga.utils.Conversions.convertToAstElement;

public class TreeSitterPythonParser {

    static {
        Map<String, Function> NODE_TYPE_TO_PARSING_FUNCTION = new HashMap<>();

    }

    public static Optional<AstElement> parse(Node node, TreeSitterParsingContext parsingContext) {
        TreeSitterPythonTypes nodeType = TreeSitterPythonTypes.NODE_TYPE_TO_ENUMERATION.get(node.getType());
        if (nodeType == null) {
            return Optional.empty();
        }
        switch (nodeType) {
            case ARGUMENT_LIST:
                return convertToAstElement(transformArgumentListToFunctionCallArguments(node, parsingContext));
            case CALL:
                return convertToAstElement(transformExprToFunctionCall(node, parsingContext));
            case FALSE:
                return convertToAstElement(transformIdentifierToAstStringWithoutCheck(node, parsingContext));
            case IDENTIFIER:
                return convertToAstElement(transformIdentifierToAstString(node, parsingContext));
            case INTEGER:
                return convertToAstElement(transformIdentifierToAstStringWithoutCheck(node, parsingContext));
            case KEYWORD_ARGUMENT:
                return convertToAstElement(keywordArgumentToFunctionCallArgument(node, parsingContext));
            case TRUE:
                return convertToAstElement(transformIdentifierToAstStringWithoutCheck(node, parsingContext));
            default:
                return Optional.empty();
        }
    }
}
