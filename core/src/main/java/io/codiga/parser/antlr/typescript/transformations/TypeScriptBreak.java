package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.Break;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

public class TypeScriptBreak {


    public static Optional<Break> transformBreak(TypeScriptParser.BreakStatementContext ctx, ParserRuleContext root) {

        if (ctx == null) {
            return Optional.empty();
        }

        return Optional.of(new Break(ctx, root));
    }

}
