package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Return;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptExpressionSequence.transformExpressionSequenceToSequence;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptHtmlElementsTransformation.transformTypeScriptHtmlElements;


public class TypeScriptReturn {


    public static Optional<AstElement> transformReturnStatement(TypeScriptParser.ReturnStatementContext ctx, ParserRuleContext root) {

        if (ctx == null) {
            return Optional.empty();
        }
        Optional<AstElement> astElementOptional = Optional.empty();
        if (ctx.expressionSequence() != null) {
            astElementOptional = convertToAstElement(transformExpressionSequenceToSequence(ctx.expressionSequence(), root));
        }
        if (ctx.htmlElements() != null) {
            astElementOptional = convertToAstElement(transformTypeScriptHtmlElements(ctx.htmlElements(), root));
        }
        return Optional.of(new Return(astElementOptional.orElse(null), ctx, root));

    }
}
