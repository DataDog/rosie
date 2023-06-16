package io.codiga.analyzer.pattern;

import datadog.trace.api.Trace;
import io.codiga.analyzer.AnalysisOptions;
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
import java.util.List;

public class PatternAnalyzer extends AnalyzerCommon {



    public PatternAnalyzer(MetricsInterface metrics, ErrorReportingInterface errorReporting, AnalyzerConfiguration configuration) {
        super(metrics, errorReporting, configuration);
    }

    @Trace(operationName = "PatternAnalyzer.execute")
    @Override
    public RuleResult execute(AnalyzerContext analyzerContext, AnalyzerRule rule) {
        long startTimestamp = System.currentTimeMillis();
        PatternMatcher patternMatcher = new PatternMatcher(analyzerContext.getCode(), rule);
        List<PatternObject> patternObjects = patternMatcher.getPatternObjects();

        // no pattern to match, return directly
        if (patternObjects.isEmpty()) {
            return new RuleResult(rule.name, List.of(), List.of(), null, null, 0);
        }

        VmContext vmContext = new VmContext(analyzerContext);
        vmContext.initializeRule(rule);


        vmContext.prepareForExecution(analyzerContext, patternObjects);
        vmContext.execute(rule);


        String output = vmContext.getOutput();
        vmContext.shutdown();

        long endTimestamp = System.currentTimeMillis();
        long executionTimeMs = endTimestamp - startTimestamp;
        return new RuleResult(rule.name, vmContext.getViolations(), List.of(), null, output, executionTimeMs);
    }


    @Trace(operationName = "PatternAnalyzer.buildContext")
    @Override
    public AnalyzerContext buildContext(Language language, String filename, String code, List<AnalyzerRule> rules, AnalysisOptions options) {
        return new AnalyzerContext(language, filename, code, rules, options);
    }

}
