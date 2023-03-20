package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Sequence;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TypeScriptStatementList {


    public static Optional<Sequence> transformStatementListToSequence(TypeScriptParser.StatementListContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        List<AstElement> astElementList = new ArrayList<>();
        if (ctx.statement() != null) {
            for (var s : ctx.statement()) {
                var so = TypeScriptStatement.transformStatementToAstElement(s, root);
                if (so.isPresent()) {
                    astElementList.add(so.get());
                }
            }
        }
        return Optional.of(new Sequence(astElementList, ctx, root));
    }
}
