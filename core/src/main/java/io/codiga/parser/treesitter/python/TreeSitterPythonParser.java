package io.codiga.parser.treesitter.python;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;

import static io.codiga.parser.treesitter.python.transformation.ArgumentList.transformArgumentListToFunctionCallArguments;
import static io.codiga.parser.treesitter.python.transformation.AttributeTransformation.transformAttribute;
import static io.codiga.parser.treesitter.python.transformation.BreakStatementTransformation.transformBreak;
import static io.codiga.parser.treesitter.python.transformation.ExprToFunctionCall.transformExprToFunctionCall;
import static io.codiga.parser.treesitter.python.transformation.ExpressionListTransformation.transformExpressionList;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstString;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstStringWithoutCheck;
import static io.codiga.parser.treesitter.python.transformation.KeywordArgument.keywordArgumentToFunctionCallArgument;
import static io.codiga.parser.treesitter.python.transformation.PassTransformation.transformPassStatement;
import static io.codiga.parser.treesitter.python.transformation.PatternListTransformation.transformPatternList;
import static io.codiga.parser.treesitter.python.transformation.SubscriptTransformation.transformSubscript;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;
import static io.codiga.utils.Conversions.convertToAstElement;

public class TreeSitterPythonParser {


    public static Optional<AstElement> parse(Node node, TreeSitterParsingContext parsingContext) {
        TreeSitterPythonTypes nodeType = getNodeType(node);
        switch (nodeType) {
            case ARGUMENT_LIST:
                return convertToAstElement(transformArgumentListToFunctionCallArguments(node, parsingContext));
            case ATTRIBUTE:
                return convertToAstElement(transformAttribute(node, parsingContext));
            case BREAK_STATEMENT:
                return convertToAstElement(transformBreak(node, parsingContext));
            case CALL:
                return convertToAstElement(transformExprToFunctionCall(node, parsingContext));
            case EXPRESSION_LIST:
                return convertToAstElement(transformExpressionList(node, parsingContext));
            case IDENTIFIER:
                return convertToAstElement(transformIdentifierToAstString(node, parsingContext));
            case KEYWORD_ARGUMENT:
                return convertToAstElement(keywordArgumentToFunctionCallArgument(node, parsingContext));
            case PATTERN_LIST:
                return convertToAstElement(transformPatternList(node, parsingContext));
            case PASS_STATEMENT:
                return convertToAstElement(transformPassStatement(node, parsingContext));
            case SUBSCRIPT:
                return convertToAstElement(transformSubscript(node, parsingContext));
            case INTEGER:
            case FALSE:
            case STRING:
            case TRUE:
                return convertToAstElement(transformIdentifierToAstStringWithoutCheck(node, parsingContext));
            default:
                return Optional.empty();
        }
    }
}
