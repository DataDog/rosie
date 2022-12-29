package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstString;
import org.antlr.v4.runtime.ParserRuleContext;


public class AstStringWithSpreadOperator extends AstString {

    public boolean isSpread = false;

    public AstStringWithSpreadOperator(String value,
                                       boolean isSpread,
                                       ParserRuleContext ruleContext,
                                       ParserRuleContext root) {
        super(value, ruleContext, root);
        this.isSpread = isSpread;
    }
    
}