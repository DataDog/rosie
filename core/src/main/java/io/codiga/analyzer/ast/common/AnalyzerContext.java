package io.codiga.analyzer.ast.common;

import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.Language;
import org.antlr.v4.runtime.Parser;
import org.graalvm.polyglot.Engine;

import java.util.List;

/**
 * This class holds all the context that is built BEFORE all threads are executed.
 * We get all the data that needs to be computed by all tasks before launching them here.
 * For example, for an AST rule, we build the AST prior to starting the analysis.
 */
public class AnalyzerContext {
    Parser parser = null;
    Language language = null;
    String filename = null;
    String code = null;
    List<AnalyzerRule> rules = null;
    boolean logOutput = false;
    Engine engine;


    public AnalyzerContext(Language language, String filename, String code, List<AnalyzerRule> rules, boolean logOutput) {
        this.language = language;
        this.filename = filename;
        this.code = code;
        this.rules = rules;
        this.logOutput = logOutput;
        this.engine = Engine.newBuilder("js")
            .allowExperimentalOptions(true)
            .option("engine.WarnInterpreterOnly", "false") // no warning when we are attempting to run the engine

            .build();

    }

    public Engine getEngine() {
        return this.engine;
    }

    public Parser getParser() {
        return this.parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public String getCode() {
        return this.code;
    }

    public Language getLanguage() {
        return this.language;
    }

    public String getFilename() {
        return filename;
    }

    public boolean isLogOutput() {
        return logOutput;
    }
}
