package io.codiga.model.ast;


import io.codiga.model.common.Position;
import org.antlr.v4.runtime.ParserRuleContext;

public class FunctionCallArgument extends AstElement {
    public String name;
    public String value;

    public FunctionCallArgument(String n, String v, Position start, Position end, ParserRuleContext parserRuleContext) {
        super(start, end, parserRuleContext);
        this.name = n;
        this.value = v;
    }
}