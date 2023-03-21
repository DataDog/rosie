package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.*;
import io.codiga.model.ast.javascript.AstStringWithSpreadOperator;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;
import java.util.Optional;

import static io.codiga.parser.antlr.javascript.transformations.JavaScriptAnonymousFunction.transformAnonymousFunction;
import static io.codiga.parser.antlr.javascript.transformations.JavaScriptArrayLiteralToArray.transformArrayLiteralToArray;
import static io.codiga.parser.antlr.javascript.transformations.JavaScriptExpression.transformExpression;
import static io.codiga.parser.antlr.javascript.transformations.JavaScriptMemberDotTransformation.transformMemberDotToJavaScriptMember;
import static io.codiga.parser.antlr.javascript.transformations.JavaScriptNotExpression.transformNotExpressionToAstElement;
import static io.codiga.utils.Conversions.convertToAstElement;

public class JavaScriptSingleExpressionTransformation {

    public static Optional<Assignment> transformJavaScriptAssignmentExpressionToAssignment(JavaScriptParser.AssignmentExpressionContext ctx, ParserRuleContext root) {
        if (ctx.singleExpression().size() == 2) {
            Optional<AstElement> left = transformSingleExpressionToAstElement(ctx.singleExpression(0), root);
            Optional<AstElement> right = transformSingleExpressionToAstElement(ctx.singleExpression(1), root);
            if (left.isPresent() && right.isPresent()) {
                return Optional.of(new Assignment(left.get(), right.get(), ctx, root));
            }
        }
        return Optional.empty();
    }


    public static Optional<AstElement> transformSingleExpressionToAstElement(JavaScriptParser.SingleExpressionContext ctx, ParserRuleContext root) {
        return transformSingleExpressionToAstElement(ctx, false, root);
    }

