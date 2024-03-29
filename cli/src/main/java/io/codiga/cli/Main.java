package io.codiga.cli;

/*
 * Unless explicitly stated otherwise all files in this repository are licensed under the Apache
 * License, Version 2.0 License.
 *
 * This product includes software developed at Datadog (https://www.datadoghq.com/ Copyright 2023
 * Datadog, Inc.
 */

import static io.codiga.cli.CliConstants.WARMUP_LOOPS;
import static io.codiga.cli.FileUtils.filterFilesByExtensions;
import static io.codiga.cli.FileUtils.getFileContent;
import static io.codiga.cli.FileUtils.writeSarifReport;
import static io.codiga.cli.FileUtils.writeViolationsToFile;
import static io.codiga.cli.config.Configuration.DATADOG_CONFIGURATION_FILE;
import static io.codiga.cli.config.ConfigurationUtils.getConfigurationFromFile;
import static io.codiga.cli.utils.DatadogUtils.getRulesFromDatadog;
import static io.codiga.cli.utils.PathUtils.checkIfPathMatches;
import static io.codiga.cli.utils.RulesUtils.getRulesFromFile;
import static io.codiga.cli.utils.RulesUtils.separateRules;
import static io.codiga.constants.Languages.LANGUAGE_EXTENSIONS;
import static io.codiga.utils.CompletableFutureUtils.sequence;
import static io.codiga.warmup.AnalyzerWarmup.warmupAnalyzer;

