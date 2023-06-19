package io.codiga.cli.model.sarif;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.codiga.analyzer.rule.AnalyzerRule;
import lombok.Builder;

/**
 * Metadata that describes a specific report produced by the tool, as part of the analysis it provides or its runtime reporting.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L1814">See in the SARIF JSON Schema</a>
 */
@Builder
public class SarifToolDriverRule {
    public String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public SarifMultiformatMessage fullDescription;
    public String helpUri;

    public static SarifToolDriverRule fromAnalyzerRule(AnalyzerRule analyzerRule) {
        return SarifToolDriverRule
                .builder()
                .fullDescription(SarifMultiformatMessage.from(analyzerRule.description()))
                .helpUri(String.format("https://docs.datadoghq.com/continuous_integration/static_analysis/rules/%s", analyzerRule.name()))
                .id(analyzerRule.name())
                .build();
    }
}
