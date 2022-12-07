package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.Block;
import io.codiga.model.ast.javascript.JavaScriptCatchStatement;
import io.codiga.model.ast.javascript.JavaScriptTryCatchStatement;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptAssignableToAstElement.transformAssignableToAstString;

public class JavaScriptTryStatement {


    public static Optional<JavaScriptTryCatchStatement> transformTryStatementToTryCatchStatement(JavaScriptParser.TryStatementContext ctx, ParserRuleContext root) {
        Optional<AstString> exceptionName = Optional.empty();
        Optional<Block> tryBlock = Optional.empty();
        Optional<Block> finallyBlock = Optional.empty();


        if (ctx == null) {
            return Optional.empty();
        }
        if (ctx.catchProduction() != null && ctx.catchProduction().assignable() != null) {
            exceptionName = transformAssignableToAstString(ctx.catchProduction().assignable(), root);
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
