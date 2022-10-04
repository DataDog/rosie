package io.codiga.cli.model;

import io.codiga.analyzer.rule.AnalyzerRule;

import java.util.List;

public class Rules {

    public List<AnalyzerRule> rules;

    public Rules() {
        this.rules = List.of();
    }

    public Rules(List rules) {
        this.rules = rules;
    }
}
