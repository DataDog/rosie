package io.codiga.model.ast;

import io.codiga.model.common.Position;
import org.antlr.v4.runtime.ParserRuleContext;


public class AstString extends AstElement {

    public String value;


    public AstString(String value,
                     Position start,
                     Position end,
                     ParserRuleContext ruleContext,
                     ParserRuleContext root) {
        super(start, end, ruleContext, root);
        this.value = value;
    }
}
