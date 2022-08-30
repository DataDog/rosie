package io.codiga.analyzer;

import io.codiga.analyzer.languages.python.PythonAnalyzer;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.Language;
import io.codiga.model.error.AnalysisResult;
import io.codiga.model.error.RuleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static io.codiga.model.ErrorCode.ERROR_LANGUAGE_MISMATCH;

public class Analyzer {

    Logger logger = LoggerFactory.getLogger(Analyzer.class);
    PythonAnalyzer pythonAnalyzer = new PythonAnalyzer();

    public CompletableFuture<AnalysisResult> analyze(Language language, String filename, String code, List<AnalyzerRule> rules) {
        List<AnalyzerRule> rulesWithValidLanguage = rules.stream().filter(f -> f.language() == language).toList();
        List<AnalyzerRule> rulesWithInvalidLanguage = rules.stream().filter(f -> f.language() != language).toList();
        CompletableFuture<AnalysisResult> completedResult = CompletableFuture.completedFuture(new AnalysisResult(List.of()));

        logger.info("rules with invalid language" + rulesWithInvalidLanguage);

        if (language == Language.PYTHON) {
            completedResult = pythonAnalyzer.analyze(filename, code, rulesWithValidLanguage);
        }

        // Return an error for the rule with an invalid language
        completedResult = completedResult.thenApply(result -> {
            for (AnalyzerRule analyzerRule : rulesWithInvalidLanguage) {
                result.ruleResults().add(new RuleResult(analyzerRule.name(), List.of(), List.of(ERROR_LANGUAGE_MISMATCH)));
            }
            return result;
        });

        return completedResult;
    }
}
