package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.*;
import io.codiga.parser.antlr.typescript.CodigaVisitor;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptArrayLiteralToArray.transformArrayLiteralToArray;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptIdentifierExpressionSequence.transformExpressionSequenceToFunctionArguments;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptIdentifierOrKeywordTransformation.transformIdentifierNameToAstString;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptObjectLiteralToObject.transformTypeScriptObjectLiteralToObject;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;
import static io.codiga.utils.Conversions.convertToAstElement;

public class TypeScriptVariableDeclaration {
    private final Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);


    public static Optional<VariableDeclaration> transformVariableDeclarationToVariableDeclaration(AstString modifier, TypeScriptParser.VariableDeclarationContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        if (ctx.singleExpression().size() != 1) {
            return Optional.empty();
        }

        Optional<AstElement> name = Optional.empty();


        if (ctx.identifierOrKeyWord() != null) {
            name = convertToAstElement(transformIdentifierNameToAstString(ctx.identifierOrKeyWord(), root));
        }

        if (ctx.arrayLiteral() != null) {
            name = convertToAstElement(transformArrayLiteralToArray(ctx.arrayLiteral(), root));
        }

        if (ctx.objectLiteral() != null) {
            name = convertToAstElement(transformTypeScriptObjectLiteralToObject(ctx.objectLiteral(), root));
        }

        Optional<AstElement> value = transformSingleExpressionToAstElement(ctx.singleExpression().get(0), root);
        Optional<AstElement> type = TypeScriptTypeAnnotation.typeAnnotationToAstElement(ctx.typeAnnotation(), root);

        if (name.isPresent()) {
            return Optional.of(
                new VariableDeclaration(
                    modifier,
                    name.orElse(null),
                    type.orElse(null),
                    value.orElse(null),
                    ctx, root));
        }

        return Optional.empty();

    }


    public static Optional<Assignment> transformVariableDeclarationToAssignment(TypeScriptParser.VariableDeclarationContext ctx, ParserRuleContext root) {
        if (ctx.singleExpression().size() != 1) {
            return Optional.empty();
        }

        Optional<AstElement> leftSide = Optional.empty();


        if (ctx.identifierOrKeyWord() != null) {
            leftSide = convertToAstElement(transformIdentifierNameToAstString(ctx.identifierOrKeyWord(), root));
        }

        if (ctx.arrayLiteral() != null) {
            leftSide = convertToAstElement(transformArrayLiteralToArray(ctx.arrayLiteral(), root));
        }

        if (ctx.objectLiteral() != null) {
            leftSide = convertToAstElement(transformTypeScriptObjectLiteralToObject(ctx.objectLiteral(), root));
        }

        Optional<AstElement> rightSide = transformSingleExpressionToAstElement(ctx.singleExpression().get(0), root);

        if (leftSide.isPresent() && rightSide.isPresent()) {
            return Optional.of(new Assignment(leftSide.get(), rightSide.get(), ctx, root));
        }

        return Optional.empty();

    }

    public static Optional<FunctionCall> transformVariableDeclarationToFunctionCall(TypeScriptParser.VariableDeclarationContext ctx, ParserRuleContext root) {
        if (ctx.singleExpression().size() != 1 || ctx.Assign() != null || ctx.identifierOrKeyWord() == null) {
            return Optional.empty();
        }

        TypeScriptParser.SingleExpressionContext arguments = ctx.singleExpression().get(0);

        if (!(arguments instanceof TypeScriptParser.ParenthesizedExpressionContext)) {
            return Optional.empty();
        }

        TypeScriptParser.ParenthesizedExpressionContext parenthesizedExpressionContext = (TypeScriptParser.ParenthesizedExpressionContext) arguments;


        Optional<AstString> functionName = transformIdentifierNameToAstString(ctx.identifierOrKeyWord(), root);
        Optional<FunctionCallArguments> functionCallArgumentsOptional = transformExpressionSequenceToFunctionArguments(parenthesizedExpressionContext.expressionSequence(), root);

        if (functionName.isPresent() && functionCallArgumentsOptional.isPresent()) {
            return Optional.of(new FunctionCall(functionName.get(), functionCallArgumentsOptional.get(), ctx, root));
        }

        return Optional.empty();

    }
}
