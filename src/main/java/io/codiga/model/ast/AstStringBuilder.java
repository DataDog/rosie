package io.codiga.model.ast;

import io.codiga.model.common.Position;
import org.antlr.v4.runtime.ParserRuleContext;

public class AstStringBuilder {
    private String value;
    private Position start;
    private Position end;
    private ParserRuleContext ruleContext;
    private ParserRuleContext root;

    public AstStringBuilder setValue(String value) {
        this.value = value;
        return this;
    }

    public AstStringBuilder setStart(Position start) {
        this.start = start;
        return this;
    }

    public AstStringBuilder setEnd(Position end) {
        this.end = end;
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
        return new AstString(value, start, end, ruleContext, root);
    }
}