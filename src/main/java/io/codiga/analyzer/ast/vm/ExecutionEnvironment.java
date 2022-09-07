package io.codiga.analyzer.ast.vm;

import io.codiga.analyzer.ast.common.ErrorReporting;

public class ExecutionEnvironment {
    public Object rootObject;
    public ErrorReporting errorReporting;
    public String code;

    public ExecutionEnvironment(Object rootObject, ErrorReporting errorReporting, String code) {
        this.rootObject = rootObject;
        this.errorReporting = errorReporting;
        this.code = code;
    }
}
