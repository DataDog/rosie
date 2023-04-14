package io.codiga.cli.model.sarif;

import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.cli.model.ViolationWithFilename;
import io.codiga.model.error.RuleResult;
import lombok.Builder;

import java.nio.file.Path;
import java.util.List;

@Builder
public class SarifRun {
    public SarifTool tool;
    public List<SarifArtifact> artifacts;

    public static SarifRun generate(List<AnalyzerRule> rules,
                                    List<Path> filesToAnalyze,
                                    List<ViolationWithFilename> violations,
                                    List<RuleResult> rulesWithError) {
        return SarifRun
                .builder()
                .tool(SarifTool.fromRules(rules))
                .artifacts(filesToAnalyze.stream().map(SarifArtifact::generate).toList())
                .build();
    }

}
