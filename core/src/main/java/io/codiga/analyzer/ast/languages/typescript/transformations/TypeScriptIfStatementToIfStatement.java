package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.IfStatement;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;


public class TypeScriptIfStatementToIfStatement {


    public static Optional<IfStatement> transformIfStatementToIfStatement(TypeScriptParser.IfStatementContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.expressionSequence() != null) {
            if (ctx.expressionSequence().singleExpression() != null && ctx.expressionSequence().singleExpression().size() > 0) {
                Optional<AstElement> astElementOptional = transformSingleExpressionToAstElement(ctx.expressionSequence().singleExpression().get(0), root);
                if (astElementOptional.isPresent()) {
                    return Optional.of(new IfStatement(astElementOptional.get(), null, null, ctx, root));
                }
            }
        }

        return Optional.empty();
    }
}
