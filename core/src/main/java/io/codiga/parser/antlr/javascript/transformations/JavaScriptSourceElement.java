package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

public class JavaScriptSourceElement {


    public static Optional<AstElement> transformSourceElement(JavaScriptParser.SourceElementContext ctx, ParserRuleContext root) {

        if (ctx == null) {
            return Optional.empty();
        }
        if (ctx.statement() != null) {
            return JavaScriptStatementToAstElement.transformStatement(ctx.statement(), root);
        }
        return Optional.empty();
    }
}
