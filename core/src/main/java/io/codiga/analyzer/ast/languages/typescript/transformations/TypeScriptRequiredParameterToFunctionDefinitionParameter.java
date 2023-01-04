package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinitionParameter;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIdentifierOrPatternTransformation.transformIdentifierNameOrPatternToAstString;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptTypeAnnotation.typeAnnotationToAstElement;


public class TypeScriptRequiredParameterToFunctionDefinitionParameter {


    public static Optional<FunctionDefinitionParameter> transformRequiredParameterToFunctionDefinitionParameter(TypeScriptParser.RequiredParameterContext ctx, ParserRuleContext root) {

        if (ctx == null) {
            return Optional.empty();
        }
        Optional<AstElement> typeOptional = Optional.empty();

        if (ctx.typeAnnotation() != null) {
            typeOptional = typeAnnotationToAstElement(ctx.typeAnnotation(), root);
        }

        if (ctx.identifierOrPattern() != null) {
            Optional<AstString> parameterNameOptional = transformIdentifierNameOrPatternToAstString(ctx.identifierOrPattern(), root);

            if (parameterNameOptional.isPresent()) {
                return Optional.of(new FunctionDefinitionParameter(parameterNameOptional.get(), typeOptional.orElse(null), null, ctx, root));
            }
        }
        return Optional.empty();
    }
}
