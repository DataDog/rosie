package io.codiga.analyzer.ast.languages.javascript;

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
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.error.RuleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static io.codiga.metrics.MetricsName.METRIC_DISTRIBUTION_PARSING_TIME_PER_LANGUAGE;

public class JavaScriptAnalyzer extends AnalyzerCommon {


    private Logger logger = LoggerFactory.getLogger(JavaScriptAnalyzer.class);


    public JavaScriptAnalyzer(MetricsInterface metrics, ErrorReportingInterface errorReporting, AnalyzerConfiguration configuration) {
        super(metrics, errorReporting, configuration);
    }


    /**
     * Execute the code to analyze
     * - Spin up a JavaScript VM
     * - For each rule
     * 1. Create an execution environment
     * 2.  Execute the code
     * 3. Add violations
     * <p>
     * - Make sure the execution is cleaned up
     *
     * @param analyzerContext
     * @param rule
     * @return
     */
    @Trace(operationName = "JavaScriptAnalyzer.execute")
    @Override
    public RuleResult execute(AnalyzerContext analyzerContext, AnalyzerRule rule) {
        long startTimestamp = System.currentTimeMillis();

        JavaScriptAnalyzerContext context = (JavaScriptAnalyzerContext) analyzerContext;
        VmContext vmContext = new VmContext(analyzerContext);

        vmContext.initializeRule(rule);

        List<AstElement> astElements = context.getElementsToCheck(rule.entityChecked());

        addVariablesToElements(astElements, rule.variables());

        // no object to analyze, return directly and do not waste resource allocating a VM
        if (astElements.isEmpty()) {
            return new RuleResult(rule.name(), List.of(), List.of(), null, null, 0);
        }

        vmContext.prepareForExecution(analyzerContext, astElements);
        vmContext.execute(rule);

        // Get the output BEFORE we shutdown the VM
        String finalOutput = vmContext.getOutput();

        // Then, shutdown the VM
        vmContext.shutdown();
        long endTimestamp = System.currentTimeMillis();
        long executionTimeMs = endTimestamp - startTimestamp;
        return new RuleResult(rule.name(), vmContext.getViolations(), List.of(), null, finalOutput, executionTimeMs);
    }


    @Trace(operationName = "JavaScriptAnalyzer.buildContext")
    @Override
    public AnalyzerContext buildContext(Language language, String filename, String code, List<AnalyzerRule> rules, AnalysisOptions options) {
        long startTimestamp = System.currentTimeMillis();
        JavaScriptAnalyzerContext javaScriptAnalyzerContext = new JavaScriptAnalyzerContext(language, filename, code, rules, options);
        long endTimestamp = System.currentTimeMillis();
        long executionTimeMs = endTimestamp - startTimestamp;
        this.metrics.recordDistribution(String.format("%s-%s", METRIC_DISTRIBUTION_PARSING_TIME_PER_LANGUAGE, language.toString().toLowerCase()), executionTimeMs);
        return javaScriptAnalyzerContext;
    }
}
