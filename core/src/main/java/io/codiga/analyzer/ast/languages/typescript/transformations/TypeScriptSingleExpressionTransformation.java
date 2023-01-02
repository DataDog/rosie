package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.analyzer.ast.languages.python.transformations.ClassOrFuncDefToClassDefinition;
import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinition;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptArrowFunctionDeclaration.transformArrowFunctionDeclarationContext;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIdentifierExpressionTransformation.transformIdentifierExpressionToAstElement;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptMemberDotTransformation.transformMemberDotToJavaScriptMember;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptObjectLiteralToObject.transformTypeScriptObjectLiteralToObject;

public class TypeScriptSingleExpressionTransformation {
    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefToClassDefinition.class);

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
        if (ctx == null) {
            return Optional.empty();
        }
        logger.info("single expression");
        logger.info(ctx.getClass().toString());

        // literal
        if (ctx instanceof TypeScriptParser.LiteralExpressionContext literalExpressionContext) {
            if (literalExpressionContext.literal() != null) {
                return Optional.of(new AstString(literalExpressionContext.literal().getText(), literalExpressionContext.literal(), root));
            }
        }
//
//        // function call
//        if (ctx instanceof JavaScriptParser.ArgumentsExpressionContext) {
//            TypeScriptParser.ArgumentsExpressionContext argumentsExpressionContext = (TypeScriptParser.ArgumentsExpressionContext) ctx;
//            if (argumentsExpressionContext != null) {
//                return convertToAstElement(transformArgumentsExpressionToFunctionCall(argumentsExpressionContext, root));
//            }
//        }
//

        // identifier
        if (ctx instanceof TypeScriptParser.IdentifierExpressionContext identifierExpressionContext) {
            return transformIdentifierExpressionToAstElement((TypeScriptParser.IdentifierExpressionContext) identifierExpressionContext, root);
        }
//
//
//        // array
//        if (ctx instanceof TypeScriptParser.ArrayLiteralExpressionContext arrayLiteralExpressionContext) {
//
//            if (arrayLiteralExpressionContext.arrayLiteral() != null) {
//                return transformArrayLiteralToArray(arrayLiteralExpressionContext.arrayLiteral(), root);
//            }
//        }
//
//
//        // object
        if (ctx instanceof TypeScriptParser.ObjectLiteralExpressionContext objectLiteralExpressionContext) {

            if (objectLiteralExpressionContext.objectLiteral() != null) {
                return transformTypeScriptObjectLiteralToObject(objectLiteralExpressionContext.objectLiteral(), root);

            }
        }

        // member dot
        if (ctx instanceof TypeScriptParser.MemberDotExpressionContext memberDotExpressionContext) {

            return transformMemberDotToJavaScriptMember(memberDotExpressionContext, root);
        }
//
//        // equality
//        if (ctx instanceof TypeScriptParser.EqualityExpressionContext equalityExpressionContext) {
//            Optional<AstString> operator = Optional.empty();
//            Optional<AstElement> left = Optional.empty();
//            Optional<AstElement> right = Optional.empty();
//
//            if (equalityExpressionContext.Equals_() != null) {
//                operator = Optional.of(new AstString("==", equalityExpressionContext.Equals_().getSymbol(), root));
//            }
//            if (equalityExpressionContext.NotEquals() != null) {
//                operator = Optional.of(new AstString("!==", equalityExpressionContext.NotEquals().getSymbol(), root));
//            }
//            if (equalityExpressionContext.IdentityEquals() != null) {
//                operator = Optional.of(new AstString("===", equalityExpressionContext.IdentityEquals().getSymbol(), root));
//            }
//            if (equalityExpressionContext.IdentityNotEquals() != null) {
//                operator = Optional.of(new AstString("!===", equalityExpressionContext.IdentityNotEquals().getSymbol(), root));
//            }
//
//            if (((TypeScriptParser.EqualityExpressionContext) ctx).singleExpression().size() == 2) {
//                left = transformSingleExpressionToAstElement(((TypeScriptParser.EqualityExpressionContext) ctx).singleExpression().get(0), root);
//                right = transformSingleExpressionToAstElement(((TypeScriptParser.EqualityExpressionContext) ctx).singleExpression().get(1), root);
//            }
//
//            if (operator.isPresent()) {
//                return Optional.of(new Operation(left.orElse(null), operator.get(), right.orElse(null), ctx, root));
//            }
//            return Optional.empty();
//        }
//
//        // assignment
//        if (ctx instanceof TypeScriptParser.AssignmentExpressionContext assignmentExpressionContext) {
//            Optional<AstElement> left = Optional.empty();
//            Optional<AstElement> right = Optional.empty();
//
//            if (assignmentExpressionContext.singleExpression().size() == 2) {
//                left = transformSingleExpressionToAstElement(assignmentExpressionContext.singleExpression().get(0), root);
//                right = transformSingleExpressionToAstElement(assignmentExpressionContext.singleExpression().get(1), root);
//            }
//
//            return Optional.of(new Operation(left.orElse(null), new AstString("=", assignmentExpressionContext.Assign().getSymbol(), root), right.orElse(null), ctx, root));
//        }
//
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
