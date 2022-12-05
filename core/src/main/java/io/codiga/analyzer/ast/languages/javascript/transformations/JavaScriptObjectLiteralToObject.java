package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.javascript.JavaScriptObject;
import io.codiga.model.ast.javascript.JavaScriptObjectElement;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptPropertyNameToAstElement.transformPropertyNameToAstElement;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;

public class JavaScriptObjectLiteralToObject {


    public static Optional<JavaScriptObjectElement> transformJavaScriptPropertyAsssignmentToObjectElement(JavaScriptParser.PropertyAssignmentContext propertyAssignmentContext, ParserRuleContext root) {
        if (propertyAssignmentContext instanceof JavaScriptParser.PropertyExpressionAssignmentContext) {
            JavaScriptParser.PropertyExpressionAssignmentContext propertyExpressionAssignmentContext = (JavaScriptParser.PropertyExpressionAssignmentContext) propertyAssignmentContext;
            Optional<AstElement> name = transformPropertyNameToAstElement(propertyExpressionAssignmentContext.propertyName(), root);
            Optional<AstElement> value = transformSingleExpressionToAstElement(propertyExpressionAssignmentContext.singleExpression(), root);

            if (name.isPresent() && value.isPresent()) {
                return Optional.of(new JavaScriptObjectElement(name.get(), value.get(), propertyAssignmentContext, root));
            }

        }
        return Optional.empty();
    }


    public static Optional<AstElement> transformJavaScriptObjectLiteralToObject(JavaScriptParser.ObjectLiteralContext ctx, ParserRuleContext root) {
        ArrayList<JavaScriptObjectElement> objectElements = new ArrayList<>();

        for (JavaScriptParser.PropertyAssignmentContext property : ctx.propertyAssignment()) {
            Optional<JavaScriptObjectElement> el = transformJavaScriptPropertyAsssignmentToObjectElement(property, root);
            if (el.isPresent()) {
                objectElements.add(el.get());
            }
        }
        return Optional.of(new JavaScriptObject(objectElements, ctx, root));
    }
}
