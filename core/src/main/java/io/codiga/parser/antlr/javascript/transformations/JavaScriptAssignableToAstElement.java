package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.parser.antlr.javascript.transformations.JavaScriptArrayLiteralToArray.transformArrayLiteralToArray;

public class JavaScriptAssignableToAstElement {


    public static Optional<AstElement> transformAssignableToAstElement(JavaScriptParser.AssignableContext ctx, ParserRuleContext root) {
        if (ctx.identifier() != null) {
            return JavaScriptIdentifierToAstElement.transformIdentifierToAstElement(ctx.identifier(), root);
        }
        if (ctx.arrayLiteral() != null) {
            return transformArrayLiteralToArray(ctx.arrayLiteral(), root);
        }
        if (ctx.objectLiteral() != null) {
            return JavaScriptObjectLiteralToObject.transformJavaScriptObjectLiteralToObject(ctx.objectLiteral(), root);
        }
        return Optional.empty();
    }


    public static Optional<AstString> transformAssignableToAstString(JavaScriptParser.AssignableContext ctx, ParserRuleContext root) {
        if (ctx.identifier() != null) {
            return JavaScriptIdentifierToAstElement.transformIdentifierToAstString(ctx.identifier(), root);
        }

        return Optional.empty();
    }
}
