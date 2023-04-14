package io.codiga.cli.model.sarif;

import io.codiga.analyzer.rule.AnalyzerRule;
import lombok.Builder;

import java.util.List;

@Builder
public class SarifTool {
    public SarifToolDriver driver;

    public static SarifTool fromRules(List<AnalyzerRule> rules) {
        return SarifTool
                .builder()
                .driver(SarifToolDriver.fromRules(rules))
                .build();
    }
}
