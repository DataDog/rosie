package io.codiga.parser.treesitter.python;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;

import static io.codiga.parser.treesitter.python.TreeSitterPythonTypes.*;
import static io.codiga.parser.treesitter.python.transformation.ArgumentList.transformArgumentListToFunctionCallArguments;
import static io.codiga.parser.treesitter.python.transformation.ExprToFunctionCall.transformExprToFunctionCall;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstString;
import static io.codiga.utils.Conversions.convertToAstElement;

public class TreeSitterPythonParser {

    public static Optional<AstElement> parse(Node node, TreeSitterParsingContext parsingContext) {
        /**
         * TODO: implement this logic with maps
         */
        switch (node.getType()) {
            case ARGUMENT_LIST:
                return convertToAstElement(transformArgumentListToFunctionCallArguments(node, parsingContext));
            case CALL:
                return convertToAstElement(transformExprToFunctionCall(node, parsingContext));
            case IDENTIFIER:
                return convertToAstElement(transformIdentifierToAstString(node, parsingContext));
            default:
                return Optional.empty();
        }
    }
}
