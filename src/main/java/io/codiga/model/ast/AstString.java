package io.codiga.model.ast;

import org.antlr.v4.runtime.ParserRuleContext;


public class AstString extends AstElement {

    public String value;
    public String str;

    public AstString(String value,
                     ParserRuleContext ruleContext,
                     ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_STRING, ruleContext, root);
        this.value = value;
        this.str = value;
    }
}
