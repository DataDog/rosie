package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;


public class AstExpression extends AstElement {

    public AstElement left;
    public AstElement operator;
    public AstElement right;

    public AstExpression(AstElement left,
                         AstElement operator,
                         AstElement right,
                         ParserRuleContext ruleContext,
                         ParserRuleContext root) {
        super(AstElementTypes.EXPRESSION, ruleContext, root);
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

}
