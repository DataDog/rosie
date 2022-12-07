package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.ForStatement;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.Conversions.convertToAstElement;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptExpressionSequence.transformExpressionSequenceToSequence;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptVariableDeclarationList.transformVariableDeclarationListToAssignmentList;

public class JavaScriptForStatement {


    public static Optional<ForStatement> transformForStatementToForStatement(JavaScriptParser.ForStatementContext ctx, ParserRuleContext root) {
        Optional<AstElement> initOptional = Optional.empty();
        Optional<AstElement> test = Optional.empty();
        Optional<AstElement> update = Optional.empty();

        if (ctx.variableDeclarationList() != null && ctx.expressionSequence() != null && ctx.expressionSequence().size() == 2) {
            initOptional = convertToAstElement(transformVariableDeclarationListToAssignmentList(ctx.variableDeclarationList(), root));
            test = convertToAstElement(transformExpressionSequenceToSequence(ctx.expressionSequence(0), root));
            update = convertToAstElement(transformExpressionSequenceToSequence(ctx.expressionSequence(1), root));
        }
        if (ctx.expressionSequence().size() == 3) {
            initOptional = convertToAstElement(transformExpressionSequenceToSequence(ctx.expressionSequence(1), root));
            test = convertToAstElement(transformExpressionSequenceToSequence(ctx.expressionSequence(1), root));
            update = convertToAstElement(transformExpressionSequenceToSequence(ctx.expressionSequence(2), root));
        }
        return Optional.of(new ForStatement(initOptional.orElse(null), test.orElse(null), update.orElse(null), ctx, root));
    }
}
