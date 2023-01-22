package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.analyzer.ast.languages.python.transformations.ClassOrFuncDefTransformation;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Sequence;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptHtmlElementTransformation.transformJavaScriptHtmlElement;

public class JavaScriptHtmlElementsTransformation {
    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefTransformation.class);

    public static Optional<AstElement> transformJavaScriptHtmlElements(JavaScriptParser.HtmlElementsContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        List<AstElement> elements = new ArrayList<>();

        for (var el : ctx.htmlElement()) {
            var transformed = transformJavaScriptHtmlElement(el, root);
            if (transformed.isPresent()) {
                elements.add(transformed.get());
            }
        }

        return Optional.of(new Sequence(elements, ctx, root));

    }

}
