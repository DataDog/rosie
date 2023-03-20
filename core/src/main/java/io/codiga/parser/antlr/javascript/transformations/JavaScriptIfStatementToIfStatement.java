package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.IfStatement;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.model.ast.common.Sequence.flattenOptionalAstElement;
import static io.codiga.parser.antlr.javascript.transformations.JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;

public class JavaScriptIfStatementToIfStatement {


    public static Optional<IfStatement> transformIfStatementToIfStatement(JavaScriptParser.IfStatementContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.expressionSequence() != null) {
            if (ctx.expressionSequence().singleExpression() != null && ctx.expressionSequence().singleExpression().size() > 0) {
                Optional<AstElement> astElementOptional = transformSingleExpressionToAstElement(ctx.expressionSequence().singleExpression().get(0), root);
                if (astElementOptional.isPresent()) {
                    Optional<AstElement> statements = Optional.empty();
                    Optional<AstElement> elseStatements = Optional.empty();
                    if (ctx.statement() != null && ctx.statement().size() > 0) {
                        statements = flattenOptionalAstElement(JavaScriptStatementToAstElement.transformStatement(ctx.statement().get(0), root));
                    }

                    if (ctx.statement() != null && ctx.statement().size() == 2) {
                        elseStatements = flattenOptionalAstElement(JavaScriptStatementToAstElement.transformStatement(ctx.statement().get(1), root));
                    }

                    return Optional.of(new IfStatement(astElementOptional.get(), statements.orElse(null), elseStatements.orElse(null), ctx, root));
                }
            }
        }

        return Optional.empty();
    }
}
