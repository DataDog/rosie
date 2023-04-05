package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;

public class Comparison extends AstElement {
    public AstElement leftSide;
    public String operator;
    public AstElement rightSide;

    public Comparison(AstElement leftSide,
                      String operator,
                      AstElement rightSide,
                      ParserRuleContext parserRuleContext,
                      ParserRuleContext root) {
        super(AstElementTypes.COMPARISON, parserRuleContext, root);
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.operator = operator;
    }
}
