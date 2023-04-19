package io.codiga.cli.model.sarif;

import io.codiga.analyzer.rule.AnalyzerRule;
import lombok.Builder;

import java.util.List;

/**
 * The driver of the analysis tool that was run.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L2941">See in the SARIF JSON Schema</a>
 */
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
