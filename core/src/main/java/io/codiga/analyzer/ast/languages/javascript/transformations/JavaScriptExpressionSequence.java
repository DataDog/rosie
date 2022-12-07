package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Sequence;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;

public class JavaScriptExpressionSequence {


    public static Optional<Sequence> transformExpressionSequenceToSequence(JavaScriptParser.ExpressionSequenceContext ctx, ParserRuleContext root) {
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
