package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptBlock.transformBlockContext;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptBreak.transformBreak;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptExpressionSequence.transformExpressionSequenceToSequence;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptIfStatementToIfStatement.transformIfStatementToIfStatement;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptReturn.transformReturn;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptSwitchStatement.transformSwitchStatement;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptVariableStatement.transformVariableStatement;
import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;

public class JavaScriptStatementToAstElement {


    public static Optional<AstElement> transformStatement(JavaScriptParser.StatementContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.block() != null) {
            return transformBlockContext(ctx.block(), root);
        }

        if (ctx.expressionStatement() != null && ctx.expressionStatement().expressionSequence() != null) {
            return convertToAstElement(transformExpressionSequenceToSequence(ctx.expressionStatement().expressionSequence(), root));
        }

        if (ctx.variableStatement() != null) {
            return transformVariableStatement(ctx.variableStatement(), root);
        }

        if (ctx.returnStatement() != null) {
            return transformReturn(ctx.returnStatement(), root);
        }

        if (ctx.ifStatement() != null) {
            return convertToAstElement(transformIfStatementToIfStatement(ctx.ifStatement(), root));
        }

        if (ctx.switchStatement() != null) {
            return convertToAstElement(transformSwitchStatement(ctx.switchStatement(), root));
        }

        if (ctx.breakStatement() != null) {
            return convertToAstElement(transformBreak(ctx.breakStatement(), null));
        }
        return Optional.empty();
    }

}
