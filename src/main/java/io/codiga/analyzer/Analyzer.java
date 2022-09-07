package io.codiga.analyzer;

import com.google.common.collect.ImmutableList;
import io.codiga.analyzer.ast.languages.python.PythonAnalyzer;
import io.codiga.analyzer.pattern.PatternAnalyzer;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.model.error.AnalysisResult;
import io.codiga.model.error.RuleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static io.codiga.model.ErrorCode.ERROR_RULE_LANGUAGE_MISMATCH;
import static io.codiga.utils.CompletableFutureUtils.sequence;

/**
 * This is the central analyzer. It receives requests to analyze code and dispatch
 * to the appropriate language-specific analyzer.
 */
public class Analyzer {

    Logger logger = LoggerFactory.getLogger(Analyzer.class);
    PythonAnalyzer pythonAnalyzer = new PythonAnalyzer();
    PatternAnalyzer patternAnalyzer = new PatternAnalyzer();

    public CompletableFuture<AnalysisResult> analyze(Language language, String filename, String code, List<AnalyzerRule> rules, boolean logOutput) {
        // Distinguish between rules with valid languages and invalid ones.
        List<AnalyzerRule> rulesWithValidLanguage = rules.stream().filter(f -> f.language() == language).toList();
        List<AnalyzerRule> rulesWithValidLanguageForAst = rulesWithValidLanguage.stream().filter(r -> r.ruleType() == RuleType.AST_CHECK).toList();
        List<AnalyzerRule> rulesWithValidLanguageForPattern = rulesWithValidLanguage.stream().filter(r -> r.ruleType() == RuleType.PATTERN && r.pattern() != null).toList();
        List<AnalyzerRule> rulesWithInvalidLanguage = rules.stream().filter(f -> f.language() != language).toList();
        CompletableFuture<AnalysisResult> completedResultForAst;

        // First, AST analysis
        switch (language) {
            case PYTHON:
                completedResultForAst = pythonAnalyzer.analyze(language, filename, code, rulesWithValidLanguageForAst, logOutput);
                break;
            default:
                completedResultForAst = CompletableFuture.completedFuture(new AnalysisResult(List.of()));
                break;
        }

        // Second, pattern analysis
        CompletableFuture<AnalysisResult> patternAnalysis = patternAnalyzer.analyze(language, filename, code, rulesWithValidLanguageForPattern, logOutput);

        CompletableFuture<List<AnalysisResult>> allFutures = sequence(List.of(patternAnalysis, completedResultForAst));

        // Return an error for the rule with an invalid language
        return allFutures.thenApply(result -> {
            List<RuleResult> invalidLanguagesRuleResults = rulesWithInvalidLanguage.stream().map(r -> {
                return new RuleResult(r.name(), List.of(), List.of(ERROR_RULE_LANGUAGE_MISMATCH), null, null);
            }).toList();
            List<RuleResult> allRuleResult = ImmutableList
                .<RuleResult>builder()
                .addAll(result.stream().flatMap(r -> r.ruleResults().stream()).toList())
                .addAll(invalidLanguagesRuleResults).build();
            return new AnalysisResult(allRuleResult);
        });
    }
}
