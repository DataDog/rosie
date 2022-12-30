package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.analyzer.ast.languages.python.transformations.ClassOrFuncDefToClassDefinition;
import io.codiga.model.ast.common.*;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIdentifierNameTransformation.transformIdentifierNameToAstString;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;
import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;


public class TypeScriptIdentifierExpressionTransformation {

    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefToClassDefinition.class);

    public static boolean isIdentifierExpressionFunctionCall(TypeScriptParser.IdentifierExpressionContext ctx) {
        return (
            ctx != null &&
                ctx.singleExpression() != null &&
                ctx.identifierName() != null &&
                ctx.singleExpression() instanceof TypeScriptParser.ParenthesizedExpressionContext
        );
    }

    public static Optional<FunctionCall> transformIdentifierExpressionToFunctionCall(TypeScriptParser.IdentifierExpressionContext ctx, ParserRuleContext root) {
        if (ctx.identifierName() == null || ctx.singleExpression() == null) {
            return Optional.empty();
        }
        logger.info("plop");

        Optional<AstString> functionName = transformIdentifierNameToAstString(ctx.identifierName(), root);
        ArrayList<FunctionCallArgument> arguments = new ArrayList<>();
        if (ctx.singleExpression() != null) {
            if (ctx.singleExpression() instanceof TypeScriptParser.ParenthesizedExpressionContext) {
                TypeScriptParser.ParenthesizedExpressionContext expr = (TypeScriptParser.ParenthesizedExpressionContext) ctx.singleExpression();
                if (expr.expressionSequence() != null) {
                    logger.info("here");
                    for (TypeScriptParser.SingleExpressionContext s : expr.expressionSequence().singleExpression()) {
                        Optional<AstElement> argOptional = transformSingleExpressionToAstElement(s, root);
                        if (argOptional.isPresent()) {
                            arguments.add(new FunctionCallArgument(null, argOptional.get(), s, root));
                        }
                    }
                }
            }
        }

        return Optional.of(new FunctionCall(functionName.orElse(null), new FunctionCallArguments(arguments, ctx.singleExpression(), root), ctx, root));
    }

    public static Optional<AstElement> transformIdentifierExpressionToAstElement(TypeScriptParser.IdentifierExpressionContext ctx, ParserRuleContext root) {
        // If this is a function call, return a function call
        if (isIdentifierExpressionFunctionCall(ctx)) {
            return convertToAstElement(transformIdentifierExpressionToFunctionCall(ctx, root));
        }

        // if this is an identifier, map into a string
        if (ctx.identifierName() != null && ctx.singleExpression() == null) {
            return convertToAstElement(transformIdentifierNameToAstString(ctx.identifierName(), root));
        }

        return Optional.empty();
    }
}