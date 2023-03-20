package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.FunctionDefinition;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import io.codiga.model.ast.javascript.JavaScriptFunctionExpression;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.parser.antlr.javascript.transformations.JavaScriptArrowFunctionParametersToFunctionParameters.transformArrowFunctionParametersToFunctionParameters;
import static io.codiga.parser.antlr.javascript.transformations.JavaScriptFunctionBody.transformFunctionBody;

public class JavaScriptAnonymousFunction {


    public static Optional<FunctionDefinition> transformAnonymousFunction(JavaScriptParser.AnoymousFunctionContext ctx, ParserRuleContext root) {
        if (ctx instanceof JavaScriptParser.FunctionDeclContext declaration) {
            if (declaration.functionDeclaration() != null) {
                return JavaScriptFunctionDeclarationToFunctionDefinition.transformFunctionDeclarationToFunctionDefinition(declaration.functionDeclaration(), root);
            }
        }

        if (ctx instanceof JavaScriptParser.AnoymousFunctionDeclContext declaration) {
            Optional<AstElement> functionContent = Optional.empty();
            if (declaration.functionBody() != null) {
                functionContent = transformFunctionBody(declaration.functionBody(), root);
            }

            return Optional.of(new JavaScriptFunctionExpression(declaration.Async() != null, null, JavaScriptFormalParametersToFunctionParameters.transformFormalParametersToFunctionParameters(declaration.formalParameterList(), root).orElse(null), functionContent.orElse(null), declaration, root));

        }

        if (ctx instanceof JavaScriptParser.ArrowFunctionContext declaration) {
            Optional<FunctionDefinitionParameters> parameters = transformArrowFunctionParametersToFunctionParameters(declaration.arrowFunctionParameters(), root);
            Optional<AstElement> functionContent = Optional.empty();

            if (declaration.arrowFunctionBody() != null) {
                functionContent = JavaScriptArrowFunctionBody.transformArrowFunctionBody(declaration.arrowFunctionBody(), root);
            }
            return Optional.of(new JavaScriptFunctionExpression(declaration.Async() != null, null, parameters.orElse(null), functionContent.orElse(null), declaration, root));
        }

        return Optional.empty();
    }
}
