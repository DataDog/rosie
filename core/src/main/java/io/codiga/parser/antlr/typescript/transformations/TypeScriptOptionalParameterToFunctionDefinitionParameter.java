package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinitionParameter;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptIdentifierOrPatternTransformation.transformIdentifierNameOrPatternToAstString;


public class TypeScriptOptionalParameterToFunctionDefinitionParameter {


    public static Optional<FunctionDefinitionParameter> transformOptionalParameterToFunctionDefinitionParameter(TypeScriptParser.OptionalParameterContext ctx, ParserRuleContext root) {

        if (ctx == null) {
            return Optional.empty();
        }

        Optional<AstElement> typeOptional = Optional.empty();

        if (ctx.typeAnnotation() != null) {
            typeOptional = TypeScriptTypeAnnotation.typeAnnotationToAstElement(ctx.typeAnnotation(), root);
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
