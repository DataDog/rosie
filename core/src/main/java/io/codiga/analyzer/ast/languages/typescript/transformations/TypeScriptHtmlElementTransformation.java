package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.javascript.JavaScriptHtmlAttribute;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptHtmlAttributeTransformation.transformTypeScriptHtmlAttribute;

public class TypeScriptHtmlElementTransformation {


    public static Optional<io.codiga.model.ast.javascript.JavaScriptHtmlElement> transformTypeScriptHtmlElement(TypeScriptParser.HtmlElementContext ctx, ParserRuleContext root) {
        Optional<AstString> tag = Optional.empty();
        List<JavaScriptHtmlAttribute> attributes = new ArrayList<>();
        if (ctx.htmlTagStartName() != null) {
            tag = Optional.of(new AstString(ctx.htmlTagStartName().htmlTagName().getText(), ctx.htmlTagStartName().htmlTagName(), root));
        }
        if (ctx.htmlTagName() != null) {
            tag = Optional.of(new AstString(ctx.htmlTagName().getText(), ctx.htmlTagName(), root));
        }


        if (ctx.htmlAttribute() != null) {
            ctx.htmlAttribute().forEach(a -> {
                Optional<JavaScriptHtmlAttribute> attribute = transformTypeScriptHtmlAttribute(a, root);
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
}
