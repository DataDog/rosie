package io.codiga.analyzer.ast.languages.python;

import io.codiga.analyzer.ast.common.AnalyzerCommon;
import io.codiga.analyzer.ast.common.AnalyzerContext;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.errorreporting.ErrorReportingInterface;
import io.codiga.metrics.MetricsInterface;
import io.codiga.model.Language;
import io.codiga.model.error.RuleResult;
import io.codiga.parser.python.gen.PythonLexer;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static io.codiga.model.ErrorCode.ERROR_RULE_PARSING;

public class PythonAnalyzer extends AnalyzerCommon {


    private Logger logger = LoggerFactory.getLogger(PythonAnalyzer.class);

    public PythonAnalyzer(MetricsInterface metrics, ErrorReportingInterface errorReporting) {
        super(metrics, errorReporting);
    }

    @Override
    public RuleResult execute(AnalyzerContext analyzerContext, AnalyzerRule rule) {
        long startTimestamp = System.currentTimeMillis();
        PythonLexer lexer = new PythonLexer(CharStreams.fromString(analyzerContext.getCode()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PythonParser parser = new PythonParser(tokens);
        parser.setBuildParseTree(true);
        analyzerContext.setParser(parser);
        CodigaVisitor codigaVisitor = new CodigaVisitor(rule, analyzerContext.getCode(), analyzerContext.getFilename(), analyzerContext.isLogOutput());

        try {
            codigaVisitor.visit(((PythonParser) analyzerContext.getParser()).root());
        } catch (StackOverflowError stackOverflowError) {
            long endTimestamp = System.currentTimeMillis();
            long executionTimeMs = endTimestamp - startTimestamp;
            return new RuleResult(rule.name(), List.of(), List.of(ERROR_RULE_PARSING), null, null, executionTimeMs);
        }

        long endTimestamp = System.currentTimeMillis();
        long executionTimeMs = endTimestamp - startTimestamp;
        return new RuleResult(rule.name(), codigaVisitor.getViolations(), List.of(), null, codigaVisitor.getOutput(), executionTimeMs);
    }

    @Override
    public void prepareExecution(String filename, String code, AnalyzerRule rule, boolean logOutput) {

    }

    @Override
    public AnalyzerContext buildContext(Language language, String filename, String code, List<AnalyzerRule> rules, boolean logOutput) {

        /**
         * Build the parser to execute all rules.
         */
        AnalyzerContext analyzerContext = new AnalyzerContext(language, filename, code, rules, logOutput);

        return analyzerContext;
    }


}
