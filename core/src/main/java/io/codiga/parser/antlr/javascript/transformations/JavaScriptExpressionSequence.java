package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Sequence;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;
import static io.codiga.parser.antlr.javascript.transformations.JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;

public class JavaScriptExpressionSequence {


    public static Optional<AstElement> transformExpressionSequenceToAstElement(JavaScriptParser.ExpressionSequenceContext ctx, ParserRuleContext root) {
        if (ctx == null || ctx.singleExpression() == null) {
            return Optional.empty();
        }

        if (ctx.singleExpression().isEmpty()) {
            return Optional.empty();
        }

        if (ctx.singleExpression().size() == 1) {
            return transformSingleExpressionToAstElement(ctx.singleExpression().get(0), root);
        }
        return convertToAstElement(transformExpressionSequenceToSequence(ctx, root));
    }

    public static Optional<Sequence> transformExpressionSequenceToSequence(JavaScriptParser.ExpressionSequenceContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        List<AstElement> res = new ArrayList<>();
        ctx.singleExpression().forEach(v -> {
            Optional<AstElement> t = transformSingleExpressionToAstElement(v, root);
            if (t.isPresent()) {
                res.add(t.get());
            }
        });


        return Optional.of(new Sequence(res, ctx, root));
    }
}
