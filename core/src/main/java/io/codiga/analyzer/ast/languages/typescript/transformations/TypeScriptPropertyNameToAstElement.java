package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;


public class TypeScriptPropertyNameToAstElement {


    public static Optional<AstElement> transformPropertyNameToAstElement(TypeScriptParser.PropertyNameContext propertyAssignmentContext, ParserRuleContext root) {
        if (propertyAssignmentContext.identifierName() != null) {
            return Optional.of(new AstString(propertyAssignmentContext.identifierName().getText(), propertyAssignmentContext.identifierName(), root));
        }
        if (propertyAssignmentContext.StringLiteral() != null) {
            return Optional.of(new AstString(propertyAssignmentContext.StringLiteral().getText(), propertyAssignmentContext, root));
        }
        if (propertyAssignmentContext.numericLiteral() != null) {
            return Optional.of(new AstString(propertyAssignmentContext.numericLiteral().getText(), propertyAssignmentContext.numericLiteral(), root));
        }

        return Optional.empty();
    }
}
