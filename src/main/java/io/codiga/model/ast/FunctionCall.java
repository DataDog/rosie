package io.codiga.model.ast;

import java.util.List;


public class FunctionCall {

    public String moduleOrObject;
    public String functionName;
    public FunctionCallArgument[] arguments;
    public int line;


    public FunctionCall(String moduleOrObject, String functionName, List<FunctionCallArgument> argumentsList, int line) {
        this.arguments = new FunctionCallArgument[argumentsList.size()];
        this.arguments = argumentsList.toArray(arguments);
        this.moduleOrObject = moduleOrObject;
        this.functionName = functionName;
        this.line = line;
    }
}
