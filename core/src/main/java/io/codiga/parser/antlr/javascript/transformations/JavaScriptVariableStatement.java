package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

public class JavaScriptVariableStatement {


    public static Optional<AstElement> transformVariableStatement(JavaScriptParser.VariableStatementContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.variableDeclarationList() != null) {
            return JavaScriptVariableDeclarationList.transformVariableDeclarationToAstElement(ctx.variableDeclarationList(), root);
        }
        return Optional.empty();
    }
}
