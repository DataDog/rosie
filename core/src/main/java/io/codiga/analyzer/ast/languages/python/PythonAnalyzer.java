package io.codiga.analyzer.ast.languages.python;

import io.codiga.analyzer.ast.common.AnalyzerCommon;
import io.codiga.analyzer.ast.common.AnalyzerContext;
import io.codiga.analyzer.ast.vm.ExecutionEnvironmentBuilder;
import io.codiga.analyzer.ast.vm.ExecutionResult;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.errorreporting.ErrorReportingInterface;
import io.codiga.metrics.MetricsInterface;
import io.codiga.model.Language;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.error.RuleResult;
import io.codiga.model.error.Violation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PythonAnalyzer extends AnalyzerCommon {


    private Logger logger = LoggerFactory.getLogger(PythonAnalyzer.class);


    public PythonAnalyzer(MetricsInterface metrics, ErrorReportingInterface errorReporting) {
        super(metrics, errorReporting);
    }


    @Override
    public RuleResult execute(AnalyzerContext analyzerContext, AnalyzerRule rule) {
        long startTimestamp = System.currentTimeMillis();

        PythonAnalyzerContext context = (PythonAnalyzerContext) analyzerContext;

        List<AstElement> astElements = context.entityCheckedToAstElements.get(rule.entityChecked());

        StringBuffer buffer = new StringBuffer();
        List<Violation> violations = new ArrayList<>();

        astElements.forEach(astElement -> {
            ExecutionResult executionResult = new ExecutionEnvironmentBuilder()
                .setCode(context.getCode())
                .setRootObject(astElement)
                .setLogOutput(context.isLogOutput())
                .setFilename(context.getFilename())
                .setRuleCode(rule.code())
                .createExecutionEnvironment()
                .execute();

            if (executionResult.getOutput() != null) {
                buffer.append(executionResult.getOutput());
            }
            violations.addAll(executionResult.getViolations());
        });

        String finalOutput = buffer.length() == 0 ? null : buffer.toString();


        long endTimestamp = System.currentTimeMillis();
        long executionTimeMs = endTimestamp - startTimestamp;
        return new RuleResult(rule.name(), violations, List.of(), null, finalOutput, executionTimeMs);
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
