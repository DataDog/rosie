package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptVariableDeclarationList.transformVariableDeclarationToAstElement;

public class JavaScriptVariableStatement {


    public static Optional<AstElement> transformVariableStatement(JavaScriptParser.VariableStatementContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.variableDeclarationList() != null) {
            return transformVariableDeclarationToAstElement(ctx.variableDeclarationList(), root);
        }
        return Optional.empty();
    }
}
