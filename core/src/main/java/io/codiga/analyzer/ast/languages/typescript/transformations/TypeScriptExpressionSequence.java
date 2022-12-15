package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Sequence;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;


public class TypeScriptExpressionSequence {


    public static Optional<Sequence> transformExpressionSequenceToSequence(TypeScriptParser.ExpressionSequenceContext ctx, ParserRuleContext root) {
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
