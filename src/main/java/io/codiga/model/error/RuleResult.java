package io.codiga.model.error;


import java.util.List;

public record RuleResult(String identifier,
                         List<Violation> violations,
                         List<String> errors,
                         String executionError) {

}