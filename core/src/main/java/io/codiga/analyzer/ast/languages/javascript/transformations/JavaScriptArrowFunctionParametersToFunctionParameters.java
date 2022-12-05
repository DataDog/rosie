package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinitionParameter;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptFormalParametersToFunctionParameters.transformFormalParametersToFunctionParameters;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptIdentifierToAstElement.transformIdentifierToAstString;

public class JavaScriptArrowFunctionParametersToFunctionParameters {


    public static Optional<FunctionDefinitionParameters> transformArrowFunctionParametersToFunctionParameters(JavaScriptParser.ArrowFunctionParametersContext ctx, ParserRuleContext root) {

        if (ctx == null) {
            return Optional.empty();
        }

        List<FunctionDefinitionParameter> parameters = new ArrayList<>();

        if (ctx.identifier() != null) {
            Optional<AstString> argumentOptional = transformIdentifierToAstString(ctx.identifier(), root);
            if (argumentOptional.isPresent()) {
                parameters.add(new FunctionDefinitionParameter(argumentOptional.get(), null, null, ctx.identifier(), root));
            }
        }

        if (ctx.formalParameterList() != null) {
            return transformFormalParametersToFunctionParameters(ctx.formalParameterList(), root);
        }

        return Optional.of(new FunctionDefinitionParameters(parameters, ctx, root));
    }
}
