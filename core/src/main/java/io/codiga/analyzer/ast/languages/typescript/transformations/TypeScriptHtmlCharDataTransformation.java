package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.analyzer.ast.languages.python.transformations.ClassOrFuncDefTransformation;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.javascript.JavaScriptHtmlData;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class TypeScriptHtmlCharDataTransformation {
    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefTransformation.class);

    public static Optional<JavaScriptHtmlData> transformTypescriptHtmlCharData(TypeScriptParser.HtmlChardataContext ctx, ParserRuleContext root) {
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
