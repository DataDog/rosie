package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;


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

    public AstString(String value,
                     Token token,
                     ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_STRING, token, root);
        this.value = value;
        this.str = value;
    }
}
