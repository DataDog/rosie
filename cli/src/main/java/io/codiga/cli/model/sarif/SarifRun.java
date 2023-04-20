package io.codiga.cli.model.sarif;

import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.cli.model.ViolationWithFilename;
import io.codiga.model.error.RuleResult;
import lombok.Builder;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Describes a single run of an analysis tool, and contains the reported output of that run.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L2339">See in the SARIF JSON Schema</a>
 */
@Builder
public class SarifRun {
    public SarifTool tool;
    public List<SarifArtifact> artifacts;
    public List<SarifResult> results;

    public static SarifRun generate(List<AnalyzerRule> rules,
                                    List<Path> filesToAnalyze,
                                    List<ViolationWithFilename> violations,
                                    List<RuleResult> rulesWithError) {
        Map<String, Integer> ruleNameToRuleIndex = generateRulesIndex(rules);

        return SarifRun
            .builder()
            .tool(SarifTool.fromRules(rules))
            .artifacts(filesToAnalyze.stream().map(SarifArtifact::generate).toList())
            .results(violations.stream().map(v -> SarifResult.generate(v, ruleNameToRuleIndex.getOrDefault(v.rule, -1))).toList())
            .build();
    }

    /**
     * Build a Map that contains the index of the rule in the list
     * of rule to build the ruleIndex in each result.
     *
     * @param rules
     * @return
     */
    public static Map<String, Integer> generateRulesIndex(List<AnalyzerRule> rules) {
        int index = 0;
        Map<String, Integer> ruleNameToRuleIndex = new HashMap<>();
        for (AnalyzerRule rule : rules) {
            ruleNameToRuleIndex.put(rule.name(), index);
            index = index + 1;
        }
        return ruleNameToRuleIndex;
    }

}
