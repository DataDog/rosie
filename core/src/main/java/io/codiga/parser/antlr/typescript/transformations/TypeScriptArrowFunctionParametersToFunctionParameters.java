package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinitionParameter;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TypeScriptArrowFunctionParametersToFunctionParameters {


    public static Optional<FunctionDefinitionParameters> transformArrowFunctionParametersToFunctionParameters(TypeScriptParser.ArrowFunctionParametersContext ctx, ParserRuleContext root) {

        if (ctx == null) {
            return Optional.empty();
        }

        List<FunctionDefinitionParameter> parameters = new ArrayList<>();

        if (ctx.Identifier() != null) {
            Optional<AstString> argumentOptional = Optional.of(new AstString(ctx.Identifier().getText(), ctx.Identifier().getSymbol(), root));
            if (argumentOptional.isPresent()) {
                parameters.add(new FunctionDefinitionParameter(argumentOptional.get(), null, null, ctx, root));
            }
        }

        if (ctx.formalParameterList() != null) {
            return TypeScriptFormalParametersToFunctionParameters.transformFormalParametersToFunctionParameters(ctx.formalParameterList(), root);
        }

        return Optional.of(new FunctionDefinitionParameters(parameters, ctx, root));
    }
}
