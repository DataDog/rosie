package io.codiga.analyzer;

import com.google.common.collect.ImmutableList;
import io.codiga.analyzer.languages.python.PythonAnalyzer;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.Language;
import io.codiga.model.error.AnalysisResult;
import io.codiga.model.error.RuleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static io.codiga.model.ErrorCode.ERROR_RULE_LANGUAGE_MISMATCH;

/**
 * This is the central analyzer. It receives requests to analyze code and dispatch
 * to the appropriate language-specific analyzer.
 */
public class Analyzer {

    Logger logger = LoggerFactory.getLogger(Analyzer.class);
    PythonAnalyzer pythonAnalyzer = new PythonAnalyzer();

    public CompletableFuture<AnalysisResult> analyze(Language language, String filename, String code, List<AnalyzerRule> rules) {
        // Distinguish between rules with valid languages and invalid ones.
        List<AnalyzerRule> rulesWithValidLanguage = rules.stream().filter(f -> f.language() == language).toList();
        List<AnalyzerRule> rulesWithInvalidLanguage = rules.stream().filter(f -> f.language() != language).toList();
        CompletableFuture<AnalysisResult> completedResult;

        switch (language) {
            case PYTHON:
                completedResult = pythonAnalyzer.analyze(filename, code, rulesWithValidLanguage);
                break;
            default:
                completedResult = CompletableFuture.completedFuture(new AnalysisResult(List.of()));
                break;
        }

        // Return an error for the rule with an invalid language
        return completedResult.thenApply(result -> {
            List<RuleResult> invalidLanguagesRuleResults = rulesWithInvalidLanguage.stream().map(r -> {
                return new RuleResult(r.name(), List.of(), List.of(ERROR_RULE_LANGUAGE_MISMATCH), null);
            }).toList();
            List<RuleResult> allRuleResult = ImmutableList
                .<RuleResult>builder()
                .addAll(result.ruleResults())
                .addAll(invalidLanguagesRuleResults).build();
            return new AnalysisResult(allRuleResult);
        });
    }
}
