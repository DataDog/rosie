package io.codiga.model.ast;

import org.antlr.v4.runtime.ParserRuleContext;


public class AstString extends AstElement {

    public String value;


    public AstString(String value,
                     ParserRuleContext ruleContext,
                     ParserRuleContext root) {
        super(ruleContext, root);
        this.value = value;
    }
}
