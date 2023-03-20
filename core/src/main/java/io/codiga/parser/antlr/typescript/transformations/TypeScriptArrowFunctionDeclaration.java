package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.FunctionDefinition;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import io.codiga.model.ast.javascript.JavaScriptFunctionExpression;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;


public class TypeScriptArrowFunctionDeclaration {


    public static Optional<FunctionDefinition> transformArrowFunctionDeclarationContext(TypeScriptParser.ArrowFunctionDeclarationContext ctx, ParserRuleContext root) {
        if (ctx.arrowFunctionParameters() != null) {
            Optional<FunctionDefinitionParameters> parameters = TypeScriptArrowFunctionParametersToFunctionParameters.transformArrowFunctionParametersToFunctionParameters(ctx.arrowFunctionParameters(), root);

            return Optional.of(new JavaScriptFunctionExpression(ctx.Async() != null, null, parameters.orElse(null), TypeScriptArrowFunctionBody.transformArrowFunctionBody(ctx.arrowFunctionBody(), root).orElse(null), ctx, root));
        }
        return Optional.empty();
    }
}
