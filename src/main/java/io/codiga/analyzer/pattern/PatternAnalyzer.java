package io.codiga.analyzer.pattern;

import io.codiga.analyzer.AnalyzerFuturePool;
import io.codiga.analyzer.ast.common.AnalyzerCommon;
import io.codiga.analyzer.ast.vm.ExecutionEnvironment;
import io.codiga.analyzer.ast.vm.ExecutionEnvironmentBuilder;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.error.RuleResult;
import io.codiga.model.error.Violation;
import io.codiga.model.pattern.PatternObject;
import org.graalvm.polyglot.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static io.codiga.analyzer.ast.vm.VmUtils.buildExecutableCode;
import static io.codiga.analyzer.ast.vm.VmUtils.createContextForJavaScriptExecution;

public class PatternAnalyzer extends AnalyzerCommon {

    private AnalyzerFuturePool pool = AnalyzerFuturePool.getInstance();

    private Logger logger = LoggerFactory.getLogger(PatternAnalyzer.class);

    @Override
    public RuleResult execute(String filename, String code, AnalyzerRule rule, boolean logOutput) {
        PatternMatcher patternMatcher = new PatternMatcher(code, rule);
        List<PatternObject> patternObjects = patternMatcher.getPatternObjects();
        List<Violation> violations = new ArrayList<>();
        String output = null;

        for (PatternObject patternObject : patternObjects) {

            String finalCode = buildExecutableCode(rule.code());

            ExecutionEnvironment executionEnvironment = new ExecutionEnvironmentBuilder()
                .setCode(code)
                .setRootObject(patternObject)
                .setLogOutput(logOutput)
                .setFilename(filename)
                .createExecutionEnvironment();

            Context context = createContextForJavaScriptExecution(executionEnvironment);
            context.eval("js", finalCode);
            violations.addAll(executionEnvironment.errorReporting.getErrors());
            output = executionEnvironment.getOutput();
        }
        return new RuleResult(rule.name(), List.copyOf(violations), List.of(), null, output);
    }

}
