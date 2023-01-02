package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIdentifierNameTransformation.transformIdentifierNameToAstString;


public class TypeScriptIdentifierOrPatternTransformation {


    public static Optional<AstString> transformIdentifierNameOrPatternToAstString(TypeScriptParser.IdentifierOrPatternContext ctx, ParserRuleContext root) {
        if (ctx != null && ctx.identifierName() != null) {
            return transformIdentifierNameToAstString(ctx.identifierName(), root);
        }

        return Optional.empty();
    }
}
