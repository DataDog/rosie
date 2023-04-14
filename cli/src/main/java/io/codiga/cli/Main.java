package io.codiga.cli;

import io.codiga.analyzer.AnalysisOptions;
import io.codiga.analyzer.Analyzer;
import io.codiga.analyzer.config.AnalyzerConfiguration;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.cli.errorreporting.ErrorReportingDummy;
import io.codiga.cli.metrics.MetricsDummy;
import io.codiga.cli.model.OutputFormat;
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
import static io.codiga.cli.utils.RulesUtils.getRulesFromFile;
import static io.codiga.cli.utils.RulesUtils.separateRules;
import static io.codiga.constants.Languages.LANGUAGE_EXTENSIONS;
import static io.codiga.utils.CompletableFutureUtils.sequence;
import static io.codiga.warmup.AnalyzerWarmup.warmupAnalyzer;

public class Main {

    private static final int DEFAULT_TIMEOUT_MS = 50000;

    static void showRules(List<AnalyzerRule> rules) {
        System.out.println("Loaded rules");
        System.out.println("============");
        rules.forEach(r -> {
            System.out.println(r.name());
        });
    }

    static OutputFormat getOutputFormatFromString(String fmt) {
        if (fmt == null) {
            return OutputFormat.JSON;
        }

        if (fmt.equalsIgnoreCase("sarif")) {
            return OutputFormat.SARIF;
        }
        return OutputFormat.JSON;
    }

