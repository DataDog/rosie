package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinition;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptAnonymousFunction.transformAnonymousFunction;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptArrayLiteralToArray.transformArrayLiteralToArray;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptObjectLiteralToObject.transformJavaScriptObjectLiteralToObject;

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
        // literal
        if (ctx instanceof JavaScriptParser.LiteralExpressionContext) {
            JavaScriptParser.LiteralExpressionContext literalExpressionContext = (JavaScriptParser.LiteralExpressionContext) ctx;
            if (literalExpressionContext.literal() != null) {
                return Optional.of(new AstString(literalExpressionContext.literal().getText(), literalExpressionContext.literal(), root));
            }
        }


        // identifier
        if (ctx instanceof JavaScriptParser.IdentifierExpressionContext) {
            JavaScriptParser.IdentifierExpressionContext identifierExpressionContext = (JavaScriptParser.IdentifierExpressionContext) ctx;
            if (identifierExpressionContext.identifier() != null) {
                return Optional.of(new AstString(identifierExpressionContext.identifier().getText(), identifierExpressionContext.identifier(), root));
            }
        }


        // array
        if (ctx instanceof JavaScriptParser.ArrayLiteralExpressionContext) {
            JavaScriptParser.ArrayLiteralExpressionContext arrayLiteralExpressionContext = (JavaScriptParser.ArrayLiteralExpressionContext) ctx;

            if (arrayLiteralExpressionContext.arrayLiteral() != null) {
                return transformArrayLiteralToArray(arrayLiteralExpressionContext.arrayLiteral(), root);
            }
        }


        // object
        if (ctx instanceof JavaScriptParser.ObjectLiteralExpressionContext) {
            JavaScriptParser.ObjectLiteralExpressionContext objectLiteralExpressionContext = (JavaScriptParser.ObjectLiteralExpressionContext) ctx;

            if (objectLiteralExpressionContext.objectLiteral() != null) {
                return transformJavaScriptObjectLiteralToObject(objectLiteralExpressionContext.objectLiteral(), root);

            }
        }

        // functions (including arrow function)
        if (ctx instanceof JavaScriptParser.FunctionExpressionContext) {
            JavaScriptParser.FunctionExpressionContext functionExpressionContext = (JavaScriptParser.FunctionExpressionContext) ctx;
            if (functionExpressionContext.anoymousFunction() != null) {
                Optional<FunctionDefinition> res = transformAnonymousFunction(functionExpressionContext.anoymousFunction(), root);
                if (res.isPresent()) {
                    return Optional.of((AstElement) res.get());
                } else {
                    return Optional.empty();
                }
            }
        }

        return Optional.empty();
    }
}
