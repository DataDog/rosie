package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptStatementList.transformStatementListToSequence;
import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;

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