import io.codiga.analyzer.AnalysisOptions;
import io.codiga.analyzer.Analyzer;
import io.codiga.analyzer.config.AnalyzerConfiguration;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.cli.config.Configuration;
import io.codiga.cli.errorreporting.ErrorReportingDummy;
import io.codiga.cli.metrics.MetricsDummy;
import io.codiga.cli.model.OutputFormat;
import io.codiga.cli.model.Result;
import io.codiga.cli.model.ViolationWithFilename;
import io.codiga.model.Language;
import io.codiga.model.error.AnalysisResult;
import io.codiga.model.error.RuleResult;
import io.codiga.utils.Version;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

  private static final int DEFAULT_TIMEOUT_MS = 50000;

  static void showRules(List<AnalyzerRule> rules) {
    System.out.println("Loaded rules");
    System.out.println("============");
    rules.forEach(
        r -> {
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

    Option optionDirectory =
        Option.builder()
            .required(true)
            .option("i")
            .longOpt("directory")
            .hasArg(true)
            .desc("directory to scan (valid existing directory)")
            .build();
    Option optionRules =
        Option.builder()
            .required(false)
            .option("r")
            .longOpt("rules")
            .hasArg(true)
            .desc("rules to use (path to JSON file)")
            .build();
    Option optionDebug =
        Option.builder()
            .required(false)
            .option("d")
            .longOpt("debug")
            .hasArg(true)
            .desc("enable debug mode (true/false)")
            .build();
    Option optionTreeSitter =
        Option.builder()
            .required(false)
            .option("t")
            .longOpt("tree-sitter")
            .hasArg(true)
            .desc("enable tree-sitter (true/false)")
            .build();
    Option optionOutput =
        Option.builder()
            .required(true)
            .option("o")
            .longOpt("output")
            .hasArg(true)
            .desc("output file (path to file)")
            .build();
    Option optionOutputFormat =
        Option.builder()
            .required(false)
            .option("f")
            .longOpt("format")
            .hasArg(true)
            .desc("output format (json/sarif)")
            .build();
    Option optionIgnorePath =
        Option.builder()
            .required(false)
            .option("p")
            .longOpt("ignore-path")
            .hasArg(true)
            .desc("paths to ignore")
            .build();
    Option optionTestMode =
        Option.builder()
            .required(false)
            .option("x")
            .longOpt("test-mode")
            .hasArg(true)
            .desc("enable test mode (true/false)")
            .build();

    options.addOption(optionDirectory);
    options.addOption(optionRules);
    options.addOption(optionDebug);
    options.addOption(optionOutput);
    options.addOption(optionTreeSitter);
    options.addOption(optionOutputFormat);
    options.addOption(optionTestMode);
    options.addOption(optionIgnorePath);

    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();
    CommandLine cmd = null; // not a good practice, it serves it purpose

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
    String testModeString = cmd.getOptionValue(optionTestMode);
    String[] ignorePathValuesFromOption = cmd.getOptionValues(optionIgnorePath);
    final List<String> ignorePaths;


    boolean debug = debugString != null && debugString.equalsIgnoreCase("true");
    boolean useTreeSitter =
        useTreeSitterString != null && useTreeSitterString.equalsIgnoreCase("true");
    OutputFormat outputFormat = getOutputFormatFromString(outputFormatString);
    boolean inTestMode = testModeString != null && testModeString.equalsIgnoreCase("true");

    Path directoryPath = Paths.get(directory);

    if (!Files.isDirectory(directoryPath)) {
      System.err.printf("%s is not a directory%n", directory);
      System.exit(1);
    }

    // read the datadog configuration
    Optional<Configuration> configurationFile = Optional.empty();
    Path configurationPath = Paths.get(directory, DATADOG_CONFIGURATION_FILE);
    if (Files.notExists(configurationPath)) {
      configurationPath = Paths.get(directory, DATADOG_CONFIGURATION_FILE.replaceFirst("\\.yml", ".yaml"));
    }

    System.out.println(configurationPath);
    if (Files.isReadable(configurationPath) && Files.isRegularFile(configurationPath)) {
      configurationFile = getConfigurationFromFile(new File(configurationPath.toUri()));
    }
    
    // no configuration and no rule file = no analysis
    if (rulesFile == null && configurationFile.isEmpty()) {
      System.err.println("no valid rule file specified and no configuration file");
      System.exit(1);
    }

    // ignore paths and configuration specified = no analysis
    if(configurationFile.isPresent() && ignorePathValuesFromOption != null && ignorePathValuesFromOption.length > 0) {
      System.err.println("configuration file detected, ignore-path option cannot be used");
      System.exit(1);
    }

    if (configurationFile.isPresent()) {
      ignorePaths = configurationFile.get().getIgnorePaths();
    } else {
      ignorePaths = ignorePathValuesFromOption == null ? List.of() : Arrays.stream(ignorePathValuesFromOption).toList();
    }

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
    System.out.printf("Ignore paths  : %s%n", String.join(",", ignorePaths));
    System.out.printf("Test mode     : %s%n", inTestMode);

    // read the rules
    List<AnalyzerRule> rules = List.of();

    if (rulesFile != null) {
      try {
        Path rulesFilePath = Paths.get(rulesFile);
        if (!Files.isReadable(rulesFilePath) || !Files.isRegularFile(rulesFilePath)) {
          System.err.printf("%s is not a readable file%n", rulesFile);
          System.exit(1);
        }
        rules = getRulesFromFile(rulesFile);
      } catch (IOException e) {
        System.err.printf(
            "Error when trying to read the rules from file %s: %s%n", rulesFile, e.getMessage());
        e.printStackTrace();
        System.exit(1);
      }
    } else {
      rules = getRulesFromDatadog(configurationFile.get());
    }

    // if no rule, do not analyze anything
    if (rules.isEmpty()) {
      System.err.println("no rule found");
      System.exit(1);
    }

    showRules(rules);

    // get the list of files to analyze
    List<Path> filesToAnalyze = List.of();
    try {
      filesToAnalyze =
          Files.walk(Paths.get(directory))
              .filter(
                  path ->
                      Files.isRegularFile(path)
                          && !checkIfPathMatches(
                              ignorePaths, directoryPath.relativize(path).toString()))
              .collect(Collectors.toList());
    } catch (IOException e) {
      System.err.println("Error when getting the list of files");
      System.exit(1);
    }

    if (debug) {
      System.out.println(Paths.get(directory));
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
    AnalysisOptions analysisOptions =
        AnalysisOptions.builder().useTreeSitter(useTreeSitter).build();

    // For each language, we get the list of file for this language and get the violations
    for (Map.Entry<Language, List<String>> entry : LANGUAGE_EXTENSIONS.entrySet()) {
      if (debug) {
        System.out.printf("Processing %s%n", entry.getKey());
      }

      // Get the list of files to analyze
      List<Path> filesForLanguage = filterFilesByExtensions(filesToAnalyze, entry.getValue());

      // Get the list of rules for this language
      List<AnalyzerRule> rulesForLanguage =
          rules.stream().filter(r -> r.language() == entry.getKey()).toList();

      // For each file
      for (Path path : filesForLanguage) {
        String relativePath = directoryPath.relativize(path).toString();
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
              subList
                  .get(i)
                  .forEach(
                      r -> {
                        System.out.printf("   %s%n", r.name());
                      });
            }
          }

          // Analyze the file with one thread that is sharing
          List<CompletableFuture<AnalysisResult>> futures =
              subList.stream()
                  .map(
                      ruleList ->
                          analyzer.analyze(
                              entry.getKey(), basename, code, ruleList, analysisOptions))
                  .toList();

          List<AnalysisResult> analysisResultList =
              sequence(futures).get(configuration.analysisTimeoutMs, TimeUnit.MILLISECONDS);

          analysisResultList.forEach(
              analysisResult -> {
                List<ViolationWithFilename> violations =
                    analysisResult.ruleResults().stream()
                        .flatMap(
                            ruleResult ->
                                ruleResult.violations().stream()
                                    .map(
                                        violation -> {
                                          return ViolationWithFilename.builder()
                                              .start(violation.start)
                                              .end(violation.end)
                                              .rule(ruleResult.identifier())
                                              .message(violation.message)
                                              .severity(violation.severity)
                                              .category(violation.category)
                                              .filename(relativePath)
                                              .fixes(violation.fixes)
                                              .build();
                                        }))
                        .toList();
                analysisResult
                    .ruleResults()
                    .forEach(
                        ruleResult -> {
                          if (debug) {
                            System.out.printf(
                                "rule %s on file %s took %s ms%n",
                                ruleResult.identifier(),
                                relativePath,
                                ruleResult.executionTimeMs());
                          }
                          if (ruleResult.errors().size() > 0) {
                            System.out.printf(
                                "rule %s on file %s reported errors %s%n",
                                ruleResult.identifier(),
                                relativePath,
                                String.join(",", ruleResult.errors()));
                          }
                          if (ruleResult.executionError() != null) {
                            System.out.printf(
                                "rule %s on file %s execution error: %s%n",
                                ruleResult.identifier(), relativePath, ruleResult.executionError());
                          }
                        });
                allViolations.addAll(violations);
                ruleResultsWithError.addAll(
                    analysisResult.ruleResults().stream()
                        .filter(r -> r.errors().size() > 0)
                        .collect(Collectors.toList()));
              });

        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
          System.err.printf("Error while reading file %s%n", path);
          e.printStackTrace();
        }
      }
    }

    try {
      if (outputFormat == OutputFormat.SARIF) {
        writeSarifReport(
            Paths.get(output), rules, filesToAnalyze, allViolations, ruleResultsWithError);
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

    // in tests, we want to test the output, so don't exit
    if (!inTestMode) {
      System.exit(0);
    }
  }
}
