package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.javascript.JavaScriptHtmlAttribute;
import io.codiga.model.ast.javascript.JavaScriptHtmlTag;
import io.codiga.model.common.Position;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import io.codiga.parser.antlr.python.transformations.ClassOrFuncDefTransformation;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.AstUtils.getEndPosition;
import static io.codiga.analyzer.ast.AstUtils.getStartPosition;

public class JavaScriptHtmlElementTransformation {
    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefTransformation.class);


    public static Optional<Position> getOpeningTagStartPosition(JavaScriptParser.HtmlElementContext ctx) {
        if (ctx.LessThan() != null && ctx.LessThan().size() > 0) {
            return Optional.of(getStartPosition(ctx.LessThan().get(0).getSymbol()));
        }

        return Optional.empty();
    }

    public static Optional<Position> getOpeningTagEndPosition(JavaScriptParser.HtmlElementContext ctx) {
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


    public static Optional<Position> getClosingTagStartPosition(JavaScriptParser.HtmlElementContext ctx) {
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

    public static Optional<Position> getClosingTagEndPosition(JavaScriptParser.HtmlElementContext ctx) {
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


    public static List<AstElement> getContent(JavaScriptParser.HtmlElementContext ctx, ParserRuleContext root) {
        if (ctx == null || ctx.htmlContent() == null) {
            return List.of();
        }

        List<AstElement> elementList = new ArrayList<>();


        JavaScriptParser.HtmlContentContext contentContext = ctx.htmlContent();

        if (contentContext.htmlElement() != null && contentContext.htmlElement().size() > 0) {
            for (var htmlElement : contentContext.htmlElement()) {
                var object = transformJavaScriptHtmlElement(htmlElement, root);
                if (object.isPresent()) {
                    elementList.add(object.get());
                }
            }
        }

        if (contentContext.objectExpressionSequence() != null && contentContext.objectExpressionSequence().size() > 0) {
            for (var objectExpression : contentContext.objectExpressionSequence()) {
                if (objectExpression.expressionSequence() != null) {
                    var expressionSequence = JavaScriptExpressionSequence.transformExpressionSequenceToSequence(objectExpression.expressionSequence(), root);
                    if (expressionSequence.isPresent() && expressionSequence.get().elements != null && expressionSequence.get().elements.length > 0) {
                        elementList.addAll(Arrays.stream(expressionSequence.get().elements).toList());
                    }
                }
            }
        }


        return elementList;
    }

    public static Optional<io.codiga.model.ast.javascript.JavaScriptHtmlElement> transformJavaScriptHtmlElement(JavaScriptParser.HtmlElementContext ctx, ParserRuleContext root) {
        Optional<AstString> tag = Optional.empty();
        Optional<JavaScriptHtmlTag> openingTag = Optional.empty();
        Optional<JavaScriptHtmlTag> closingTag = Optional.empty();
        List<JavaScriptHtmlAttribute> attributes = new ArrayList<>();

        // opening tag and tag name
        if (ctx.htmlTagStartName() != null) {
            tag = mapTagName(ctx.htmlTagStartName().htmlTagName(), root);
        }
        if (ctx.htmlTagName() != null) {
            tag = mapTagName(ctx.htmlTagName(), root);
        }
        if (ctx.htmlTagStartName() == null && ctx.htmlTagName() == null) {
            tag = Optional.empty();
        }
        openingTag = Optional.of(new JavaScriptHtmlTag(tag.orElse(null), getOpeningTagStartPosition(ctx).orElse(null), getOpeningTagEndPosition(ctx).orElse(null), ctx, root));


        // closing tag
        Optional<AstString> closingTagName = Optional.empty();
        if (ctx != null && ctx.htmlTagClosingName() != null) {
            closingTagName = mapTagName(ctx.htmlTagClosingName().htmlTagName(), root);
        }
        closingTag = Optional.of(new JavaScriptHtmlTag(closingTagName.orElse(null), getClosingTagStartPosition(ctx).orElse(null), getClosingTagEndPosition(ctx).orElse(null), ctx, root));


        if (ctx.htmlAttribute() != null) {
            ctx.htmlAttribute().forEach(a -> {
                Optional<JavaScriptHtmlAttribute> attribute = JavaScriptHtmlAttributeTransformation.transformJavaScriptHtmlAttribute(a, root);
                if (attribute.isPresent()) {
                    attributes.add(attribute.get());
                }
            });
        }


        return Optional.of(
            new io.codiga.model.ast.javascript.JavaScriptHtmlElement(tag.orElse(null), openingTag.orElse(null), closingTag.orElse(null), attributes, getContent(ctx, root), ctx, root)
        );


    }

    public static Optional<AstString> mapTagName(JavaScriptParser.HtmlTagNameContext context, ParserRuleContext root) {
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
