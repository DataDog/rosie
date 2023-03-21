package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptArrowFunctionDeclaration.transformArrowFunctionDeclarationContext;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptBlock.transformBlock;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptExpressionSequence.transformExpressionSequenceToSequence;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptReturn.transformReturnStatement;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptVariableStatement.transformVariableStatementToSequence;
import static io.codiga.utils.Conversions.convertToAstElement;


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
            return convertToAstElement(TypeScriptIfStatementToIfStatement.transformIfStatementToIfStatement(ctx.ifStatement(), root));
        }

        if (ctx.returnStatement() != null) {
            return transformReturnStatement(ctx.returnStatement(), root);
        }

        if (ctx.switchStatement() != null) {
            return convertToAstElement(TypeScriptSwitchStatement.transformSwitchStatement(ctx.switchStatement(), root));
        }

        if (ctx.breakStatement() != null) {
            return convertToAstElement(TypeScriptBreak.transformBreak(ctx.breakStatement(), null));
        }
        return Optional.empty();

    }
}
