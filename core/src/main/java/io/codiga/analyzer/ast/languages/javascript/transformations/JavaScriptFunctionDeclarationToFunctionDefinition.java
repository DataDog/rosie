package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinition;
import io.codiga.model.ast.common.FunctionDefinitionParameter;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptAssignableToAstElement.transformAssignableToAstString;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptIdentifierToAstElement.transformIdentifierToAstString;

public class JavaScriptFunctionDeclarationToFunctionDefinition {


    public static Optional<FunctionDefinition> transformFunctionDeclarationToFunctionDefinition(JavaScriptParser.FunctionDeclarationContext ctx, ParserRuleContext root) {
        Optional<AstString> identifier = transformIdentifierToAstString(ctx.identifier(), root);
        FunctionDefinitionParameters functionDefinitionParameters = null;

        if (ctx.formalParameterList() != null) {
            List<FunctionDefinitionParameter> parameters = new ArrayList<>();

            if (ctx.formalParameterList().formalParameterArg() != null) {
                for (JavaScriptParser.FormalParameterArgContext formalParameterArgContext : ctx.formalParameterList().formalParameterArg()) {
                    Optional<AstString> parameter = transformAssignableToAstString(formalParameterArgContext.assignable(), root);
                    if (parameter.isPresent()) {
                        parameters.add(new FunctionDefinitionParameter(parameter.get(), null, null, formalParameterArgContext, root));
                    }
                }
            }

            functionDefinitionParameters = new FunctionDefinitionParameters(parameters, ctx.formalParameterList(), root);
        }

        return Optional.of(new FunctionDefinition(identifier.orElse(null), functionDefinitionParameters, ctx, root));
    }
}
