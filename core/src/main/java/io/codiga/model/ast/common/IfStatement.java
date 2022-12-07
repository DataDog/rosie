package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;

public class IfStatement extends AstElement {
    public AstElement condition;
    public AstString statements;
    public AstString elseStatements;

    public IfStatement(AstElement condition,
                       AstString statements,
                       AstString elseStatements,
                       ParserRuleContext ctx,
                       ParserRuleContext root) {
        super(AST_ELEMENT_ELIF_STATEMENT, ctx, root);
        this.condition = condition;
        this.statements = statements;
        this.elseStatements = elseStatements;
    }
}
