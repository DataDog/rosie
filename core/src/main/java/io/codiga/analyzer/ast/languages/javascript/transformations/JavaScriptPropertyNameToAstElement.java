package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;

public class JavaScriptPropertyNameToAstElement {


    public static Optional<AstElement> transformPropertyNameToAstElement(JavaScriptParser.PropertyNameContext propertyAssignmentContext, ParserRuleContext root) {
        if (propertyAssignmentContext.identifierName() != null) {
            return Optional.of(new AstString(propertyAssignmentContext.identifierName().getText(), propertyAssignmentContext.identifierName(), root));
        }
        if (propertyAssignmentContext.StringLiteral() != null) {
            return Optional.of(new AstString(propertyAssignmentContext.StringLiteral().getText(), propertyAssignmentContext, root));
        }
        if (propertyAssignmentContext.numericLiteral() != null) {
            return Optional.of(new AstString(propertyAssignmentContext.numericLiteral().getText(), propertyAssignmentContext.numericLiteral(), root));
        }
        if (propertyAssignmentContext.singleExpression() != null) {
            return transformSingleExpressionToAstElement(propertyAssignmentContext.singleExpression(), root);
        }
        return Optional.empty();
    }
}
