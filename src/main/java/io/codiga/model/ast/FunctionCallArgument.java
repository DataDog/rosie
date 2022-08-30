package io.codiga.model.ast;


public class FunctionCallArgument {
    public String name;
    public String value;

    public FunctionCallArgument(String n, String v) {
        this.name = n;
        this.value = v;
    }
}