    public static Optional<AstElement> transformSingleExpressionToAstElement(JavaScriptParser.SingleExpressionContext ctx, boolean isSpread, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        // literal
        if (ctx instanceof JavaScriptParser.LiteralExpressionContext literalExpressionContext) {
            if (literalExpressionContext.literal() != null) {
                return Optional.of(new AstStringWithSpreadOperator(literalExpressionContext.literal().getText(), isSpread, literalExpressionContext.literal(), root));
            }
        }

        // function call
        if (ctx instanceof JavaScriptParser.ArgumentsExpressionContext argumentsExpressionContext) {
            if (argumentsExpressionContext != null) {
                return convertToAstElement(JavaScriptFunctionCallTransformation.transformArgumentsExpressionToFunctionCall(argumentsExpressionContext, root));
            }
        }

        // identifier
        if (ctx instanceof JavaScriptParser.IdentifierExpressionContext identifierExpressionContext) {
            if (identifierExpressionContext.identifier() != null) {
                return Optional.of(new AstStringWithSpreadOperator(identifierExpressionContext.identifier().getText(), isSpread, identifierExpressionContext.identifier(), root));
            }
        }


        // array
        if (ctx instanceof JavaScriptParser.ArrayLiteralExpressionContext arrayLiteralExpressionContext) {

            if (arrayLiteralExpressionContext.arrayLiteral() != null) {
                return transformArrayLiteralToArray(arrayLiteralExpressionContext.arrayLiteral(), root);
            }
        }

        // parenthesized expression
        if (ctx instanceof JavaScriptParser.ParenthesizedExpressionContext parenthesizedExpressionContext) {

            return JavaScriptExpressionSequence.transformExpressionSequenceToAstElement(parenthesizedExpressionContext.expressionSequence(), root);
        }

        // object
        if (ctx instanceof JavaScriptParser.ObjectLiteralExpressionContext objectLiteralExpressionContext) {

            if (objectLiteralExpressionContext.objectLiteral() != null) {
                return JavaScriptObjectLiteralToObject.transformJavaScriptObjectLiteralToObject(objectLiteralExpressionContext.objectLiteral(), root);

            }
        }

        // HTML Expression
        if (ctx instanceof JavaScriptParser.HtmlElementExpressionContext htmlElementExpressionContext) {

            if (htmlElementExpressionContext.htmlElements() != null) {
                List<AstElement> elementList = htmlElementExpressionContext.htmlElements().htmlElement().stream().map(htmlElement -> convertToAstElement(JavaScriptHtmlElementTransformation.transformJavaScriptHtmlElement(htmlElement, root))).filter(v -> v.isPresent()).map(v -> v.get()).toList();
                return Optional.of(new Sequence(elementList, ctx, root));
            }
        }

        // member dot
        if (ctx instanceof JavaScriptParser.MemberDotExpressionContext memberDotExpressionContext) {

            return transformMemberDotToJavaScriptMember(memberDotExpressionContext, root);
        }

        // equality
        if (ctx instanceof JavaScriptParser.EqualityExpressionContext equalityExpressionContext) {
            Optional<AstString> operator = Optional.empty();
            Optional<AstElement> left = Optional.empty();
            Optional<AstElement> right = Optional.empty();

            if (equalityExpressionContext.Equals_() != null) {
                operator = Optional.of(new AstString("==", equalityExpressionContext.Equals_().getSymbol(), root));
            }
            if (equalityExpressionContext.NotEquals() != null) {
                operator = Optional.of(new AstString("!==", equalityExpressionContext.NotEquals().getSymbol(), root));
            }
            if (equalityExpressionContext.IdentityEquals() != null) {
                operator = Optional.of(new AstString("===", equalityExpressionContext.IdentityEquals().getSymbol(), root));
            }
            if (equalityExpressionContext.IdentityNotEquals() != null) {
                operator = Optional.of(new AstString("!===", equalityExpressionContext.IdentityNotEquals().getSymbol(), root));
            }

            if (((JavaScriptParser.EqualityExpressionContext) ctx).singleExpression().size() == 2) {
                left = transformSingleExpressionToAstElement(((JavaScriptParser.EqualityExpressionContext) ctx).singleExpression().get(0), root);
                right = transformSingleExpressionToAstElement(((JavaScriptParser.EqualityExpressionContext) ctx).singleExpression().get(1), root);
            }

            if (operator.isPresent()) {
                return Optional.of(new Operation(left.orElse(null), operator.get(), right.orElse(null), ctx, root));
            }
            return Optional.empty();
        }

        // assignment
        if (ctx instanceof JavaScriptParser.AssignmentExpressionContext assignmentExpressionContext) {
            Optional<AstElement> left = Optional.empty();
            Optional<AstElement> right = Optional.empty();

            if (assignmentExpressionContext.singleExpression().size() == 2) {
                left = transformSingleExpressionToAstElement(assignmentExpressionContext.singleExpression().get(0), root);
                right = transformSingleExpressionToAstElement(assignmentExpressionContext.singleExpression().get(1), root);
            }

            return Optional.of(new Operation(left.orElse(null), new AstString("=", assignmentExpressionContext.Assign().getSymbol(), root), right.orElse(null), ctx, root));
        }


        // or expression (||)
        if (ctx instanceof JavaScriptParser.LogicalOrExpressionContext logicalOrExpressionContext) {
            if (logicalOrExpressionContext.singleExpression().size() == 2) {
                return convertToAstElement(transformExpression(
                    logicalOrExpressionContext.singleExpression().get(0),
                    logicalOrExpressionContext.Or().getSymbol(),
                    logicalOrExpressionContext.singleExpression().get(1),
                    logicalOrExpressionContext,
                    root
                ));
            }
        }

        // or expression (&&)
        if (ctx instanceof JavaScriptParser.LogicalAndExpressionContext logicalAndExpressionContext) {
            if (logicalAndExpressionContext.singleExpression().size() == 2) {
                return convertToAstElement(transformExpression(
                    logicalAndExpressionContext.singleExpression().get(0),
                    logicalAndExpressionContext.And().getSymbol(),
                    logicalAndExpressionContext.singleExpression().get(1),
                    logicalAndExpressionContext,
                    root
                ));
            }
        }

        // not expression
        if (ctx instanceof JavaScriptParser.NotExpressionContext notExpressionContext) {
            return convertToAstElement(transformNotExpressionToAstElement(notExpressionContext, root));
        }

        // ternary expression
        if (ctx instanceof JavaScriptParser.TernaryExpressionContext ternaryExpressionContext) {
            return convertToAstElement(JavaScriptTernaryExpressionToIfStatement.transformTernaryExpressionToIfStatement(ternaryExpressionContext, root));
        }

        // functions (including arrow function)
        if (ctx instanceof JavaScriptParser.FunctionExpressionContext functionExpressionContext) {
            if (functionExpressionContext.anoymousFunction() != null) {
                Optional<FunctionDefinition> res = transformAnonymousFunction(functionExpressionContext.anoymousFunction(), root);
                if (res.isPresent()) {
                    return Optional.of(res.get());
                } else {
                    return Optional.empty();
                }
            }
        }

        return Optional.empty();
    }
}
