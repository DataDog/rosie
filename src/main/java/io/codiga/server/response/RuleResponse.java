package io.codiga.server.response;

import java.util.List;

public class RuleResponse {
    public String identifier;
    public List<Violation> violations;
    public List<String> errors;
    public String executionError;
    public String output;
    public long executionTimeMs;

    public RuleResponse() {
        this.identifier = null;
        this.violations = null;
        this.errors = null;
        this.executionError = null;
        this.output = null;
        this.executionTimeMs = 0;
    }

    public RuleResponse(String id, List<Violation> violations, List<String> errors, String executionError, String output, long executionTimeMs) {
        this.identifier = id;
        this.violations = violations;
        this.errors = errors;
        this.executionError = executionError;
        this.output = output;
        this.executionTimeMs = executionTimeMs;
    }

    @Override
    public String toString() {
        return "RuleResponse{" +
            "identifier='" + identifier + '\'' +
            ", violations=" + violations +
            ", errors=" + errors +
            ", executionError='" + executionError + '\'' +
            ", output='" + output + '\'' +
            '}';
    }
}
