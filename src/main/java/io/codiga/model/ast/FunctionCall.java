package io.codiga.model.ast;

import io.codiga.model.common.Position;

import java.util.List;


public class FunctionCall extends AstElement {

    public String moduleOrObject;
    public String functionName;
    public FunctionCallArgument[] arguments;
    public int line;


    public FunctionCall(String moduleOrObject, String functionName, List<FunctionCallArgument> argumentsList, Position start, Position end) {
        super(start, end);
        this.arguments = new FunctionCallArgument[argumentsList.size()];
        this.arguments = argumentsList.toArray(arguments);
        this.moduleOrObject = moduleOrObject;
        this.functionName = functionName;
    }
}
