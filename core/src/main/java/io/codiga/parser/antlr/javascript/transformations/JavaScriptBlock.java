package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.parser.antlr.javascript.transformations.JavaScriptStatementListToAstElement.transformStatementList;
import static io.codiga.utils.Conversions.convertToAstElement;


public class JavaScriptBlock {


    public static Optional<AstElement> transformBlockContext(JavaScriptParser.BlockContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }


        if (ctx.statementList() != null) {
            return convertToAstElement(transformStatementList(ctx.statementList(), root));
        }


        return Optional.empty();
    }

}
