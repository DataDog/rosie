package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptArrayLiteralToArray.transformArrayLiteralToArray;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptIdentifierToAstElement.transformIdentifierToAstElement;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptIdentifierToAstElement.transformIdentifierToAstString;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptObjectLiteralToObject.transformJavaScriptObjectLiteralToObject;

public class JavaScriptAssignableToAstElement {


    public static Optional<AstElement> transformAssignableToAstElement(JavaScriptParser.AssignableContext ctx, ParserRuleContext root) {
        if (ctx.identifier() != null) {
            return transformIdentifierToAstElement(ctx.identifier(), root);
        }
        if (ctx.arrayLiteral() != null) {
            return transformArrayLiteralToArray(ctx.arrayLiteral(), root);
        }
        if (ctx.objectLiteral() != null) {
            return transformJavaScriptObjectLiteralToObject(ctx.objectLiteral(), root);
        }
        return Optional.empty();
    }


    public static Optional<AstString> transformAssignableToAstString(JavaScriptParser.AssignableContext ctx, ParserRuleContext root) {
        if (ctx.identifier() != null) {
            return transformIdentifierToAstString(ctx.identifier(), root);
        }

        return Optional.empty();
    }
}
