package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.javascript.JavaScriptHtmlData;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import io.codiga.parser.antlr.python.transformations.ClassOrFuncDefTransformation;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class JavaScriptHtmlCharDataTransformation {
    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefTransformation.class);

    public static Optional<JavaScriptHtmlData> transformJavaScriptHtmlCharData(JavaScriptParser.HtmlChardataContext ctx, ParserRuleContext root) {
        if (ctx.getText() != null) {
            return Optional.of(new JavaScriptHtmlData(
                new AstString(ctx.getText(), ctx, root),
                ctx,
                root
            ));
        }

        return Optional.empty();
    }


}
