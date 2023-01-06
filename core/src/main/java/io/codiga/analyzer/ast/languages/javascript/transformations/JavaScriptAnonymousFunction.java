package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.FunctionDefinition;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import io.codiga.model.ast.javascript.JavaScriptFunctionExpression;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptArrowFunctionParametersToFunctionParameters.transformArrowFunctionParametersToFunctionParameters;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptFormalParametersToFunctionParameters.transformFormalParametersToFunctionParameters;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptFunctionDeclarationToFunctionDefinition.transformFunctionDeclarationToFunctionDefinition;

public class JavaScriptAnonymousFunction {


    public static Optional<FunctionDefinition> transformAnonymousFunction(JavaScriptParser.AnoymousFunctionContext ctx, ParserRuleContext root) {
        if (ctx instanceof JavaScriptParser.FunctionDeclContext declaration) {
            if (declaration.functionDeclaration() != null) {
                return transformFunctionDeclarationToFunctionDefinition(declaration.functionDeclaration(), root);
            }
        }

        if (ctx instanceof JavaScriptParser.AnoymousFunctionDeclContext declaration) {

            return Optional.of(new JavaScriptFunctionExpression(null, transformFormalParametersToFunctionParameters(declaration.formalParameterList(), root).orElse(null), declaration, root));

        }

        if (ctx instanceof JavaScriptParser.ArrowFunctionContext declaration) {
            Optional<FunctionDefinitionParameters> parameters = transformArrowFunctionParametersToFunctionParameters(declaration.arrowFunctionParameters(), root);
            return Optional.of(new JavaScriptFunctionExpression(null, parameters.orElse(null), declaration, root));
        }

        return Optional.empty();
    }
}
