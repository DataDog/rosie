package io.codiga.analyzer.pattern;

import io.codiga.analyzer.AnalyzerFuturePool;
import io.codiga.analyzer.ast.languages.AnalyzerCommon;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.error.AnalysisResult;
import io.codiga.model.error.RuleResult;
import io.codiga.model.error.Violation;
import org.graalvm.polyglot.PolyglotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static io.codiga.analyzer.ast.vm.VmUtils.formatVmErrorMessage;
import static io.codiga.model.ErrorCode.*;
import static io.codiga.utils.CompletableFutureUtils.sequence;

public class PatternAnalyzer extends AnalyzerCommon {

    private AnalyzerFuturePool pool = AnalyzerFuturePool.getInstance();

    private Logger logger = LoggerFactory.getLogger(PatternAnalyzer.class);

    public CompletableFuture<AnalysisResult> analyze(String filename, String code, List<AnalyzerRule> rules) {
        List<Long> linesToIgnore = getCommentsLine(code, COMMENT_SHARP);
        List<CompletableFuture<RuleResult>> futures = rules.stream().map(rule -> {
            CompletableFuture<RuleResult> future = CompletableFuture.supplyAsync(() -> {
                    PatternMatcher patternMatcher = new PatternMatcher(code, rule);
                    return new RuleResult(rule.name(), patternMatcher.getViolations(), List.of(), null);
                }, pool.service)
                .orTimeout(getTimeout(), TimeUnit.MILLISECONDS)
                .exceptionally(exception -> {
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
