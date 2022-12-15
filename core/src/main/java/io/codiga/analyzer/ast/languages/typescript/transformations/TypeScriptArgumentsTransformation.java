package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionCallArgument;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;


public class TypeScriptArgumentsTransformation {


    public static Optional<AstElement> transformArgumentToAstElement(TypeScriptParser.ArgumentContext ctx, ParserRuleContext root) {
        // just a literal/identifier
        if (ctx.Identifier() != null) {
            return Optional.of(new AstString(ctx.Identifier().getText(), ctx.Identifier().getSymbol(), root));
        }

        // single expression, must dig in and get what it is
        if (ctx.singleExpression() != null) {
            return transformSingleExpressionToAstElement(ctx.singleExpression(), root);
        }

        return Optional.empty();

    }


    public static List<FunctionCallArgument> transformArgumentsContextToFunctionArguments(TypeScriptParser.ArgumentsContext ctx, ParserRuleContext root) {
        ArrayList<FunctionCallArgument> arguments = new ArrayList<>();
        if (ctx.argumentList() != null) {
            for (TypeScriptParser.ArgumentContext argument : ctx.argumentList().argument()) {
                Optional<AstElement> argumentAstElement = transformArgumentToAstElement(argument, root);
                if (argumentAstElement.isPresent()) {
                    arguments.add(new FunctionCallArgument(null, argumentAstElement.get(), argument, root));
                }
            }
        }

        return arguments;
    }
}
