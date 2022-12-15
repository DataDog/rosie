package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.FunctionCall;
import io.codiga.model.ast.common.FunctionCallArgument;
import io.codiga.model.ast.common.FunctionCallArguments;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptArgumentsTransformation.transformArgumentsContextToFunctionArguments;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;


public class TypeScriptFunctionCallTransformation {

    public static boolean isFunctionCall(ParseTree node) {

        if (!(node instanceof TypeScriptParser.ArgumentsExpressionContext ctx)) {
            return false;
        }

        return ctx.arguments() != null && ctx.singleExpression() != null;
    }


    public static Optional<FunctionCall> transformArgumentsExpressionToFunctionCall(TypeScriptParser.ArgumentsExpressionContext ctx, ParserRuleContext root) {
        if (!isFunctionCall(ctx)) {
            return Optional.empty();
        }

        Optional<AstElement> functionNameNode = transformSingleExpressionToAstElement(ctx.singleExpression(), root);
        List<FunctionCallArgument> arguments = transformArgumentsContextToFunctionArguments(ctx.arguments(), root);
        
        return Optional.of(new FunctionCall(functionNameNode.orElse(null), new FunctionCallArguments(arguments, ctx.arguments(), root), ctx, root));
    }
}
