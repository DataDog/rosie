package io.codiga.model.ast;


import io.codiga.model.common.Position;

public class FunctionCallArgument extends AstElement {
    public String name;
    public String value;

    public FunctionCallArgument(String n, String v, Position start, Position end) {
        super(start, end);
        this.name = n;
        this.value = v;
    }
}