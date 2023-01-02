package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.ForStatement;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptExpressionSequence.transformExpressionSequenceToSequence;
import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;

public class TypeScriptForStatement {


    public static Optional<ForStatement> transformForStatementToForStatement(TypeScriptParser.ForStatementContext ctx, ParserRuleContext root) {
        Optional<AstElement> initOptional = Optional.empty();
        Optional<AstElement> test = Optional.empty();
        Optional<AstElement> update = Optional.empty();


        if (ctx.expressionSequence().size() == 3) {
            initOptional = convertToAstElement(transformExpressionSequenceToSequence(ctx.expressionSequence(0), root));
            test = convertToAstElement(transformExpressionSequenceToSequence(ctx.expressionSequence(1), root));
            update = convertToAstElement(transformExpressionSequenceToSequence(ctx.expressionSequence(2), root));
        }
        return Optional.of(new ForStatement(initOptional.orElse(null), test.orElse(null), update.orElse(null), ctx, root));
    }
}
