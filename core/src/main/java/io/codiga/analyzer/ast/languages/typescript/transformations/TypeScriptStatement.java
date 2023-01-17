package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptArrowFunctionDeclaration.transformArrowFunctionDeclarationContext;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptBlock.transformBlock;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptBreak.transformBreak;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptExpressionSequence.transformExpressionSequenceToSequence;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIfStatementToIfStatement.transformIfStatementToIfStatement;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptReturn.transformReturnStatement;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptSwitchStatement.transformSwitchStatement;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptVariableStatement.transformVariableStatementToSequence;
import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;


public class TypeScriptStatement {


    public static Optional<AstElement> transformStatementToAstElement(TypeScriptParser.StatementContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.arrowFunctionDeclaration() != null) {
            return convertToAstElement(transformArrowFunctionDeclarationContext(ctx.arrowFunctionDeclaration(), root));
        }

        if (ctx.block() != null) {
            return transformBlock(ctx.block(), root);
        }

        if (ctx.expressionStatement() != null && ctx.expressionStatement().expressionSequence() != null) {
            return convertToAstElement(transformExpressionSequenceToSequence(ctx.expressionStatement().expressionSequence(), root));
        }

        if (ctx.variableStatement() != null) {
            return convertToAstElement(transformVariableStatementToSequence(ctx.variableStatement(), root));
        }

        if (ctx.ifStatement() != null) {
            return convertToAstElement(transformIfStatementToIfStatement(ctx.ifStatement(), root));
        }

        if (ctx.returnStatement() != null) {
            return transformReturnStatement(ctx.returnStatement(), root);
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
