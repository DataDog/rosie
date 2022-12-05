package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinition;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptFormalParametersToFunctionParameters.transformFormalParametersToFunctionParameters;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptIdentifierToAstElement.transformIdentifierToAstString;

public class JavaScriptFunctionDeclarationToFunctionDefinition {


    public static Optional<FunctionDefinition> transformFunctionDeclarationToFunctionDefinition(JavaScriptParser.FunctionDeclarationContext ctx, ParserRuleContext root) {
        Optional<AstString> identifier = transformIdentifierToAstString(ctx.identifier(), root);
        FunctionDefinitionParameters functionDefinitionParameters = transformFormalParametersToFunctionParameters(ctx.formalParameterList(), root).orElse(null);

        return Optional.of(new FunctionDefinition(identifier.orElse(null), functionDefinitionParameters, ctx, root));
    }
}
