package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.Block;
import io.codiga.model.ast.javascript.JavaScriptCatchStatement;
import io.codiga.model.ast.javascript.JavaScriptTryCatchStatement;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;
import java.util.Optional;

public class TypeScriptTryStatement {


    public static Optional<JavaScriptTryCatchStatement> transformTryStatementToTryCatchStatement(TypeScriptParser.TryStatementContext ctx, ParserRuleContext root) {
        Optional<AstString> exceptionName = Optional.empty();
        Optional<Block> tryBlock = Optional.empty();
        Optional<Block> finallyBlock = Optional.empty();


        if (ctx == null) {
            return Optional.empty();
        }
        if (ctx.catchProduction() != null && ctx.catchProduction().Identifier() != null) {
            exceptionName = Optional.of(new AstString(ctx.catchProduction().Identifier().getText(), ctx.catchProduction().Identifier().getSymbol(), root));
            if (exceptionName.isPresent()) {
                return Optional.of(
                    new JavaScriptTryCatchStatement(
                        tryBlock.orElse(null),
                        new JavaScriptCatchStatement(List.of(), exceptionName.get(), ctx.catchProduction(), root),
                        finallyBlock.orElse(null),
                        ctx,
                        root
                    )
                );
            }
        }
        return Optional.empty();
    }
}
