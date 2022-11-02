package io.codiga.analyzer.ast.languages.python;

import io.codiga.analyzer.ast.common.AnalyzerCommon;
import io.codiga.analyzer.ast.common.AnalyzerContext;
import io.codiga.analyzer.ast.vm.VmContext;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.errorreporting.ErrorReportingInterface;
import io.codiga.metrics.MetricsInterface;
import io.codiga.model.Language;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.error.RuleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PythonAnalyzer extends AnalyzerCommon {


    private Logger logger = LoggerFactory.getLogger(PythonAnalyzer.class);


    public PythonAnalyzer(MetricsInterface metrics, ErrorReportingInterface errorReporting) {
        super(metrics, errorReporting);
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
    @Override
    public RuleResult execute(AnalyzerContext analyzerContext, AnalyzerRule rule) {
        long startTimestamp = System.currentTimeMillis();

        PythonAnalyzerContext context = (PythonAnalyzerContext) analyzerContext;
        VmContext vmContext = new VmContext(analyzerContext);

        vmContext.initializeRule(rule);

        List<AstElement> astElements = context.entityCheckedToAstElements.get(rule.entityChecked());

        // no object to analyze, return directly and do not waste resource allocating a VM
        if (astElements.isEmpty()) {
            return new RuleResult(rule.name(), List.of(), List.of(), null, null, 0);
        }

        astElements.forEach(astElement -> {
            vmContext.prepareForExecution(analyzerContext, astElement);
            vmContext.execute(rule);
        });

        // Get the output BEFORE we shutdown the VM
        String finalOutput = vmContext.getOutput();

        // Then, shutdown the VM
        vmContext.shutdown();
        long endTimestamp = System.currentTimeMillis();
        long executionTimeMs = endTimestamp - startTimestamp;
        return new RuleResult(rule.name(), vmContext.getViolations(), List.of(), null, finalOutput, executionTimeMs);
    }

    @Override
    public void prepareExecution(String filename, String code, AnalyzerRule rule, boolean logOutput) {

    }

    @Override
    public AnalyzerContext buildContext(Language language, String filename, String code, List<AnalyzerRule> rules, boolean logOutput) {

        /**
         * Build the parser to execute all rules.
         */
        PythonAnalyzerContext analyzerContext = new PythonAnalyzerContext(language, filename, code, rules, logOutput);

        return analyzerContext;
    }


}
