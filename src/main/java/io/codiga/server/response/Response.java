package io.codiga.server.response;

import java.util.List;

public class Response {
    public List<RuleResponse> ruleResponses;
    public List<String> errors;

    public Response(List<RuleResponse> ruleResponses, List<String> errors) {
        this.ruleResponses = ruleResponses;
        this.errors = errors;
    }
}
