package io.codiga.cli.model.sarif;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.codiga.analyzer.rule.AnalyzerRule;
import java.util.List;
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
    public SarifMultiformatMessage shortDescription;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public SarifMultiformatMessage fullDescription;
    public String helpUri;
    public SarifPropertyBag properties;

    public static SarifToolDriverRule fromAnalyzerRule(AnalyzerRule analyzerRule) {
        return SarifToolDriverRule
                .builder()
                .shortDescription(SarifMultiformatMessage.from(analyzerRule.shortDescription()))
                .fullDescription(SarifMultiformatMessage.from(analyzerRule.description()))
                .helpUri(String.format("https://static-analysis.datadoghq.com/%s", analyzerRule.name()))
                .properties(SarifPropertyBag.builder().tags(createPropertyTags(analyzerRule)).build())
                .id(analyzerRule.name())
                .build();
    }

    /**
     * Create a list of tags for the properties object.
     * @param analyzerRule The rule that's used to run an analysis
     * @return A list of tags
     */
    public static List<String> createPropertyTags(AnalyzerRule analyzerRule) {
        List<String> properties = new java.util.ArrayList<>();
        Boolean shouldUseAiFix = analyzerRule.shouldUseAiFix();
        if(shouldUseAiFix != null && shouldUseAiFix) {
            properties.add("shouldUseAiFix:true");
        }
        return properties;
    }
}
