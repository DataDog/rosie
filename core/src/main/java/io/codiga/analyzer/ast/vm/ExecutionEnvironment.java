package io.codiga.analyzer.ast.vm;

import io.codiga.analyzer.ast.common.AnalyzerContext;
import io.codiga.analyzer.ast.common.ErrorReporting;
import io.codiga.analyzer.rule.AnalyzerRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.analyzer.ast.vm.VmUtils.buildExecutableCode;


public class ExecutionEnvironment {
    private static final Logger logger = LoggerFactory.getLogger(ExecutionEnvironment.class);
    public Object rootObject;
    public ErrorReporting errorReporting;
    public String code;
    public String ruleCode;
    public String filename = null;
    public boolean logOutput;


    public ExecutionEnvironment(Object rootObject, String code, String ruleCode, boolean logOutput, String filename) {
        this.rootObject = rootObject;
        this.errorReporting = new ErrorReporting();
        this.logOutput = logOutput;
        this.filename = filename;
        this.code = code;
        this.ruleCode = ruleCode;
    }



    public ExecutionResult execute(AnalyzerContext analyzerContext, AnalyzerRule rule) {
        analyzerContext.getVmContext().prepareForExecutionEnvironment(this);
        String finalCode = buildExecutableCode(rule.code());
        analyzerContext.getVmContext().getContext().eval("js", finalCode);
        return new ExecutionResult(this.errorReporting.getErrors(), analyzerContext.getOutput());
    }
}
