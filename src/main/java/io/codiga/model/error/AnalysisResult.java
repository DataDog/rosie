package io.codiga.model.error;


import java.util.List;

public record AnalysisResult(List<RuleResult> ruleResults) {

}