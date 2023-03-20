package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.analyzer.ast.languages.typescript.TypeScriptAnalyzer;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Sequence;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TypeScriptHtmlElementsTransformation {
    private static final Logger logger = LoggerFactory.getLogger(TypeScriptAnalyzer.class);

    public static Optional<Sequence> transformTypeScriptHtmlElements(TypeScriptParser.HtmlElementsContext ctx, ParserRuleContext root) {
        if (ctx == null || ctx.htmlElement() == null) {
            return Optional.empty();
        }

        List<AstElement> astElementList = new ArrayList<>();
        for (var el : ctx.htmlElement()) {
            var htmlElementOptional = TypeScriptHtmlElementTransformation.transformTypeScriptHtmlElement(el, root);
            if (htmlElementOptional.isPresent()) {
                astElementList.add(htmlElementOptional.get());
            }
        }

        return Optional.of(new Sequence(astElementList, ctx, root));

    }
}
