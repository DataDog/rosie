package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinition;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import io.codiga.model.ast.javascript.JavaScriptFunctionDefinition;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;


public class TypeScriptFunctionExpressionDeclarationToFunctionDefinition {


    public static Optional<FunctionDefinition> transformFunctionDeclarationToFunctionDefinition(TypeScriptParser.FunctionDeclarationContext ctx, ParserRuleContext root) {
        Optional<AstString> identifier = AstString.fromTerminalNode(ctx.Identifier(), root);
        FunctionDefinitionParameters functionDefinitionParameters = TypeScriptParametersListToFunctionParameters.transformParametersListToFunctionParameters(ctx.callSignature().parameterList(), root).orElse(null);
        return Optional.of(new JavaScriptFunctionDefinition(
            ctx.Async() != null,
            identifier.orElse(null),
            functionDefinitionParameters,
            null,
            ctx,
            root));
    }
}
