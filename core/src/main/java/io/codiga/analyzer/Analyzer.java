package io.codiga.analyzer;

import com.google.common.collect.ImmutableList;
import io.codiga.analyzer.ast.languages.javascript.JavaScriptAnalyzer;
import io.codiga.analyzer.ast.languages.python.PythonAnalyzer;
import io.codiga.analyzer.ast.languages.typescript.TypeScriptAnalyzer;
import io.codiga.analyzer.config.AnalyzerConfiguration;
import io.codiga.analyzer.pattern.PatternAnalyzer;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.errorreporting.ErrorReportingInterface;
import io.codiga.metrics.MetricsInterface;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.model.error.AnalysisResult;
import io.codiga.model.error.RuleResult;
import io.codiga.parser.treesitter.utils.TreeSitterInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static io.codiga.metrics.MetricsName.*;
import static io.codiga.model.RuleErrorCode.ERROR_RULE_INVALID_RULE_TYPE;
import static io.codiga.model.RuleErrorCode.ERROR_RULE_LANGUAGE_MISMATCH;
import static io.codiga.utils.CompletableFutureUtils.sequence;

/**
 * This is the central analyzer. It receives requests to analyze code and dispatch
 * to the appropriate language-specific analyzer.
 */
public class Analyzer {

    private final MetricsInterface metrics;
    private final ErrorReportingInterface errorReporting;
    Logger logger = LoggerFactory.getLogger(Analyzer.class);
    PythonAnalyzer pythonAnalyzer;
    JavaScriptAnalyzer javaScriptAnalyzer;
    TypeScriptAnalyzer typeScriptAnalyzer;
    PatternAnalyzer patternAnalyzer;

    public Analyzer(ErrorReportingInterface errorReporting, MetricsInterface metrics, AnalyzerConfiguration configuration) {
        this.errorReporting = errorReporting;
        this.metrics = metrics;
        this.patternAnalyzer = new PatternAnalyzer(this.metrics, this.errorReporting, configuration);
        this.pythonAnalyzer = new PythonAnalyzer(this.metrics, this.errorReporting, configuration);
        this.javaScriptAnalyzer = new JavaScriptAnalyzer(this.metrics, this.errorReporting, configuration);
        this.typeScriptAnalyzer = new TypeScriptAnalyzer(this.metrics, this.errorReporting, configuration);

        try {
            TreeSitterInit.init();
        } catch (FileNotFoundException fileNotFoundException) {
            logger.info("Shared library not found");
        }

    }

    public CompletableFuture<AnalysisResult> analyze(Language language, String filename, String code, List<AnalyzerRule> rules, AnalysisOptions options) {
        long startAnalysisTimestampMs = System.currentTimeMillis();
        // Distinguish between rules with valid languages and invalid ones.
        List<AnalyzerRule> rulesWithValidLanguage = rules.stream().filter(f -> f.validForLanguage(language)).toList();
        List<AnalyzerRule> rulesWithValidLanguageForAst = rulesWithValidLanguage.stream().filter(r -> r.ruleType() == RuleType.AST_CHECK).toList();
        List<AnalyzerRule> rulesWithValidLanguageForPattern = rulesWithValidLanguage.stream().filter(r -> r.ruleType() == RuleType.PATTERN && r.pattern() != null).toList();
        List<AnalyzerRule> rulesWithInvalidLanguage = rules.stream().filter(f -> !f.validForLanguage(language)).toList();
        CompletableFuture<AnalysisResult> completedResultForAst;

        // First, AST analysis
        switch (language) {
            case PYTHON:
                completedResultForAst = pythonAnalyzer.analyze(language, filename, code, rulesWithValidLanguageForAst, options);
                break;
            case JAVASCRIPT:
                completedResultForAst = javaScriptAnalyzer.analyze(language, filename, code, rulesWithValidLanguageForAst, options);
                break;
            case TYPESCRIPT:
                completedResultForAst = typeScriptAnalyzer.analyze(language, filename, code, rulesWithValidLanguageForAst, options);
                break;
            default:
                completedResultForAst = CompletableFuture.completedFuture(new AnalysisResult(List.of()));
                break;
        }

        // Second, pattern analysis
        CompletableFuture<AnalysisResult> patternAnalysis = patternAnalyzer.analyze(language, filename, code, rulesWithValidLanguageForPattern, options);

        CompletableFuture<List<AnalysisResult>> allFutures = sequence(List.of(patternAnalysis, completedResultForAst));

        // Return an error for the rule with an invalid language
        return allFutures.thenApply(result -> {
            List<RuleResult> invalidLanguagesRuleResults = rulesWithInvalidLanguage.stream().map(r -> new RuleResult(r.name(), List.of(), List.of(ERROR_RULE_LANGUAGE_MISMATCH), null, null, 0)).toList();

            List<RuleResult> invalidRuleTypeResults = rulesWithValidLanguage.stream()
                .filter(r -> r.ruleType() == RuleType.UNKNOWN)
                .map(r -> new RuleResult(r.name(), List.of(), List.of(ERROR_RULE_INVALID_RULE_TYPE), null, null, 0)).toList();

            List<RuleResult> allRuleResult = ImmutableList
                .<RuleResult>builder()
                .addAll(result.stream().flatMap(r -> r.ruleResults().stream()).toList())
                .addAll(invalidLanguagesRuleResults)
                .addAll(invalidRuleTypeResults)
                .build();
            long endAnalysisTimestampMs = System.currentTimeMillis();
            long analysisTimeMs = endAnalysisTimestampMs - startAnalysisTimestampMs;
            // Total analysis time
            metrics.histogramValue(METRIC_HISTOGRAM_REQUEST_ANALYSIS_TIME_TOTAL, analysisTimeMs);
            metrics.recordDistribution(METRIC_DISTRIBUTION_ANALYSIS_TOTAL_TIME, analysisTimeMs);
            // Analysis time per language
            metrics.recordDistribution(String.format("%s-%s", METRIC_DISTRIBUTION_ANALYSIS_TIME_PER_LANGUAGE, language.toString().toLowerCase()), analysisTimeMs);
            return new AnalysisResult(allRuleResult);
        });
    }
}
