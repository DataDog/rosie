package io.codiga.cli;

import io.codiga.analyzer.Analyzer;
import io.codiga.analyzer.config.AnalyzerConfiguration;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.cli.errorreporting.ErrorReportingDummy;
import io.codiga.cli.metrics.MetricsDummy;
import io.codiga.cli.model.Result;
import io.codiga.cli.model.ViolationWithFilename;
import io.codiga.model.Language;
import io.codiga.model.error.AnalysisResult;
import io.codiga.model.error.RuleResult;
import io.codiga.utils.Version;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static io.codiga.cli.CliConstants.WARMUP_LOOPS;
import static io.codiga.cli.FileUtils.*;
import static io.codiga.cli.RulesUtils.getRulesFromFile;
import static io.codiga.cli.RulesUtils.separateRules;
import static io.codiga.constants.Languages.LANGUAGE_EXTENSIONS;
import static io.codiga.utils.CompletableFutureUtils.sequence;
import static io.codiga.warmup.AnalyzerWarmup.warmupAnalyzer;

public class Main {

    static void showRules(List<AnalyzerRule> rules) {
        System.out.println("Loaded rules");
        System.out.println("============");
        rules.forEach(r -> {
            System.out.println(r.name());
        });
    }

    public static void main(String[] args) {

        AnalyzerConfiguration configuration = new AnalyzerConfiguration(10000);

        Options options = new Options();

        Option optionDirectory = Option.builder().required(true).option("i")
            .longOpt("directory").hasArg(true).desc("directory to scan (valid existing directory)").build();
        Option optionRules = Option.builder().required(true).option("r")
            .longOpt("rules").hasArg(true).desc("rules to use (path to JSON file)").build();
        Option optionDebug = Option.builder().required(false).option("d")
            .longOpt("debug").hasArg(true).desc("enable debug mode (true/false)").build();
        Option optionOutput = Option.builder().required(true).option("o")
            .longOpt("output").hasArg(true).desc("output file (path to file)").build();

        options.addOption(optionDirectory);
        options.addOption(optionRules);
        options.addOption(optionDebug);
        options.addOption(optionOutput);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;//not a good practice, it serves it purpose

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("rosie-cli", options);
            System.exit(1);
        }

        String directory = cmd.getOptionValue(optionDirectory);
        String rulesFile = cmd.getOptionValue(optionRules);
        String debugString = cmd.getOptionValue(optionDebug);
        String output = cmd.getOptionValue(optionOutput);
        boolean debug = debugString != null && debugString.equalsIgnoreCase("true");

        System.out.println("Configuration");
        System.out.println("=============");
        System.out.println(String.format("Version     : %s", Version.CURRENT_VERSION));
        System.out.println(String.format("# cores     : %s", Runtime.getRuntime().availableProcessors()));
        System.out.println(String.format("Debug       : %s", debug));
        System.out.println(String.format("Directory   : %s", directory));
        System.out.println(String.format("Rules file  : %s", rulesFile));
        System.out.println(String.format("Debug       : %s", debugString));
        System.out.println(String.format("Output file : %s", output));


        if (!Files.isDirectory(Paths.get(directory))) {
            System.err.println(String.format("%s is not a directory", directory));
            System.exit(1);
        }

        if (!Files.isReadable(Paths.get(rulesFile)) || !Files.isRegularFile(Paths.get(rulesFile))) {
            System.err.println(String.format("%s is not a readable file", rulesFile));
            System.exit(1);
        }

        // read the rules
        List<AnalyzerRule> rules = List.of();
        try {
            rules = getRulesFromFile(rulesFile);
        } catch (IOException e) {
            System.err.println(String.format("Error when trying to read the rules from file %s: %s", rulesFile, e.getMessage()));
            e.printStackTrace();
            System.exit(1);
        }

        showRules(rules);

        // get the list of files to analyze
        List<Path> filesToAnalyze = List.of();
        try {
            filesToAnalyze = Files.walk(Paths.get(directory)).filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println(String.format("Error when getting the list of files"));
            System.exit(1);
        }

