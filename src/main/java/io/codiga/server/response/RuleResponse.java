package io.codiga.server.response;

import java.util.List;

public class RuleResponse {
    String identifier;
    public List<Violation> violations;
    public List<String> errors;

    public RuleResponse() {
        this.identifier = null;
        this.violations = null;
        this.errors = null;
    }

    public RuleResponse(String identifier, List<Violation> violations, List<String> errors) {
        this.identifier = identifier;
        this.violations = violations;
        this.errors = errors;
    }
}
