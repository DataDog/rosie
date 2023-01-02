package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinitionParameter;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIdentifierOrKeywordTransformation.transformIdentifierNameToAstString;


public class TypeScriptFormalParametersToFunctionParameters {


    public static Optional<FunctionDefinitionParameters> transformFormalParametersToFunctionParameters(TypeScriptParser.FormalParameterListContext ctx, ParserRuleContext root) {

        if (ctx == null) {
            return Optional.empty();
        }

        List<FunctionDefinitionParameter> parameters = new ArrayList<>();

        if (ctx.formalParameterArg() != null) {
            for (TypeScriptParser.FormalParameterArgContext formalParameterArgContext : ctx.formalParameterArg()) {
                Optional<AstString> parameter = transformIdentifierNameToAstString(formalParameterArgContext.identifierOrKeyWord(), root);
                if (parameter.isPresent()) {
                    parameters.add(new FunctionDefinitionParameter(parameter.get(), null, null, formalParameterArgContext, root));
                }
            }
        }

        return Optional.of(new FunctionDefinitionParameters(parameters, ctx, root));
    }
}
