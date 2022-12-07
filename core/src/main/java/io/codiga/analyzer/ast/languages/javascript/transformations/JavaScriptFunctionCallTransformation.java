package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.FunctionCall;
import io.codiga.model.ast.common.FunctionCallArgument;
import io.codiga.model.ast.common.FunctionCallArguments;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptArgumentsTransformation.transformArgumentsContextToFunctionArguments;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;

public class JavaScriptFunctionCallTransformation {

    public static boolean isFunctionCall(ParseTree node) {

        if (!(node instanceof JavaScriptParser.ArgumentsExpressionContext)) {
            return false;
        }

        JavaScriptParser.ArgumentsExpressionContext ctx = (JavaScriptParser.ArgumentsExpressionContext) node;

        if (ctx.arguments() == null || ctx.singleExpression() == null) {
            return false;
        }

        return true;
    }


    public static Optional<FunctionCall> transformArgumentsExpressionToFunctionCall(JavaScriptParser.ArgumentsExpressionContext ctx, ParserRuleContext root) {
        if (!isFunctionCall(ctx)) {
            return Optional.empty();
        }


        Optional<AstElement> functionNameNode = transformSingleExpressionToAstElement(ctx.singleExpression(), root);
        List<FunctionCallArgument> arguments = transformArgumentsContextToFunctionArguments(ctx.arguments(), root);


        return Optional.of(new FunctionCall(functionNameNode.orElse(null), new FunctionCallArguments(arguments, ctx.arguments(), root), ctx, root));
    }
}
