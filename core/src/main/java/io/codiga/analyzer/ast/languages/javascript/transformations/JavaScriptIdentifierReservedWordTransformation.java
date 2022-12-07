package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

public class JavaScriptIdentifierReservedWordTransformation {


    public static Optional<AstString> transformReservedWordToAstString(JavaScriptParser.ReservedWordContext ctx, ParserRuleContext root) {
        if (ctx != null) {
            return Optional.of(new AstString(ctx.getText(), ctx, root));
        }
        return Optional.empty();
    }

}
