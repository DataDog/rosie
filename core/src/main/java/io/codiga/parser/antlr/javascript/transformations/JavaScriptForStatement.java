package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.ForStatement;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;
import static io.codiga.parser.antlr.javascript.transformations.JavaScriptVariableDeclarationList.transformVariableDeclarationListToAssignmentList;

public class JavaScriptForStatement {


    public static Optional<ForStatement> transformForStatementToForStatement(JavaScriptParser.ForStatementContext ctx, ParserRuleContext root) {
        Optional<AstElement> initOptional = Optional.empty();
        Optional<AstElement> test = Optional.empty();
        Optional<AstElement> update = Optional.empty();

        if (ctx.variableDeclarationList() != null && ctx.expressionSequence() != null && ctx.expressionSequence().size() == 2) {
            initOptional = convertToAstElement(transformVariableDeclarationListToAssignmentList(ctx.variableDeclarationList(), root));
            test = convertToAstElement(JavaScriptExpressionSequence.transformExpressionSequenceToSequence(ctx.expressionSequence(0), root));
            update = convertToAstElement(JavaScriptExpressionSequence.transformExpressionSequenceToSequence(ctx.expressionSequence(1), root));
        }
        if (ctx.expressionSequence().size() == 3) {
            initOptional = convertToAstElement(JavaScriptExpressionSequence.transformExpressionSequenceToSequence(ctx.expressionSequence(1), root));
            test = convertToAstElement(JavaScriptExpressionSequence.transformExpressionSequenceToSequence(ctx.expressionSequence(1), root));
            update = convertToAstElement(JavaScriptExpressionSequence.transformExpressionSequenceToSequence(ctx.expressionSequence(2), root));
        }
        return Optional.of(new ForStatement(initOptional.orElse(null), test.orElse(null), update.orElse(null), ctx, root));
    }
}
