package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.FunctionDefinitionParameter;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TypeScriptParametersListToFunctionParameters {


    public static Optional<FunctionDefinitionParameters> transformParametersListToFunctionParameters(TypeScriptParser.ParameterListContext ctx, ParserRuleContext root) {

        if (ctx == null) {
            return Optional.empty();
        }

        List<FunctionDefinitionParameter> parameters = new ArrayList<>();

        if (ctx.parameter() != null) {
            for (TypeScriptParser.ParameterContext parameterContext : ctx.parameter()) {
                if (parameterContext.requiredParameter() != null) {
                    Optional<FunctionDefinitionParameter> parameterOptional = TypeScriptRequiredParameterToFunctionDefinitionParameter.transformRequiredParameterToFunctionDefinitionParameter(parameterContext.requiredParameter(), root);
                    if (parameterOptional.isPresent()) {
                        parameters.add(parameterOptional.get());
                    }
                }
                if (parameterContext.optionalParameter() != null) {
                    Optional<FunctionDefinitionParameter> parameterOptional = TypeScriptOptionalParameterToFunctionDefinitionParameter.transformOptionalParameterToFunctionDefinitionParameter(parameterContext.optionalParameter(), root);
                    if (parameterOptional.isPresent()) {
                        parameters.add(parameterOptional.get());
                    }
                }
            }
        }

        return Optional.of(new FunctionDefinitionParameters(parameters, ctx, root));
    }
}
