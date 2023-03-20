package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;


public class TypeScriptIdentifierOrKeywordTransformation {


    public static Optional<AstString> transformIdentifierNameToAstString(TypeScriptParser.IdentifierOrKeyWordContext ctx, ParserRuleContext root) {
        if (ctx != null && ctx.Identifier() != null) {
            return Optional.of(new AstString(ctx.Identifier().getText(), ctx, root));
        }

        return Optional.empty();
    }
}
