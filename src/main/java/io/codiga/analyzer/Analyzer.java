package io.codiga.analyzer;

import io.codiga.analyzer.languages.python.PythonAnalyzer;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.error.AnalysisResult;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Analyzer {

    PythonAnalyzer pythonAnalyzer = new PythonAnalyzer();

    public CompletableFuture<AnalysisResult> analyze(String language, String filename, String code, List<AnalyzerRule> rules) {
        if (language.equalsIgnoreCase("python")) {
            return pythonAnalyzer.analyze(filename, code, rules);
        }
        return CompletableFuture.completedFuture(null);

    }
}