        if (debug) {
            System.out.println("Files to analyze:");
            System.out.println("=================");
            filesToAnalyze.stream().forEach(System.out::println);
        }

        Analyzer analyzer = new Analyzer(new ErrorReportingDummy(), new MetricsDummy(), configuration);
        List<ViolationWithFilename> allViolations = new ArrayList<>();
        List<RuleResult> ruleResultsWithError = new ArrayList<>();

        // Warmup the analyzer
        warmupAnalyzer(analyzer, WARMUP_LOOPS);

        long startTimeMs = System.currentTimeMillis();
        int cpus = Runtime.getRuntime().availableProcessors();

        // For each language, we get the list of file for this language and get the violations
        for (Map.Entry<Language, List<String>> entry : LANGUAGE_EXTENSIONS.entrySet()) {
            if (debug) {
                System.out.println(String.format("Processing %s", entry.getKey()));
            }

            // Get the list of files to analyze
            List<Path> filesForLanguage = filterFilesByExtensions(filesToAnalyze, entry.getValue());

            // Get the list of rules for this language
            List<AnalyzerRule> rulesForLanguage = rules.stream().filter(r -> r.language() == entry.getKey()).toList();

            for (Path path : filesForLanguage) {
                String fullPath = path.toString();
                String relativePath = fullPath.replace(directory, "");
                try {
                    final String code = getFileContent(path);


                    List<List<AnalyzerRule>> subList = separateRules(rulesForLanguage, cpus);
                    System.out.println(String.format("Number of rules %s", rulesForLanguage.size()));
                    System.out.println(String.format("List size %s", subList.size()));
                    for (int i = 0; i < subList.size(); i++) {
                        System.out.println(String.format("sublist %s, size %s", i, subList.get(i).size()));
                        subList.get(i).forEach(r -> {
                            System.out.println(String.format("   %s", r.name()));
                        });
                    }

                    List<CompletableFuture<AnalysisResult>> futures = subList.stream().map(ruleList -> {
                        return analyzer.analyze(entry.getKey(), relativePath, code, ruleList, false);
                    }).toList();


                    List<AnalysisResult> analysisResultList = sequence(futures).get(configuration.analysisTimeoutMs, TimeUnit.MILLISECONDS);

                    analysisResultList.forEach(analysisResult -> {
                        List<ViolationWithFilename> violations = analysisResult.ruleResults().stream().flatMap(ruleResult -> ruleResult.violations().stream().map(violation -> new ViolationWithFilename(violation.start, violation.end, violation.message, violation.severity, violation.category, relativePath, ruleResult.identifier()))).toList();
                        analysisResult.ruleResults().forEach(ruleResult -> {
                            if (ruleResult.errors().size() > 0) {
                                System.out.println(String.format("rule %s on file %s reported errors %s", ruleResult.identifier(), relativePath, String.join(",", ruleResult.errors())));
                            }
                            if (ruleResult.executionError() != null) {
                                System.out.println(String.format("rule %s on file %s execution error: %s", ruleResult.identifier(), relativePath, ruleResult.executionError()));
                            }
                        });
                        allViolations.addAll(violations);
                        ruleResultsWithError.addAll(analysisResult.ruleResults().stream().filter(r -> r.errors().size() > 0).collect(Collectors.toList()));
                    });

                } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
                    System.err.println(String.format("Error while reading file %s", fullPath));
                    e.printStackTrace();
                }
            }
        }

        try {
            writeViolationsToFile(Paths.get(output), new Result(allViolations, ruleResultsWithError));
        } catch (IOException e) {
            System.err.println(String.format("Failed to write result into file %s", output));
            System.exit(1);
        }
        long endTimeMs = System.currentTimeMillis();

        System.out.println(String.format("Analysis took %sms", endTimeMs - startTimeMs));

        System.exit(0);

    }
}