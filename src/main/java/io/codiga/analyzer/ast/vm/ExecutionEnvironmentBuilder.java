package io.codiga.analyzer.ast.vm;

import io.codiga.analyzer.ast.common.ErrorReporting;

public class ExecutionEnvironmentBuilder {
    private Object rootObject;
    private ErrorReporting errorReporting;
    private String code;
    private boolean logOutput = false;

    public ExecutionEnvironmentBuilder setRootObject(Object rootObject) {
        this.rootObject = rootObject;
        return this;
    }

    public ExecutionEnvironmentBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public ExecutionEnvironmentBuilder setLogOutput(boolean logOutput) {
        this.logOutput = logOutput;
        return this;
    }

    public ExecutionEnvironment createExecutionEnvironment() {
        return new ExecutionEnvironment(rootObject, code, logOutput);
    }
}