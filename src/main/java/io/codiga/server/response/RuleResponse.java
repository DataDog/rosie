package io.codiga.server.response;

import java.util.List;

public class RuleResponse {
    public String identifier;
    public List<Violation> violations;
    public List<String> errors;
    public String executionError;
    public String output;

    public RuleResponse() {
        this.identifier = null;
        this.violations = null;
        this.errors = null;
        this.executionError = null;
        this.output = null;
    }

    public RuleResponse(String identifier, List<Violation> violations, List<String> errors, String executionError, String output) {
        this.identifier = identifier;
        this.violations = violations;
        this.errors = errors;
        this.executionError = executionError;
        this.output = output;
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
