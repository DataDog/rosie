package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.*;
import io.codiga.model.ast.javascript.AstStringWithSpreadOperator;
import io.codiga.parser.antlr.python.transformations.ClassOrFuncDefTransformation;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptArrayLiteralToArray.transformArrayLiteralToArray;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptArrowFunctionDeclaration.transformArrowFunctionDeclarationContext;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptExpressionSequence.transformExpressionSequenceToAstElement;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptHtmlElementTransformation.transformTypeScriptHtmlElement;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptMemberDotTransformation.transformMemberDotToJavaScriptMember;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptObjectLiteralToObject.transformTypeScriptObjectLiteralToObject;
import static io.codiga.utils.Conversions.convertToAstElement;

public class TypeScriptSingleExpressionTransformation {
    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefTransformation.class);

    public static Optional<Assignment> transformJavaScriptAssignmentExpressionToAssignment(TypeScriptParser.AssignmentExpressionContext ctx, ParserRuleContext root) {
        if (ctx.singleExpression().size() == 2) {
            Optional<AstElement> left = transformSingleExpressionToAstElement(ctx.singleExpression(0), root);
            Optional<AstElement> right = transformSingleExpressionToAstElement(ctx.singleExpression(1), root);
            if (left.isPresent() && right.isPresent()) {
                return Optional.of(new Assignment(left.get(), right.get(), ctx, root));
            }
        }
        return Optional.empty();
    }

    public static Optional<AstElement> transformSingleExpressionToAstElement(TypeScriptParser.SingleExpressionContext ctx, ParserRuleContext root) {
        return transformSingleExpressionToAstElement(ctx, false, root);
    }


    public static Optional<AstElement> transformSingleExpressionToAstElement(TypeScriptParser.SingleExpressionContext ctx, boolean isSpread, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }


        // literal
        if (ctx instanceof TypeScriptParser.LiteralExpressionContext literalExpressionContext) {
            if (literalExpressionContext.literal() != null) {
                return Optional.of(new AstStringWithSpreadOperator(literalExpressionContext.literal().getText(), isSpread, literalExpressionContext.literal(), root));
            }
        }

        // function call
        if (ctx instanceof TypeScriptParser.ArgumentsExpressionContext argumentsExpressionContext) {
            if (argumentsExpressionContext != null) {
                return convertToAstElement(TypeScriptFunctionCallTransformation.transformArgumentsExpressionToFunctionCall(argumentsExpressionContext, root));
            }
        }


        // parenthesized expression
        if (ctx instanceof TypeScriptParser.ParenthesizedExpressionContext parenthesizedExpressionContext) {
            return transformExpressionSequenceToAstElement(parenthesizedExpressionContext.expressionSequence(), root);
        }

        // logical and
        if (ctx instanceof TypeScriptParser.LogicalAndExpressionContext logicalAndExpressionContext && logicalAndExpressionContext.singleExpression().size() == 2) {
            return convertToAstElement(TypeScriptExpression.transformExpression(
                logicalAndExpressionContext.singleExpression().get(0),
                logicalAndExpressionContext.And().getSymbol(),
                logicalAndExpressionContext.singleExpression().get(1),
                logicalAndExpressionContext,
                root
            ));
        }

        // logical or
        if (ctx instanceof TypeScriptParser.LogicalOrExpressionContext logicalOrExpressionContext && logicalOrExpressionContext.singleExpression().size() == 2) {
            return convertToAstElement(TypeScriptExpression.transformExpression(
                logicalOrExpressionContext.singleExpression().get(0),
                logicalOrExpressionContext.Or().getSymbol(),
                logicalOrExpressionContext.singleExpression().get(1),
                logicalOrExpressionContext,
                root
            ));
        }

        // not expression
        if (ctx instanceof TypeScriptParser.NotExpressionContext notExpressionContext && notExpressionContext.singleExpression() != null) {
            return convertToAstElement(TypeScriptExpression.transformExpressionRightOnly(
                notExpressionContext.Not().getSymbol(),
                notExpressionContext.singleExpression(),
                notExpressionContext,
                root
            ));
        }

        // identifier
        if (ctx instanceof TypeScriptParser.IdentifierExpressionContext identifierExpressionContext) {
            return TypeScriptIdentifierExpressionTransformation.transformIdentifierExpressionToAstElement(identifierExpressionContext, isSpread, root);
        }


        // array
        if (ctx instanceof TypeScriptParser.ArrayLiteralExpressionContext arrayLiteralExpressionContext) {

            if (arrayLiteralExpressionContext.arrayLiteral() != null) {
                return transformArrayLiteralToArray(arrayLiteralExpressionContext.arrayLiteral(), root);
            }
        }

        // HTML Expression
        if (ctx instanceof TypeScriptParser.HtmlElementExpressionContext htmlElementExpressionContext) {

            if (htmlElementExpressionContext.htmlElements() != null) {
                List<AstElement> elementList = htmlElementExpressionContext.htmlElements().htmlElement().stream().map(htmlElement -> convertToAstElement(transformTypeScriptHtmlElement(htmlElement, root))).filter(v -> v.isPresent()).map(v -> v.get()).toList();
                return Optional.of(new Sequence(elementList, ctx, root));
            }
        }

        // object
        if (ctx instanceof TypeScriptParser.ObjectLiteralExpressionContext objectLiteralExpressionContext) {

            if (objectLiteralExpressionContext.objectLiteral() != null) {
                return transformTypeScriptObjectLiteralToObject(objectLiteralExpressionContext.objectLiteral(), root);

            }
        }

        // member dot
        if (ctx instanceof TypeScriptParser.MemberDotExpressionContext memberDotExpressionContext) {

            return transformMemberDotToJavaScriptMember(memberDotExpressionContext, root);
        }

        // ternary expression
        if (ctx instanceof TypeScriptParser.TernaryExpressionContext ternaryExpressionContext) {

            return convertToAstElement(TypeScriptTernaryExpressionToIfStatement.transformTernaryExpressionToIfStatement(ternaryExpressionContext, root));
        }


        // not expression
        if (ctx instanceof TypeScriptParser.NotExpressionContext notExpressionContext) {
            return TypeScriptNotExpresssion.transformNotExpressionToAstElement(notExpressionContext, root);
        }


        // equality
        if (ctx instanceof TypeScriptParser.EqualityExpressionContext equalityExpressionContext) {
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

            if (((TypeScriptParser.EqualityExpressionContext) ctx).singleExpression().size() == 2) {
                left = transformSingleExpressionToAstElement(((TypeScriptParser.EqualityExpressionContext) ctx).singleExpression().get(0), root);
                right = transformSingleExpressionToAstElement(((TypeScriptParser.EqualityExpressionContext) ctx).singleExpression().get(1), root);
            }

            if (operator.isPresent()) {
                return Optional.of(new Operation(left.orElse(null), operator.get(), right.orElse(null), ctx, root));
            }
            return Optional.empty();
        }

        // assignment
        if (ctx instanceof TypeScriptParser.AssignmentExpressionContext assignmentExpressionContext) {
            Optional<AstElement> left = Optional.empty();
            Optional<AstElement> right = Optional.empty();

            if (assignmentExpressionContext.singleExpression().size() == 2) {
                left = transformSingleExpressionToAstElement(assignmentExpressionContext.singleExpression().get(0), root);
                right = transformSingleExpressionToAstElement(assignmentExpressionContext.singleExpression().get(1), root);
            }

            return Optional.of(new Operation(left.orElse(null), new AstString("=", assignmentExpressionContext.Assign().getSymbol(), root), right.orElse(null), ctx, root));
        }

        // functions (including arrow function)
        if (ctx instanceof TypeScriptParser.ArrowFunctionExpressionContext functionExpressionContext) {
            if (functionExpressionContext.arrowFunctionDeclaration() != null) {
                Optional<FunctionDefinition> res = transformArrowFunctionDeclarationContext(functionExpressionContext.arrowFunctionDeclaration(), root);
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
