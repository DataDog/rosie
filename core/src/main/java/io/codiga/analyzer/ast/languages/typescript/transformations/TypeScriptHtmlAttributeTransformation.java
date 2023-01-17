package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptExpressionSequence.transformExpressionSequenceToSequence;
import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;

public class TypeScriptHtmlAttributeTransformation {

    public static Optional<AstString> transformAttributeNameToAstString(TypeScriptParser.HtmlAttributeNameContext name, ParserRuleContext root) {
        if (name.TagName() != null) {
            return Optional.of(new AstString(name.TagName().getText(), name.TagName().getSymbol(), root));
        }

        if (name.identifierOrKeyWordForHtmlAttribute() != null) {
            String n = String.join("-", name.identifierOrKeyWordForHtmlAttribute().stream().map(i -> i.getText()).toList());
            return Optional.of(new AstString(n, name, root));
        }
        return Optional.empty();
    }


    public static Optional<AstElement> transformAttributeValue(TypeScriptParser.HtmlAttributeValueContext ctx, ParserRuleContext root) {
        if (ctx.AttributeValue() != null) {
            return Optional.of(new AstString(ctx.AttributeValue().getText(), ctx.AttributeValue().getSymbol(), root));
        }
        if (ctx.StringLiteral() != null) {
            return Optional.of(new AstString(ctx.StringLiteral().getText(), ctx.StringLiteral().getSymbol(), root));
        }
        if (ctx.objectExpressionSequence() != null) {
            if (ctx.objectExpressionSequence().expressionSequence() != null) {
                return convertToAstElement(transformExpressionSequenceToSequence(ctx.objectExpressionSequence().expressionSequence(), root));
            }
        }
        return Optional.empty();
    }

    public static Optional<io.codiga.model.ast.javascript.JavaScriptHtmlAttribute> transformTypeScriptHtmlAttribute(TypeScriptParser.HtmlAttributeContext ctx, ParserRuleContext root) {
        Optional<AstString> name = Optional.empty();
        Optional<AstElement> value = Optional.empty();


        if (ctx.htmlAttributeName() != null) {
            name = transformAttributeNameToAstString(ctx.htmlAttributeName(), root);
        }

        if (ctx.htmlAttributeValue() != null) {
            value = transformAttributeValue(ctx.htmlAttributeValue(), root);
        }

        if (name.isPresent()) {
            return Optional.of(new io.codiga.model.ast.javascript.JavaScriptHtmlAttribute(name.orElse(null), value.orElse(null), ctx, root));
        }

        return Optional.empty();
    }
}
