package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.analyzer.ast.languages.python.transformations.ClassOrFuncDefToClassDefinition;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.javascript.JavaScriptHtmlAttribute;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptHtmlAttributeTransformation.transformJavaScriptHtmlAttribute;

public class JavaScriptHtmlElementTransformation {
    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefToClassDefinition.class);

    public static Optional<io.codiga.model.ast.javascript.JavaScriptHtmlElement> transformJavaScriptHtmlElement(JavaScriptParser.HtmlElementContext ctx, ParserRuleContext root) {
        Optional<AstElement> tag = Optional.empty();
        List<JavaScriptHtmlAttribute> attributes = new ArrayList<>();
        if (ctx.htmlTagStartName() != null) {
            tag = mapTagName(ctx.htmlTagStartName().htmlTagName(), root);
        }
        if (ctx.htmlTagName() != null) {
            tag = mapTagName(ctx.htmlTagName(), root);
        }


        if (ctx.htmlAttribute() != null) {
            ctx.htmlAttribute().forEach(a -> {
                Optional<JavaScriptHtmlAttribute> attribute = transformJavaScriptHtmlAttribute(a, root);
                if (attribute.isPresent()) {
                    attributes.add(attribute.get());
                }
            });
        }

        if (tag.isPresent()) {
            return Optional.of(
                new io.codiga.model.ast.javascript.JavaScriptHtmlElement(tag.get(), attributes, ctx, root)
            );
        }


        return Optional.empty();
    }

    public static Optional<AstElement> mapTagName(JavaScriptParser.HtmlTagNameContext context, ParserRuleContext root) {
        if (context == null) {
            return Optional.empty();
        }
//        if (context.Identifier() != null) {
//            logger.info("identifier");
//            logger.info(context.Identifier().getText());
//
//        }
//        if (context.TagName() != null) {
//            logger.info("tag");
//            logger.info(context.TagName().getText());
//        }
        return Optional.of(new AstString(context.getText(), context, root));
//        if (context.TagName() != null) {
//            return Optional.of(new AstString(context.getText(), context.TagName().getSymbol(), root));
//        }
//        if (context.keyword() != null) {
//            return Optional.of(new AstString(context.().getText(), context.keyword(), root));
//        }
//        if (context.Identifier() != null) {
//            return Optional.of(new AstString(context.Identifier().getText(), context.Identifier().getSymbol(), root));
//        }
//        return Optional.empty();
    }
}
