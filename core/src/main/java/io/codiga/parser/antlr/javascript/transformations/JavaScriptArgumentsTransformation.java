package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.FunctionCallArgument;
import io.codiga.model.ast.javascript.AstStringWithSpreadOperator;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JavaScriptArgumentsTransformation {


    public static Optional<AstElement> transformArgumentToAstElement(JavaScriptParser.ArgumentContext ctx, ParserRuleContext root) {
        // just a literal/identifier
        if (ctx.identifier() != null) {
            return Optional.of(new AstStringWithSpreadOperator(ctx.identifier().getText(), ctx.Ellipsis() != null, ctx.identifier(), root));
        }

        // single expression, must dig in and get what it is
        if (ctx.singleExpression() != null) {
            return JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement(ctx.singleExpression(), ctx.Ellipsis() != null, root);
        }


        return Optional.empty();

    }


    public static List<FunctionCallArgument> transformArgumentsContextToFunctionArguments(JavaScriptParser.ArgumentsContext ctx, ParserRuleContext root) {
        ArrayList<FunctionCallArgument> arguments = new ArrayList<>();
        for (JavaScriptParser.ArgumentContext argument : ctx.argument()) {
            Optional<AstElement> argumentAstElement = transformArgumentToAstElement(argument, root);
            if (argumentAstElement.isPresent()) {
                arguments.add(new FunctionCallArgument(null, argumentAstElement.get(), argument, root));
            }
        }
        return arguments;
    }
}
