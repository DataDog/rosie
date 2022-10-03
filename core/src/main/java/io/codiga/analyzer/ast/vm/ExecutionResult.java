package io.codiga.analyzer.ast.vm;

import io.codiga.model.error.Violation;

import java.util.List;


public class ExecutionResult {
    private List<Violation> violations;
    private String output;

    public ExecutionResult(List<Violation> violations, String output) {
        this.violations = violations;
        this.output = output;
    }

    public String getOutput() {
        return this.output;
    }

    public List<Violation> getViolations() {
        return this.violations;
    }
}
