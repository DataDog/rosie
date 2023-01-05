package io.codiga.analyzer.ast.common;

import datadog.trace.api.Trace;
import io.codiga.analyzer.AnalyzerFuturePool;
import io.codiga.analyzer.config.AnalyzerConfiguration;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.errorreporting.ErrorReportingInterface;
import io.codiga.metrics.MetricsInterface;
import io.codiga.model.Language;
import io.codiga.model.error.AnalysisResult;
import io.codiga.model.error.RuleResult;
import io.codiga.model.error.Violation;
import org.graalvm.polyglot.PolyglotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

import static io.codiga.analyzer.ast.vm.VmUtils.formatVmErrorMessage;
import static io.codiga.metrics.MetricsName.METRIC_HISTOGRAM_REQUEST_ANALYSIS_TIME_PREFIX;
import static io.codiga.metrics.MetricsName.METRIC_RULE_EXECUTION_UNKNOWN_ERROR;
import static io.codiga.model.RuleErrorCode.*;
import static io.codiga.utils.EnvironmentUtils.ANALYSIS_TIMEOUT;
import static io.codiga.utils.EnvironmentUtils.getEnvironmentValueAsLong;

public abstract class AnalyzerCommon {

    public final static String COMMENT_SHARP = "#";
    public final static String COMMENT_TWO_SLASHES = "//";

    private final Logger logger = LoggerFactory.getLogger(AnalyzerCommon.class);
    private final AnalyzerFuturePool pool = AnalyzerFuturePool.getInstance();

    private final MetricsInterface metrics;
    private final ErrorReportingInterface errorReporting;
    private final AnalyzerConfiguration analyzerConfiguration;

    public AnalyzerCommon(MetricsInterface metrics, ErrorReportingInterface errorReporting, AnalyzerConfiguration configuration) {
        this.metrics = metrics;
        this.errorReporting = errorReporting;
        this.analyzerConfiguration = configuration;
    }


    protected long getTimeoutMs() {
        return getEnvironmentValueAsLong(ANALYSIS_TIMEOUT).orElse(this.analyzerConfiguration.analysisTimeoutMs);
    }

