package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.IfStatement;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;


public class TypeScriptTernaryExpressionToIfStatement {


    public static Optional<IfStatement> transformTernaryExpressionToIfStatement(TypeScriptParser.TernaryExpressionContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.singleExpression().size() != 3) {
            return Optional.empty();
        }

        Optional<AstElement> condition = transformSingleExpressionToAstElement(ctx.singleExpression(0), root);
        Optional<AstElement> thenBlock = transformSingleExpressionToAstElement(ctx.singleExpression(1), root);
        Optional<AstElement> elseBlock = transformSingleExpressionToAstElement(ctx.singleExpression(2), root);


        return Optional.of(new IfStatement(condition.orElse(null), thenBlock.orElse(null), elseBlock.orElse(null), ctx, root));
    }
}
