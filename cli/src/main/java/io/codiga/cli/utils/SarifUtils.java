package io.codiga.cli.utils;

import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.cli.model.ViolationWithFilename;
import io.codiga.cli.model.sarif.SarifReport;
import io.codiga.model.error.RuleResult;

import java.nio.file.Path;
import java.util.List;

public class SarifUtils {

    public static final String SARIF_VERSION = "2.4.1";

    public static SarifReport generateReport(List<AnalyzerRule> rules,
                                             List<Path> filesToAnalyze,
                                             List<ViolationWithFilename> violations,
                                             List<RuleResult> rulesWithError) {
        return SarifReport.generate(rules, filesToAnalyze, violations, rulesWithError);
    }
}
