package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstArray;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;

public class JavaScriptArrayLiteralToArray {


    public static Optional<AstElement> transformArrayLiteralToArray(JavaScriptParser.ArrayLiteralContext ctx, ParserRuleContext root) {
        ArrayList<AstElement> arrayElements = new ArrayList<>();

        if (ctx.elementList() != null && ctx.elementList().arrayElement() != null) {
            for (JavaScriptParser.ArrayElementContext element : ctx.elementList().arrayElement()) {
                if (element.singleExpression() != null) {
                    Optional<AstElement> arrayElement = transformSingleExpressionToAstElement(element.singleExpression(), element.Ellipsis() != null, root);
                    if (arrayElement.isPresent()) {
                        arrayElements.add(arrayElement.get());
                    }
                }

            }
        }
        return Optional.of(new AstArray(arrayElements, ctx, root));
    }
}
