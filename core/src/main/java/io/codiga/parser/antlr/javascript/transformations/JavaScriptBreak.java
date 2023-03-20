package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.Break;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

public class JavaScriptBreak {


    public static Optional<Break> transformBreak(JavaScriptParser.BreakStatementContext ctx, ParserRuleContext root) {

        if (ctx == null) {
            return Optional.empty();
        }

        return Optional.of(new Break(ctx, root));
    }

}
