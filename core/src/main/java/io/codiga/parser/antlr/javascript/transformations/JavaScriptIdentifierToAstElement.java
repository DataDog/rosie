package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.parser.antlr.javascript.transformations.JavaScriptIdentifierReservedWordTransformation.transformReservedWordToAstString;

public class JavaScriptIdentifierToAstElement {


    public static Optional<AstElement> transformIdentifierToAstElement(JavaScriptParser.IdentifierContext ctx, ParserRuleContext root) {
        if (ctx != null) {
            return Optional.of(new AstString(ctx.getText(), ctx, root));
        }
        return Optional.empty();

    }

    public static Optional<AstString> transformIdentifierToAstString(JavaScriptParser.IdentifierContext ctx, ParserRuleContext root) {
        if (ctx != null) {
            return Optional.of(new AstString(ctx.getText(), ctx, root));
        }
        return Optional.empty();
    }

    public static Optional<AstString> transformIdentifierNameToAstString(JavaScriptParser.IdentifierNameContext ctx, ParserRuleContext root) {
        if (ctx != null && ctx.identifier() != null) {
            return transformIdentifierToAstString(ctx.identifier(), root);
        }
        if (ctx != null && ctx.reservedWord() != null) {
            return transformReservedWordToAstString(ctx.reservedWord(), root);
        }
        return Optional.empty();
    }
}
