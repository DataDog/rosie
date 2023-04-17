package io.codiga.cli;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.cli.model.Result;
import io.codiga.cli.model.ViolationWithFilename;
import io.codiga.cli.utils.SarifUtils;
import io.codiga.model.error.RuleResult;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static io.codiga.cli.utils.SarifUtils.generateReport;

public class FileUtils {


    public static List<Path> filterFilesByExtensions(List<Path> paths, List<String> extensions) {
        return paths.stream().filter(p -> {
            String extension = Files.getFileExtension(p.getFileName().toString());
            return extensions.contains(extension);
        }).collect(Collectors.toList());
    }

    public static String getFileContent(Path path) throws IOException {
        return new String(java.nio.file.Files.readAllBytes(path));
    }


    public static void writeViolationsToFile(Path path, Result result) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(path.toFile(), result);
    }

    /**
     * Write the SARIF report
     *
     * @param reportPath     - the file where to write the report
     * @param rules          - the list of rules used
     * @param filesToAnalyze - the files to analyze
     * @param violations     - the list of violations
     * @param rulesWithError - the list of rules with an error
     * @throws IOException
     */
    public static void writeSarifReport(Path reportPath,
                                        List<AnalyzerRule> rules,
                                        List<Path> filesToAnalyze,
                                        List<ViolationWithFilename> violations,
                                        List<RuleResult> rulesWithError) throws IOException {
        SarifUtils
            .getSarifObjectMapper()
            .writeValue(reportPath.toFile(), generateReport(rules, filesToAnalyze, violations, rulesWithError));
    }

}
