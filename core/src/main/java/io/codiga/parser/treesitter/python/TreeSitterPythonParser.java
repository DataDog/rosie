package io.codiga.parser.treesitter.python;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;

import static io.codiga.parser.treesitter.python.transformation.ArgumentList.transformArgumentListToFunctionCallArguments;
import static io.codiga.parser.treesitter.python.transformation.AssertTransformation.transformAssert;
import static io.codiga.parser.treesitter.python.transformation.AssignmentTransformation.transformAssignment;
import static io.codiga.parser.treesitter.python.transformation.AttributeTransformation.transformAttribute;
import static io.codiga.parser.treesitter.python.transformation.BinaryOperatorTransformation.transformBinaryOperator;
import static io.codiga.parser.treesitter.python.transformation.BlockTransformation.transformBlock;
import static io.codiga.parser.treesitter.python.transformation.BreakStatementTransformation.transformBreak;
import static io.codiga.parser.treesitter.python.transformation.CallTransformation.transformCall;
import static io.codiga.parser.treesitter.python.transformation.ComparisonOperatorTransformation.transformComparisonOperator;
import static io.codiga.parser.treesitter.python.transformation.ContinueStatementTransformation.transformContinue;
import static io.codiga.parser.treesitter.python.transformation.DecoratedDefinitionTransformation.transformDecoratedDefinition;
import static io.codiga.parser.treesitter.python.transformation.DictionaryTransformation.transformDictionary;
import static io.codiga.parser.treesitter.python.transformation.ExpressionListTransformation.transformExpressionList;
import static io.codiga.parser.treesitter.python.transformation.ExpressionStatementTransformation.transformExpressionStatement;
import static io.codiga.parser.treesitter.python.transformation.ForStatementTransformation.transformForStatement;
import static io.codiga.parser.treesitter.python.transformation.FunctionDefinitionTransformation.transformFunctionDefinition;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstString;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstStringWithoutCheck;
import static io.codiga.parser.treesitter.python.transformation.IfStatementTransformation.transformIfStatement;
import static io.codiga.parser.treesitter.python.transformation.KeywordArgument.keywordArgumentToFunctionCallArgument;
import static io.codiga.parser.treesitter.python.transformation.ListTransformation.transformList;
import static io.codiga.parser.treesitter.python.transformation.NoneTransformation.transformNone;
import static io.codiga.parser.treesitter.python.transformation.NotOperatorTransformation.transformNot;
import static io.codiga.parser.treesitter.python.transformation.PassTransformation.transformPassStatement;
import static io.codiga.parser.treesitter.python.transformation.PatternListTransformation.transformPatternList;
import static io.codiga.parser.treesitter.python.transformation.RaiseStatementTransformation.transformRaiseStatement;
import static io.codiga.parser.treesitter.python.transformation.ReturnTransformation.transformReturn;
import static io.codiga.parser.treesitter.python.transformation.SubscriptTransformation.transformSubscript;
import static io.codiga.parser.treesitter.python.transformation.TupleTransformation.transformTuple;
import static io.codiga.parser.treesitter.python.transformation.WhileStatementTransformation.transformWhileStatement;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;
import static io.codiga.utils.Conversions.convertToAstElement;

public class TreeSitterPythonParser {


    public static Optional<AstElement> parse(Node node, TreeSitterParsingContext parsingContext) {
        TreeSitterPythonTypes nodeType = getNodeType(node);
        switch (nodeType) {
            case ARGUMENT_LIST:
                return convertToAstElement(transformArgumentListToFunctionCallArguments(node, parsingContext));
            case ASSERT_STATEMENT:
                return convertToAstElement(transformAssert(node, parsingContext));
            case ASSIGNMENT:
                return convertToAstElement(transformAssignment(node, parsingContext));
            case ATTRIBUTE:
                return convertToAstElement(transformAttribute(node, parsingContext));
            case BINARY_OPERATOR:
                return convertToAstElement(transformBinaryOperator(node, parsingContext));
            case BLOCK:
                return convertToAstElement(transformBlock(node, parsingContext));
            case BREAK_STATEMENT:
                return convertToAstElement(transformBreak(node, parsingContext));
            case CALL:
                return convertToAstElement(transformCall(node, parsingContext));
            case COMPARISON_OPERATOR:
                return convertToAstElement(transformComparisonOperator(node, parsingContext));
            case CONTINUE_STATEMENT:
                return convertToAstElement(transformContinue(node, parsingContext));
            case DECORATED_DEFINITION:
                return convertToAstElement(transformDecoratedDefinition(node, parsingContext));
            case DICTIONARY:
                return convertToAstElement(transformDictionary(node, parsingContext));
            case EXPRESSION_LIST:
                return convertToAstElement(transformExpressionList(node, parsingContext));
            case EXPRESSION_STATEMENT:
                return convertToAstElement(transformExpressionStatement(node, parsingContext));
            case FOR_STATEMENT:
                return convertToAstElement(transformForStatement(node, parsingContext));
            case FUNCTION_DEFINITION:
                return convertToAstElement(transformFunctionDefinition(node, parsingContext));
            case IDENTIFIER:
                return convertToAstElement(transformIdentifierToAstString(node, parsingContext));
            case IF_STATEMENT:
                return convertToAstElement(transformIfStatement(node, parsingContext));
            case KEYWORD_ARGUMENT:
                return convertToAstElement(keywordArgumentToFunctionCallArgument(node, parsingContext));
            case LIST:
                return convertToAstElement(transformList(node, parsingContext));
            case NONE:
                return convertToAstElement(transformNone(node, parsingContext));
            case NOT_OPERATOR:
                return convertToAstElement(transformNot(node, parsingContext));
            case PATTERN_LIST:
                return convertToAstElement(transformPatternList(node, parsingContext));
            case PASS_STATEMENT:
                return convertToAstElement(transformPassStatement(node, parsingContext));
            case RAISE_STATEMENT:
                return convertToAstElement(transformRaiseStatement(node, parsingContext));
            case RETURN_STATEMENT:
                return convertToAstElement(transformReturn(node, parsingContext));
            case SUBSCRIPT:
                return convertToAstElement(transformSubscript(node, parsingContext));
            case TUPLE:
                return convertToAstElement(transformTuple(node, parsingContext));
            case WHILE_STATEMENT:
                return convertToAstElement(transformWhileStatement(node, parsingContext));
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
