package io.codiga.analyzer.rule;

import io.codiga.model.Language;
import io.codiga.model.RuleType;

public record AnalyzerRule(String name, Language language, RuleType ruleType, String code) {
}
