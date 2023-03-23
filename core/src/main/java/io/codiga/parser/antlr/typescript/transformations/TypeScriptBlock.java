package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptStatementList.transformStatementListToSequence;
import static io.codiga.utils.Conversions.convertToAstElement;

public class TypeScriptBlock {

    public static Optional<AstElement> transformBlock(TypeScriptParser.BlockContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        if (ctx.statementList() != null) {
            return convertToAstElement(transformStatementListToSequence(ctx.statementList(), root));
        }
        return Optional.empty();
    }
}
