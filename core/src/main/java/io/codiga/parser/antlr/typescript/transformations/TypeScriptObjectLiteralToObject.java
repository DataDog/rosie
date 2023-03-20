package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.javascript.JavaScriptObject;
import io.codiga.model.ast.javascript.JavaScriptObjectElement;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.Optional;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptIdentifierOrKeywordTransformation.transformIdentifierNameToAstString;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptPropertyNameToAstElement.transformPropertyNameToAstElement;


public class TypeScriptObjectLiteralToObject {


    public static Optional<JavaScriptObjectElement> transformTypeScriptPropertyAsssignmentToObjectElement(TypeScriptParser.PropertyAssignmentContext propertyAssignmentContext, ParserRuleContext root) {
        if (propertyAssignmentContext instanceof TypeScriptParser.PropertyExpressionAssignmentContext) {
            TypeScriptParser.PropertyExpressionAssignmentContext propertyExpressionAssignmentContext = (TypeScriptParser.PropertyExpressionAssignmentContext) propertyAssignmentContext;
            Optional<AstElement> name = transformPropertyNameToAstElement(propertyExpressionAssignmentContext.propertyName(), root);
            Optional<AstElement> value = TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement(propertyExpressionAssignmentContext.singleExpression(), root);

            if (name.isPresent() && value.isPresent()) {
                return Optional.of(new JavaScriptObjectElement(name.get(), value.get(), propertyAssignmentContext, root));
            }

        }

        if (propertyAssignmentContext instanceof TypeScriptParser.PropertyShorthandContext) {
            TypeScriptParser.PropertyShorthandContext propertyShorthandContext = (TypeScriptParser.PropertyShorthandContext) propertyAssignmentContext;
            Optional<AstString> v = transformIdentifierNameToAstString(propertyShorthandContext.identifierOrKeyWord(), root);
            if (v.isPresent()) {
                return Optional.of(new JavaScriptObjectElement(null, v.get(), propertyAssignmentContext, root));
            }

        }

        if (propertyAssignmentContext instanceof TypeScriptParser.RestParameterInObjectContext restParameterInObjectContext) {
            if (restParameterInObjectContext.restParameter() != null && restParameterInObjectContext.restParameter().singleExpression() != null) {
                Optional<AstElement> astElementOptional = TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement(restParameterInObjectContext.restParameter().singleExpression(),
                    restParameterInObjectContext.restParameter().Ellipsis() != null,
                    root);

                if (astElementOptional.isPresent()) {
                    return Optional.of(
                        new JavaScriptObjectElement(null, astElementOptional.get(), restParameterInObjectContext.restParameter().singleExpression(), root)
                    );
                }
            }
        }
        return Optional.empty();
    }


    public static Optional<AstElement> transformTypeScriptObjectLiteralToObject(TypeScriptParser.ObjectLiteralContext ctx, ParserRuleContext root) {
        ArrayList<JavaScriptObjectElement> objectElements = new ArrayList<>();

        for (TypeScriptParser.PropertyAssignmentContext property : ctx.propertyAssignment()) {
            Optional<JavaScriptObjectElement> el = transformTypeScriptPropertyAsssignmentToObjectElement(property, root);
            if (el.isPresent()) {
                objectElements.add(el.get());
            }
        }
        return Optional.of(new JavaScriptObject(objectElements, ctx, root));
    }
}
