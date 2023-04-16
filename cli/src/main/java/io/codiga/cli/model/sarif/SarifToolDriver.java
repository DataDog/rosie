package io.codiga.cli.model.sarif;

import io.codiga.analyzer.rule.AnalyzerRule;
import lombok.Builder;

import java.util.List;

@Builder
public class SarifToolDriver {
    public String name;
    public String informationUri;
    public List<SarifToolDriverRule> rules;

    public static SarifToolDriver fromRules(List<AnalyzerRule> rules) {
        return SarifToolDriver
                .builder()
                .name("datadog-static-analysis")
                .informationUri("https://www.datadoghq.com")
                .rules(rules.stream().map(SarifToolDriverRule::fromAnalyzerRule).toList())
                .build();
    }
}
