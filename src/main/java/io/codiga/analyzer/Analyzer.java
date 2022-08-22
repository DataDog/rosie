package io.codiga.analyzer;

import io.codiga.analyzer.languages.python.PythonAnalyzer;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.error.AnalysisError;
import io.codiga.parser.python.CodigaVisitor;
import io.codiga.parser.python.gen.PythonLexer;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Analyzer {

    PythonAnalyzer pythonAnalyzer = new PythonAnalyzer();
    public CompletableFuture<List<AnalysisError>> analyze(String language, String filename, String code, List<AnalyzerRule> rules) {
        if (language.equalsIgnoreCase("python")){
            return pythonAnalyzer.analyze(filename, code, rules);
        }
        return CompletableFuture.completedFuture(List.of());

    }
}
