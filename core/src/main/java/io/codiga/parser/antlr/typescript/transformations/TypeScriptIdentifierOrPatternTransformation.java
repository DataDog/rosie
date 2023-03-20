package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;


public class TypeScriptIdentifierOrPatternTransformation {


    public static Optional<AstString> transformIdentifierNameOrPatternToAstString(TypeScriptParser.IdentifierOrPatternContext ctx, ParserRuleContext root) {
        if (ctx != null && ctx.identifierName() != null) {
            return TypeScriptIdentifierNameTransformation.transformIdentifierNameToAstString(ctx.identifierName(), root);
        }

        return Optional.empty();
    }
}
