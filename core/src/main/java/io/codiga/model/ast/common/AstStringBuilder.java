package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;

public class AstStringBuilder {
    private String value;
    private ParserRuleContext ruleContext;
    private ParserRuleContext root;

    public AstStringBuilder setValue(String value) {
        this.value = value;
        return this;
    }

    public AstStringBuilder setRuleContext(ParserRuleContext ruleContext) {
        this.ruleContext = ruleContext;
        return this;
    }

    public AstStringBuilder setRoot(ParserRuleContext root) {
        this.root = root;
        return this;
    }

    public AstString createAstString() {
        return new AstString(value, ruleContext, root);
    }
}