package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;

public class IfStatement extends AstElement {
    public AstElement condition;
    public AstElement statements;
    public AstElement elseStatements;

    public IfStatement(AstElement condition,
                       AstElement statements,
                       AstElement elseStatements,
                       ParserRuleContext ctx,
                       ParserRuleContext root) {
        super(AST_ELEMENT_IF_STATEMENT, ctx, root);
        this.condition = condition;
        this.statements = statements;
        this.elseStatements = elseStatements;
    }
}
