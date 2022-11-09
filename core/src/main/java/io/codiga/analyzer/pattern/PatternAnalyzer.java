package io.codiga.analyzer.pattern;

import datadog.trace.api.Trace;
import io.codiga.analyzer.AnalyzerFuturePool;
import io.codiga.analyzer.ast.common.AnalyzerCommon;
import io.codiga.analyzer.ast.common.AnalyzerContext;
import io.codiga.analyzer.ast.vm.VmContext;
import io.codiga.analyzer.config.AnalyzerConfiguration;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.errorreporting.ErrorReportingInterface;
import io.codiga.metrics.MetricsInterface;
import io.codiga.model.Language;
import io.codiga.model.error.RuleResult;
import io.codiga.model.pattern.PatternObject;
import org.graalvm.polyglot.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PatternAnalyzer extends AnalyzerCommon {

    private AnalyzerFuturePool pool = AnalyzerFuturePool.getInstance();

    private Logger logger = LoggerFactory.getLogger(PatternAnalyzer.class);

    private Engine engine = Engine.create("js");

    public PatternAnalyzer(MetricsInterface metrics, ErrorReportingInterface errorReporting, AnalyzerConfiguration configuration) {
        super(metrics, errorReporting, configuration);
    }

    @Trace
    @Override
    public RuleResult execute(AnalyzerContext analyzerContext, AnalyzerRule rule) {
        long startTimestamp = System.currentTimeMillis();
        PatternMatcher patternMatcher = new PatternMatcher(analyzerContext.getCode(), rule);
        List<PatternObject> patternObjects = patternMatcher.getPatternObjects();

        // no pattern to match, return directly
        if (patternObjects.isEmpty()) {
            return new RuleResult(rule.name(), List.of(), List.of(), null, null, 0);
        }

        VmContext vmContext = new VmContext(analyzerContext);
        vmContext.initializeRule(rule);


        vmContext.prepareForExecution(analyzerContext, patternObjects);
        vmContext.execute(rule);


        String output = vmContext.getOutput();
        vmContext.shutdown();

        long endTimestamp = System.currentTimeMillis();
        long executionTimeMs = endTimestamp - startTimestamp;
        return new RuleResult(rule.name(), vmContext.getViolations(), List.of(), null, output, executionTimeMs);
    }


    @Trace
    @Override
    public AnalyzerContext buildContext(Language language, String filename, String code, List<AnalyzerRule> rules, boolean logOutput) {
        return new AnalyzerContext(language, filename, code, rules, logOutput);
    }

}
