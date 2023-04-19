package io.codiga.cli.model.sarif;

import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.cli.model.ViolationWithFilename;
import io.codiga.model.error.RuleResult;
import lombok.Builder;

import java.nio.file.Path;
import java.util.List;

import static io.codiga.cli.utils.SarifUtils.SARIF_VERSION;

/**
 * The standard format for the output of static analysis tools.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L8">See in the SARIF JSON Schema</a>
 */
@Builder
public class SarifReport {
    public String version;
    public List<SarifRun> runs;


    public static SarifReport generate(List<AnalyzerRule> rules,
                                       List<Path> filesToAnalyze,
                                       List<ViolationWithFilename> violations,
                                       List<RuleResult> rulesWithError) {
        return SarifReport
                .builder()
                .version(SARIF_VERSION)
                .runs(List.of(SarifRun.generate(rules, filesToAnalyze, violations, rulesWithError)))
                .build();
    }
}
