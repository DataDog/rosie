package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinition;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import io.codiga.model.ast.javascript.JavaScriptFunctionDefinition;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

public class JavaScriptFunctionDeclarationToFunctionDefinition {


    public static Optional<FunctionDefinition> transformFunctionDeclarationToFunctionDefinition(JavaScriptParser.FunctionDeclarationContext ctx, ParserRuleContext root) {
        Optional<AstString> identifier = JavaScriptIdentifierToAstElement.transformIdentifierToAstString(ctx.identifier(), root);
        FunctionDefinitionParameters functionDefinitionParameters = JavaScriptFormalParametersToFunctionParameters.transformFormalParametersToFunctionParameters(ctx.formalParameterList(), root).orElse(null);
        return Optional.of(new JavaScriptFunctionDefinition(
            ctx.Async() != null,
            identifier.orElse(null),
            functionDefinitionParameters,
            null,
            ctx,
            root));
    }
}
