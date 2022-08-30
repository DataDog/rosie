package io.codiga.server.response;

import java.util.List;

public class RuleResponse {
    public String identifier;
    public List<Violation> violations;
    public List<String> errors;
    public String executionError;

    public RuleResponse() {
        this.identifier = null;
        this.violations = null;
        this.errors = null;
        this.executionError = null;
    }

    public RuleResponse(String identifier, List<Violation> violations, List<String> errors, String executionError) {
        this.identifier = identifier;
        this.violations = violations;
        this.errors = errors;
        this.executionError = executionError;
    }
}
