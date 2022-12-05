package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinitionParameter;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptAssignableToAstElement.transformAssignableToAstString;

public class JavaScriptFormalParametersToFunctionParameters {


    public static Optional<FunctionDefinitionParameters> transformFormalParametersToFunctionParameters(JavaScriptParser.FormalParameterListContext ctx, ParserRuleContext root) {

        if (ctx == null) {
            return Optional.empty();
        }

        List<FunctionDefinitionParameter> parameters = new ArrayList<>();

        if (ctx.formalParameterArg() != null) {
            for (JavaScriptParser.FormalParameterArgContext formalParameterArgContext : ctx.formalParameterArg()) {
                Optional<AstString> parameter = transformAssignableToAstString(formalParameterArgContext.assignable(), root);
                if (parameter.isPresent()) {
                    parameters.add(new FunctionDefinitionParameter(parameter.get(), null, null, formalParameterArgContext, root));
                }
            }
        }

        return Optional.of(new FunctionDefinitionParameters(parameters, ctx, root));
    }
}
