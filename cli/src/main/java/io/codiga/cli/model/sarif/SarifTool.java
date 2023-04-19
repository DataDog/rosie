package io.codiga.cli.model.sarif;

import io.codiga.analyzer.rule.AnalyzerRule;
import lombok.Builder;

import java.util.List;

/**
 * The analysis tool that was run.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L2910">See in the SARIF JSON Schema</a>
 */
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