    public List<Long> getCommentsLine(String code, String commentStart) {
        String commentLine = String.format("%scodiga-disable", commentStart);

        ArrayList<Long> comments = new ArrayList<>();
        Scanner scanner = new Scanner(code);
        long lineNumber = 1;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String lineStripped = line.replaceAll(" ", "");
            if (lineStripped.equalsIgnoreCase(commentLine)) {
                comments.add(lineNumber);
            }
            lineNumber = lineNumber + 1;
        }
        scanner.close();
        return comments;

    }


    public String getCommentsSymbol(Language language) {
        if (language == Language.PYTHON) {
            return COMMENT_SHARP;
        }
        if (language == Language.JAVASCRIPT) {
            return COMMENT_TWO_SLASHES;
        }
        if (language == Language.TYPESCRIPT) {
            return COMMENT_TWO_SLASHES;
        }
        return COMMENT_TWO_SLASHES;
    }

    public abstract RuleResult execute(AnalyzerContext analyzerContext, AnalyzerRule rule);

    public abstract AnalyzerContext buildContext(Language language, String filename, String code, List<AnalyzerRule> rules, boolean logOutput);

    @Trace(operationName = "AnalyzerCommon.analyze")
    public CompletableFuture<AnalysisResult> analyze(Language language, String filename, String code, List<AnalyzerRule> rules, boolean logOutput) {
        long timeoutMs = getTimeoutMs();
        // Get the lines to ignore that have codiga-disable
        List<Long> linesToIgnore = getCommentsLine(code, getCommentsSymbol(language));

        // Build execution, get the parser if this is an AST rule.
        AnalyzerContext analyzerContext = buildContext(language, filename, code, rules, logOutput);

        CompletableFuture<AnalysisResult> result = CompletableFuture.supplyAsync(() -> {
                List<RuleResult> ruleResults = rules.stream().map(rule -> {
                    long startTime = System.currentTimeMillis();
                    try {
                        RuleResult res = execute(analyzerContext, rule);
                        long endTime = System.currentTimeMillis();
                        long executionTime = endTime - startTime;
                        String metricName = String.format("%s-%s", METRIC_HISTOGRAM_REQUEST_ANALYSIS_TIME_PREFIX, rule.name());
                        metrics.histogramValue(metricName, (double) executionTime);
//                        logger.info(String.format("rule %s took %s ms to execute", rule.name(), executionTime));
                        return res;
                    } catch (PatternSyntaxException patternSyntaxException) {
                        logger.error(String.format("reporting rule %s as invalid-pattern", rule.name()));
                        return new RuleResult(rule.name(), List.of(), List.of(ERROR_INVALID_PATTERN), null, null, 0);
                    } catch (PolyglotException polyglotException) {
                        long endTime = System.currentTimeMillis();
                        long executionTime = endTime - startTime;
                        if (polyglotException.getMessage() != null && polyglotException.getMessage().contains("Statement count limit of") && polyglotException.getMessage().contains("Statements executed")) {
                            logger.info(String.format("rule %s timedout because of instructions", rule.name()));
                            return new RuleResult(rule.name(), List.of(), List.of(ERROR_RULE_TIMEOUT), null, null, executionTime);
                        }
                        String executionMessage = "unknown error";
                        if (polyglotException.getMessage() != null) {
                            executionMessage = formatVmErrorMessage(polyglotException.getMessage());
                        }
                        logger.error(String.format("reporting rule %s as execution error", rule.name()));
                        polyglotException.printStackTrace();
                        return new RuleResult(rule.name(), List.of(), List.of(ERROR_RULE_EXECUTION), executionMessage, null, executionTime);
                    } catch (Exception unknownException) {
                        long endTime = System.currentTimeMillis();
                        long executionTime = endTime - startTime;
                        this.metrics.incrementMetric(METRIC_RULE_EXECUTION_UNKNOWN_ERROR);
                        this.errorReporting.reportError(unknownException, String.format("error unknown exception rule %s", rule.name()));
                        return new RuleResult(rule.name(), List.of(), List.of(ERROR_RULE_UNKNOWN), unknownException.getMessage(), null, executionTime);
                    }

                }).collect(Collectors.toList());
                return ruleResults;
            }, pool.service)
            .thenApply(finalList -> {
                // ignore the violations being ignored
                List<RuleResult> fileteredList = finalList.stream().map(ruleResult -> {
                    List<Violation> filteredViolations = ruleResult.violations().stream().filter(v -> {
                        if (v.start != null) {
                            long previousLine = v.start.line - 1;
                            return !linesToIgnore.contains(previousLine);
                        }
                        return false;
                    }).toList();
                    if (ruleResult.output() != null) {
                        logger.debug(String.format("Output for rule %s: %s", ruleResult.identifier(), ruleResult.output()));
                    }
                    return new RuleResult(ruleResult.identifier(), filteredViolations, ruleResult.errors(), ruleResult.executionError(), ruleResult.output(), ruleResult.executionTimeMs());
                }).toList();
                return new AnalysisResult(fileteredList);
            })
            .orTimeout(timeoutMs, TimeUnit.MILLISECONDS)
            .exceptionally(exception -> {
                if (exception instanceof TimeoutException) {
                    logger.error(String.format("analysis for rules %s timeout", String.join(",", rules.stream().map(r -> r.name()).toList())));
                    List<RuleResult> timedoutRuleResults = rules.stream().map(r -> {
                        return new RuleResult(r.name(), List.of(), List.of(ERROR_RULE_TIMEOUT), null, null, timeoutMs);
                    }).collect(Collectors.toList());
                    return new AnalysisResult(timedoutRuleResults);
                }
                logger.error(String.format("analysis for rules %s report an error", String.join(",", rules.stream().map(r -> r.name()).toList())));

                List<RuleResult> erroredRules = rules.stream().map(r -> {
                    return new RuleResult(r.name(), List.of(), List.of(ERROR_RULE_UNKNOWN), exception.getCause() != null ? exception.getCause().getMessage() : exception.getMessage(), null, timeoutMs);
                }).collect(Collectors.toList());
                return new AnalysisResult(erroredRules);
            });
        return result;
    }
}
