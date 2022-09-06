package io.codiga.analyzer.ast.common;

import io.codiga.analyzer.AnalyzerFuturePool;
import io.codiga.analyzer.rule.AnalyzerRule;
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

import static io.codiga.analyzer.ast.vm.VmUtils.formatVmErrorMessage;
import static io.codiga.model.ErrorCode.*;
import static io.codiga.utils.CompletableFutureUtils.sequence;
import static io.codiga.utils.EnvironmentUtils.ANALYSIS_TIMEOUT;
import static io.codiga.utils.EnvironmentUtils.getEnvironmentValueAsLong;

public abstract class AnalyzerCommon {

    public final static String COMMENT_SHARP = "#";
    private final long DEFAULT_TIMEOUT_MS = 1000;

    private Logger logger = LoggerFactory.getLogger(AnalyzerCommon.class);
    private AnalyzerFuturePool pool = AnalyzerFuturePool.getInstance();


    protected long getTimeout() {
        return getEnvironmentValueAsLong(ANALYSIS_TIMEOUT).orElse(DEFAULT_TIMEOUT_MS);
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
        switch (language) {
            case PYTHON:
                return COMMENT_SHARP;
            default:
                return COMMENT_SHARP;
        }
    }

    public abstract RuleResult execute(String filename, String code, AnalyzerRule rule);

    public CompletableFuture<AnalysisResult> analyze(Language language, String filename, String code, List<AnalyzerRule> rules) {
        // Get the lines to ignore that have codiga-disable
        List<Long> linesToIgnore = getCommentsLine(code, getCommentsSymbol(language));


        List<CompletableFuture<RuleResult>> futures = rules.stream().map(rule -> {
            CompletableFuture<RuleResult> future = CompletableFuture
                .supplyAsync(() -> execute(filename, code, rule), pool.service)
                .orTimeout(getTimeout(), TimeUnit.MILLISECONDS)
                .exceptionally(exception -> {
                    logger.info("caught exception: " + exception.getMessage());
                    if (exception instanceof TimeoutException) {
                        logger.error(String.format("reporting rule %s as timeout", rule.name()));
                        return new RuleResult(rule.name(), List.of(), List.of(ERROR_RULE_TIMEOUT), null);
                    }

                    if (exception.getCause() != null && exception.getCause() instanceof PolyglotException) {
                        String executionMessage = formatVmErrorMessage(exception.getMessage());
                        logger.error(String.format("reporting rule %s as execution error", rule.name()));
                        exception.printStackTrace();
                        return new RuleResult(rule.name(), List.of(), List.of(ERROR_RULE_EXECUTION), executionMessage);
                    }

                    logger.error(String.format("unhandled exception for rule %s: %s - %s", rule.name(), exception, exception.getMessage()));

                    return new RuleResult(rule.name(), List.of(), List.of(ERROR_RULE_UNKNOWN), null);
                });
            return future;
        }).toList();


        return sequence(futures).thenApply(finalList -> {

            logger.info("final list: " + finalList);
            // ignore the violations being ignored
            List<RuleResult> fileteredList = finalList.stream().map(ruleResult -> {
                List<Violation> filteredViolations = ruleResult.violations().stream().filter(v -> {
                    if (v.start() != null) {
                        long previousLine = v.start().line - 1;
                        return !linesToIgnore.contains(previousLine);
                    }
                    return false;
                }).toList();
                return new RuleResult(ruleResult.identifier(), filteredViolations, ruleResult.errors(), ruleResult.executionError());
            }).toList();
            return new AnalysisResult(fileteredList);
        });
    }
}