    public static void main(String[] args) {

        AnalyzerConfiguration configuration = new AnalyzerConfiguration(DEFAULT_TIMEOUT_MS);
        Options options = new Options();

        Option optionDirectory = Option.builder().required(true).option("i")
            .longOpt("directory").hasArg(true).desc("directory to scan (valid existing directory)").build();
        Option optionRules = Option.builder().required(true).option("r")
            .longOpt("rules").hasArg(true).desc("rules to use (path to JSON file)").build();
        Option optionDebug = Option.builder().required(false).option("d")
            .longOpt("debug").hasArg(true).desc("enable debug mode (true/false)").build();
        Option optionTreeSitter = Option.builder().required(false).option("t")
            .longOpt("tree-sitter").hasArg(true).desc("enable tree-sitter (true/false)").build();
        Option optionOutput = Option.builder().required(true).option("o")
            .longOpt("output").hasArg(true).desc("output file (path to file)").build();
        Option optionOutputFormat = Option.builder().required(false).option("f")
            .longOpt("format").hasArg(true).desc("output format (json/sarif)").build();


        options.addOption(optionDirectory);
        options.addOption(optionRules);
        options.addOption(optionDebug);
        options.addOption(optionOutput);
        options.addOption(optionTreeSitter);
        options.addOption(optionOutputFormat);

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
        String useTreeSitterString = cmd.getOptionValue(optionTreeSitter);
        String output = cmd.getOptionValue(optionOutput);
        String outputFormatString = cmd.getOptionValue(optionOutputFormat);
        boolean debug = debugString != null && debugString.equalsIgnoreCase("true");
        boolean useTreeSitter = useTreeSitterString != null && useTreeSitterString.equalsIgnoreCase("true");
        OutputFormat outputFormat = getOutputFormatFromString(outputFormatString);

        System.out.println("Configuration");
        System.out.println("===================");
        System.out.printf("Version       : %s%n", Version.CURRENT_VERSION);
        System.out.printf("# cores       : %s%n", Runtime.getRuntime().availableProcessors());
        System.out.printf("Debug         : %s%n", debug);
        System.out.printf("Directory     : %s%n", directory);
        System.out.printf("Rules file    : %s%n", rulesFile);
        System.out.printf("Debug         : %s%n", debugString);
        System.out.printf("Tree-Sitter   : %s%n", useTreeSitter);
        System.out.printf("Output file   : %s%n", output);
        System.out.printf("Output format : %s%n", outputFormat.name());


        if (!Files.isDirectory(Paths.get(directory))) {
            System.err.printf("%s is not a directory%n", directory);
            System.exit(1);
        }

        if (!Files.isReadable(Paths.get(rulesFile)) || !Files.isRegularFile(Paths.get(rulesFile))) {
            System.err.printf("%s is not a readable file%n", rulesFile);
            System.exit(1);
        }

        // read the rules
        List<AnalyzerRule> rules = List.of();
        try {
            rules = getRulesFromFile(rulesFile);
        } catch (IOException e) {
            System.err.printf("Error when trying to read the rules from file %s: %s%n", rulesFile, e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        showRules(rules);

        // get the list of files to analyze
        List<Path> filesToAnalyze = List.of();
        try {
            filesToAnalyze = Files.walk(Paths.get(directory)).filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error when getting the list of files");
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
        // we are defining parallemism: if we have more than 2 CPUs, we take the number of CPU - 1
        int parallelism = cpus > 1 ? cpus - 1 : cpus;

        // Analysis options
        AnalysisOptions analysisOptions = AnalysisOptions.builder()
            .useTreeSitter(useTreeSitter)
            .build();

        // For each language, we get the list of file for this language and get the violations
        for (Map.Entry<Language, List<String>> entry : LANGUAGE_EXTENSIONS.entrySet()) {
            if (debug) {
                System.out.printf("Processing %s%n", entry.getKey());
            }

            // Get the list of files to analyze
            List<Path> filesForLanguage = filterFilesByExtensions(filesToAnalyze, entry.getValue());

            // Get the list of rules for this language
            List<AnalyzerRule> rulesForLanguage = rules.stream().filter(r -> r.language() == entry.getKey()).toList();

            // For each file
            for (Path path : filesForLanguage) {
                String fullPath = path.toString();
                String relativePath = fullPath.replace(directory, "");
                String basename = path.getFileName().toString();
                try {
                    final String code = getFileContent(path);

                    // put the rule to execute for each core
                    List<List<AnalyzerRule>> subList = separateRules(rulesForLanguage, parallelism);
                    if (debug) {
                        System.out.printf("Number of rules %s%n", rulesForLanguage.size());
                        System.out.printf("List size %s%n", subList.size());
                        for (int i = 0; i < subList.size(); i++) {
                            System.out.printf("sublist %s, size %s%n", i, subList.get(i).size());
                            subList.get(i).forEach(r -> {
                                System.out.printf("   %s%n", r.name());
                            });
                        }
                    }

                    // Analyze the file with one thread that is sharing
                    List<CompletableFuture<AnalysisResult>> futures = subList.stream().map(ruleList -> analyzer.analyze(entry.getKey(), basename, code, ruleList, analysisOptions)).toList();


                    List<AnalysisResult> analysisResultList = sequence(futures).get(configuration.analysisTimeoutMs, TimeUnit.MILLISECONDS);

                    analysisResultList.forEach(analysisResult -> {
                        List<ViolationWithFilename> violations = analysisResult.ruleResults().stream().flatMap(ruleResult -> ruleResult.violations().stream().map(violation -> new ViolationWithFilename(violation.start, violation.end, violation.message, violation.severity, violation.category, relativePath, ruleResult.identifier()))).toList();
                        analysisResult.ruleResults().forEach(ruleResult -> {
                            if (debug) {
                                System.out.printf("rule %s on file %s took %s ms%n", ruleResult.identifier(), relativePath, ruleResult.executionTimeMs());
                            }
                            if (ruleResult.errors().size() > 0) {
                                System.out.printf("rule %s on file %s reported errors %s%n", ruleResult.identifier(), relativePath, String.join(",", ruleResult.errors()));
                            }
                            if (ruleResult.executionError() != null) {
                                System.out.printf("rule %s on file %s execution error: %s%n", ruleResult.identifier(), relativePath, ruleResult.executionError());
                            }
                        });
                        allViolations.addAll(violations);
                        ruleResultsWithError.addAll(analysisResult.ruleResults().stream().filter(r -> r.errors().size() > 0).collect(Collectors.toList()));
                    });

                } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
                    System.err.printf("Error while reading file %s%n", fullPath);
                    e.printStackTrace();
                }
            }
        }

        try {
            if (outputFormat == OutputFormat.SARIF) {
                writeSarifReport(Paths.get(output), rules, filesToAnalyze, allViolations, ruleResultsWithError);
            } else {
                writeViolationsToFile(Paths.get(output), new Result(allViolations, ruleResultsWithError));
            }
        } catch (IOException e) {
            System.err.printf("Failed to write result into file %s%n", output);
            e.printStackTrace();
            System.exit(1);
        }
        long endTimeMs = System.currentTimeMillis();

        System.out.printf("Analysis took %sms%n", endTimeMs - startTimeMs);

        System.exit(0);

    }
}