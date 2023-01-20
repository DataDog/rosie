package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.FunctionDefinition;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import io.codiga.model.ast.javascript.JavaScriptFunctionExpression;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptArrowFunctionBody.transformArrowFunctionBody;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptArrowFunctionParametersToFunctionParameters.transformArrowFunctionParametersToFunctionParameters;


public class TypeScriptArrowFunctionDeclaration {


    public static Optional<FunctionDefinition> transformArrowFunctionDeclarationContext(TypeScriptParser.ArrowFunctionDeclarationContext ctx, ParserRuleContext root) {
        if (ctx.arrowFunctionParameters() != null) {
            Optional<FunctionDefinitionParameters> parameters = transformArrowFunctionParametersToFunctionParameters(ctx.arrowFunctionParameters(), root);

            return Optional.of(new JavaScriptFunctionExpression(ctx.Async() != null, null, parameters.orElse(null), transformArrowFunctionBody(ctx.arrowFunctionBody(), root).orElse(null), ctx, root));
        }
        return Optional.empty();
    }
}
