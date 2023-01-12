package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptStatementToAstElement.transformStatement;

public class JavaScriptSourceElement {


    public static Optional<AstElement> transformSourceElement(JavaScriptParser.SourceElementContext ctx, ParserRuleContext root) {

        if (ctx == null) {
            return Optional.empty();
        }
        if (ctx.statement() != null) {
            return transformStatement(ctx.statement(), root);
        }
        return Optional.empty();
    }
}
