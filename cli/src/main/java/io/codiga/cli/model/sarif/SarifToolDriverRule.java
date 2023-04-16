package io.codiga.cli.model.sarif;

import io.codiga.analyzer.rule.AnalyzerRule;
import lombok.Builder;

@Builder
public class SarifToolDriverRule {
    public String id;
    public String helpUri;

    public static SarifToolDriverRule fromAnalyzerRule(AnalyzerRule analyzerRule) {
        return SarifToolDriverRule
                .builder()
                .helpUri(String.format("https://static-analysis.datadoghq.com/%s", analyzerRule.name()))
                .id(analyzerRule.name())
                .build();
    }
}
