package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinitionParameter;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIdentifierOrPatternTransformation.transformIdentifierNameOrPatternToAstString;


public class TypeScriptRequiredParameterToFunctionDefinitionParameter {


    public static Optional<FunctionDefinitionParameter> transformRequiredParameterToFunctionDefinitionParameter(TypeScriptParser.RequiredParameterContext ctx, ParserRuleContext root) {

        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.identifierOrPattern() != null) {
            Optional<AstString> parameterNameOptional = transformIdentifierNameOrPatternToAstString(ctx.identifierOrPattern(), root);
            if (parameterNameOptional.isPresent()) {
                return Optional.of(new FunctionDefinitionParameter(parameterNameOptional.get(), null, null, ctx, root));
            }
        }
        return Optional.empty();
    }
}
