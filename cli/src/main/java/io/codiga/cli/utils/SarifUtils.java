package io.codiga.cli.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.cli.model.ViolationWithFilename;
import io.codiga.cli.model.sarif.SarifReport;
import io.codiga.model.error.RuleResult;
import io.codiga.model.error.Severity;

import java.nio.file.Path;
import java.util.List;

/**
 * Utilities method to convert to SARIF.
 * See the official schema in https://github.com/oasis-tcs/sarif-spec/blob/main/Documents/CommitteeSpecifications/2.1.0/sarif-schema-2.1.0.json
 */
public class SarifUtils {

    public static final String SARIF_VERSION = "2.1.0";

    /**
     * Convert the Severity enumeration from Rosie into the String
     *
     * @param severity
     * @return
     */
    public static String severityToSarifLevel(Severity severity) {
        switch (severity) {
            case ERROR, CRITICAL -> {
                return "error";
            }
            case WARNING -> {
                return "warning";
            }
            case INFORMATIONAL -> {
                return "note";
            }
            case UNKNOWN -> {
                return "none";
            }
        }
        return "none";
    }

    public static SarifReport generateReport(List<AnalyzerRule> rules,
                                             List<Path> filesToAnalyze,
                                             List<ViolationWithFilename> violations,
                                             List<RuleResult> rulesWithError) {
        return SarifReport.generate(rules, filesToAnalyze, violations, rulesWithError);
    }

    /**
     * Get the object mapper for SARIF. This object mapper should be the one
     * used to map all SARIF objects. Having this function ensures the same
     * mapper is used/called between the code and the tests
     *
     * @return the object mapper used to serialized the SARIF data.
     */
    public static ObjectMapper getSarifObjectMapper() {
        // Just return the default mapper and add customization if needed.
        return new ObjectMapper();
    }
}
