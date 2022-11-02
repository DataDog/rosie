package io.codiga.analyzer.pattern;

import io.codiga.analyzer.AnalyzerFuturePool;
import io.codiga.analyzer.ast.common.AnalyzerCommon;
import io.codiga.analyzer.ast.common.AnalyzerContext;
import io.codiga.analyzer.ast.vm.ExecutionEnvironmentBuilder;
import io.codiga.analyzer.ast.vm.ExecutionResult;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.errorreporting.ErrorReportingInterface;
import io.codiga.metrics.MetricsInterface;
import io.codiga.model.Language;
import io.codiga.model.error.RuleResult;
import io.codiga.model.error.Violation;
import io.codiga.model.pattern.PatternObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static io.codiga.analyzer.ast.vm.VmUtils.buildExecutableCode;

public class PatternAnalyzer extends AnalyzerCommon {

    private AnalyzerFuturePool pool = AnalyzerFuturePool.getInstance();

    private Logger logger = LoggerFactory.getLogger(PatternAnalyzer.class);


    public PatternAnalyzer(MetricsInterface metrics, ErrorReportingInterface errorReporting) {
        super(metrics, errorReporting);
    }


    @Override
    public RuleResult execute(AnalyzerContext analyzerContext, AnalyzerRule rule) {
        long startTimestamp = System.currentTimeMillis();
        PatternMatcher patternMatcher = new PatternMatcher(analyzerContext.getCode(), rule);
        List<PatternObject> patternObjects = patternMatcher.getPatternObjects();
        List<Violation> violations = new ArrayList<>();
        String output = null;

        for (PatternObject patternObject : patternObjects) {

            String finalCode = buildExecutableCode(rule.code());

            ExecutionResult executionResult = new ExecutionEnvironmentBuilder()
                .setCode(analyzerContext.getCode())
                .setRootObject(patternObject)
                .setLogOutput(analyzerContext.isLogOutput())
                .setFilename(analyzerContext.getFilename())
                .createExecutionEnvironment()
                .execute(analyzerContext, rule);


            violations.addAll(executionResult.getViolations());
            output = analyzerContext.getOutput();
        }

        long endTimestamp = System.currentTimeMillis();
        long executionTimeMs = endTimestamp - startTimestamp;
        return new RuleResult(rule.name(), List.copyOf(violations), List.of(), null, output, executionTimeMs);
    }

    @Override
    public void prepareExecution(String filename, String code, AnalyzerRule rule, boolean logOutput) {

    }

    @Override
    public AnalyzerContext buildContext(Language language, String filename, String code, List<AnalyzerRule> rules, boolean logOutput) {
        return new AnalyzerContext(language, filename, code, rules, logOutput);
    }

}
