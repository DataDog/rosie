package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;


public class TypeScriptIdentifierOrKeywordTransformation {


    public static Optional<AstString> transformIdentifierNameToAstString(TypeScriptParser.IdentifierOrKeyWordContext ctx, ParserRuleContext root) {
        if (ctx.Identifier() != null) {
            return Optional.of(new AstString(ctx.Identifier().getText(), ctx, root));
        }

        return Optional.empty();
    }
}
