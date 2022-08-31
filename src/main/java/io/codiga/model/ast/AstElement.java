package io.codiga.model.ast;


import io.codiga.model.common.Position;
import org.antlr.v4.runtime.ParserRuleContext;

public class AstElement {
    public Position start;
    public Position end;
    private ParserRuleContext parserRuleContext;

    public AstElement(Position start, Position end, ParserRuleContext parserRuleContext) {
        this.start = start;
        this.end = end;
        this.parserRuleContext = parserRuleContext;
    }
}
