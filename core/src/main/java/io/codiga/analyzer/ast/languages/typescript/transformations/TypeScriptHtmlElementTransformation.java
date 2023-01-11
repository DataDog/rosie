package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.analyzer.ast.languages.typescript.TypeScriptAnalyzer;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.javascript.JavaScriptHtmlAttribute;
import io.codiga.model.ast.javascript.JavaScriptHtmlTag;
import io.codiga.model.common.Position;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.AstUtils.getEndPosition;
import static io.codiga.analyzer.ast.AstUtils.getStartPosition;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptExpressionSequence.transformExpressionSequenceToSequence;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptHtmlAttributeTransformation.transformTypeScriptHtmlAttribute;

public class TypeScriptHtmlElementTransformation {
    private static final Logger logger = LoggerFactory.getLogger(TypeScriptAnalyzer.class);

    public static Optional<Position> getOpeningTagStartPosition(TypeScriptParser.HtmlElementContext ctx) {
        if (ctx.LessThan() != null && ctx.LessThan().size() > 0) {
            return Optional.of(getStartPosition(ctx.LessThan().get(0).getSymbol()));
        }

        return Optional.empty();
    }

    public static Optional<Position> getOpeningTagEndPosition(TypeScriptParser.HtmlElementContext ctx) {
        if (ctx.htmlTagStartName() != null && ctx.MoreThan() != null && ctx.MoreThan().size() == 2) {
            return Optional.of(getEndPosition(ctx.MoreThan().get(0).getSymbol()));
        }
        if (ctx.htmlTagName() != null) {
            return Optional.of(getEndPosition(ctx.htmlTagName()));
        }

        if (ctx.htmlTagName() == null && ctx.htmlTagStartName() == null && ctx.LessThan() != null && ctx.MoreThan() != null && ctx.LessThan().size() > 0 && ctx.MoreThan().size() > 0) {
            return Optional.of(getEndPosition(ctx.MoreThan().get(0).getSymbol()));
        }

        return Optional.empty();
    }


    public static Optional<Position> getClosingTagStartPosition(TypeScriptParser.HtmlElementContext ctx) {
        if (ctx.htmlTagStartName() != null) {
            if (ctx.htmlTagClosingName() != null && ctx.LessThan() != null && ctx.LessThan().size() == 2) {
                return Optional.of(getStartPosition(ctx.LessThan().get(1).getSymbol()));
            }
        }

        if (ctx.htmlTagStartName() == null && ctx.htmlTagName() != null) {
            if (ctx.Divide() != null) {
                return Optional.of(getStartPosition(ctx.Divide().getSymbol()));
            }
        }

        if (ctx.htmlTagName() == null && ctx.htmlTagStartName() == null) {
            if (ctx.LessThan() != null && ctx.LessThan().size() == 2) {
                return Optional.of(getStartPosition(ctx.LessThan().get(1).getSymbol()));
            }
        }

        return Optional.empty();
    }

    public static Optional<Position> getClosingTagEndPosition(TypeScriptParser.HtmlElementContext ctx) {
        if (ctx.htmlTagStartName() != null && ctx.MoreThan() != null && ctx.MoreThan().size() == 2) {
            return Optional.of(getEndPosition(ctx.MoreThan().get(1).getSymbol()));
        }

        if (ctx.htmlTagStartName() == null && ctx.htmlTagName() == null && ctx.MoreThan() != null && ctx.MoreThan().size() == 1) {
            return Optional.of(getEndPosition(ctx.MoreThan().get(0).getSymbol()));
        }

        if (ctx.htmlTagStartName() == null && ctx.htmlTagName() != null && ctx.MoreThan() != null && ctx.MoreThan().size() == 1) {
            return Optional.of(getEndPosition(ctx.MoreThan().get(0).getSymbol()));
        }

        return Optional.empty();
    }

    public static List<AstElement> getContent(TypeScriptParser.HtmlElementContext ctx, ParserRuleContext root) {
        if (ctx == null || ctx.htmlContent() == null) {
            return List.of();
        }

        List<AstElement> elementList = new ArrayList<>();


        TypeScriptParser.HtmlContentContext contentContext = ctx.htmlContent();

        if (contentContext.htmlElement() != null && contentContext.htmlElement().size() > 0) {
            for (var htmlElement : contentContext.htmlElement()) {
                var object = transformTypeScriptHtmlElement(htmlElement, root);
                if (object.isPresent()) {
                    elementList.add(object.get());
                }
            }
        }

        if (contentContext.objectExpressionSequence() != null && contentContext.objectExpressionSequence().size() > 0) {
            for (var objectExpression : contentContext.objectExpressionSequence()) {
                if (objectExpression.expressionSequence() != null) {
                    var expressionSequence = transformExpressionSequenceToSequence(objectExpression.expressionSequence(), root);
                    if (expressionSequence.isPresent() && expressionSequence.get().elements != null && expressionSequence.get().elements.length > 0) {
                        elementList.addAll(Arrays.stream(expressionSequence.get().elements).toList());
                    }
                }
            }
        }


        return elementList;
    }

    public static Optional<io.codiga.model.ast.javascript.JavaScriptHtmlElement> transformTypeScriptHtmlElement(TypeScriptParser.HtmlElementContext ctx, ParserRuleContext root) {
        Optional<AstString> tag = Optional.empty();
        Optional<JavaScriptHtmlTag> openingTag = Optional.empty();
        Optional<JavaScriptHtmlTag> closingTag = Optional.empty();
        List<JavaScriptHtmlAttribute> attributes = new ArrayList<>();


        if (ctx.htmlTagStartName() != null && ctx.htmlTagStartName().htmlTagName() != null) {
            tag = Optional.of(new AstString(ctx.htmlTagStartName().htmlTagName().getText(), ctx.htmlTagStartName().htmlTagName(), root));
        }
        if (ctx.htmlTagName() != null) {
            tag = Optional.of(new AstString(ctx.htmlTagName().getText(), ctx.htmlTagName(), root));
        }
        openingTag = Optional.of(new JavaScriptHtmlTag(tag.orElse(null), getOpeningTagStartPosition(ctx).orElse(null), getOpeningTagEndPosition(ctx).orElse(null), ctx, root));

        // closing tag
        Optional<AstString> closingTagName = Optional.empty();
        if (ctx != null && ctx.htmlTagClosingName() != null && ctx.htmlTagClosingName().htmlTagName() != null) {
            closingTagName = Optional.of(new AstString(ctx.htmlTagClosingName().htmlTagName().getText(), ctx.htmlTagClosingName().htmlTagName(), root));
        }
        closingTag = Optional.of(new JavaScriptHtmlTag(closingTagName.orElse(null), getClosingTagStartPosition(ctx).orElse(null), getClosingTagEndPosition(ctx).orElse(null), ctx, root));


        if (ctx.htmlAttribute() != null) {
            ctx.htmlAttribute().forEach(a -> {
                Optional<JavaScriptHtmlAttribute> attribute = transformTypeScriptHtmlAttribute(a, root);
                if (attribute.isPresent()) {
                    attributes.add(attribute.get());
                }
            });
        }


        return Optional.of(
            new io.codiga.model.ast.javascript.JavaScriptHtmlElement(tag.orElse(null), openingTag.orElse(null), closingTag.orElse(null), attributes, getContent(ctx, root), ctx, root)
        );

    }
}
