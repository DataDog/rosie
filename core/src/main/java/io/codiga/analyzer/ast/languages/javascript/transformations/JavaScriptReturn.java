package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Return;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptExpressionSequence.transformExpressionSequenceToSequence;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptHtmlElementsTransformation.transformJavaScriptHtmlElements;
import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;

public class JavaScriptReturn {


    public static Optional<AstElement> transformReturn(JavaScriptParser.ReturnStatementContext ctx, ParserRuleContext root) {

        if (ctx == null) {
            return Optional.empty();
        }
        Optional<AstElement> astElementOptional = Optional.empty();
        if (ctx.expressionSequence() != null) {
            astElementOptional = convertToAstElement(transformExpressionSequenceToSequence(ctx.expressionSequence(), root));
        }
        if (ctx.htmlElements() != null) {
            astElementOptional = transformJavaScriptHtmlElements(ctx.htmlElements(), root);
        }
        return Optional.of(new Return(astElementOptional.orElse(null), ctx, root));
        
    }
}
