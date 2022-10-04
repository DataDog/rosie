package io.codiga.cli.model;

import io.codiga.analyzer.rule.AnalyzerRule;

import java.util.List;

/**
 * This class is used only to serialized/deserialized the JSON file being passed as an argument.
 */
public class Rules {

    public List<AnalyzerRule> rules;

    public Rules() {
        this.rules = List.of();
    }

    public Rules(List rules) {
        this.rules = rules;
    }
}
