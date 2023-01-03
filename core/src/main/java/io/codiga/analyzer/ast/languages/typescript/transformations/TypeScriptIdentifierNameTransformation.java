package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.javascript.AstStringWithSpreadOperator;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;


public class TypeScriptIdentifierNameTransformation {

    public static Optional<AstString> transformIdentifierNameToAstString(TypeScriptParser.IdentifierNameContext ctx, ParserRuleContext root) {
        return transformIdentifierNameToAstString(ctx, false, root);
    }

    public static Optional<AstString> transformIdentifierNameToAstString(TypeScriptParser.IdentifierNameContext ctx, boolean isSpread, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        if (ctx.Identifier() != null) {
            return Optional.of(new AstStringWithSpreadOperator(ctx.Identifier().getText(), isSpread, ctx, root));
        }
        if (ctx.reservedWord() != null) {
            return Optional.of(new AstStringWithSpreadOperator(ctx.reservedWord().getText(), isSpread, ctx, root));
        }
        return Optional.empty();

    }


}
