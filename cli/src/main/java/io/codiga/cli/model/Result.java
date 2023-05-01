package io.codiga.cli.model;

import io.codiga.model.error.RuleResult;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Result {
    public List<ViolationWithFilename> violations;
    public List<RuleResult> ruleResultsWithError;

    public Result(List<ViolationWithFilename> violations, List<RuleResult> ruleResultsWithError) {
        this.violations = violations;
        this.ruleResultsWithError = ruleResultsWithError;
    }
}
