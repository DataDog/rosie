package io.codiga.cli.model.sarif;

import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.cli.model.ViolationWithFilename;
import io.codiga.model.error.RuleResult;
import lombok.Builder;

import java.nio.file.Path;
import java.util.List;

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
        return SarifRun
            .builder()
            .tool(SarifTool.fromRules(rules))
            .artifacts(filesToAnalyze.stream().map(SarifArtifact::generate).toList())
            .results(violations.stream().map(SarifResult::generate).toList())
            .build();
    }

}
