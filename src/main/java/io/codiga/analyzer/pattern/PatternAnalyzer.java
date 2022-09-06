package io.codiga.analyzer.pattern;

import io.codiga.analyzer.AnalyzerFuturePool;
import io.codiga.analyzer.ast.common.AnalyzerCommon;
import io.codiga.analyzer.ast.common.ErrorReporting;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.error.RuleResult;
import io.codiga.model.error.Violation;
import io.codiga.model.pattern.PatternObject;
import org.graalvm.polyglot.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static io.codiga.analyzer.ast.vm.VmUtils.createContextForAst;

public class PatternAnalyzer extends AnalyzerCommon {

    private AnalyzerFuturePool pool = AnalyzerFuturePool.getInstance();

    private Logger logger = LoggerFactory.getLogger(PatternAnalyzer.class);

    @Override
    public RuleResult execute(String filename, String code, AnalyzerRule rule) {
        PatternMatcher patternMatcher = new PatternMatcher(code, rule);
        List<PatternObject> patternObjects = patternMatcher.getPatternObjects();
        List<Violation> violations = new ArrayList<>();

        for (PatternObject patternObject : patternObjects) {
            logger.info("found pattern: " + patternObject);

            ErrorReporting errorReporting = new ErrorReporting();
            String finalCode = " reportError = addError.addError; " + rule.code() + " visit(root);";

            Context context = createContextForAst(patternObject, errorReporting);
            context.eval("js", finalCode);
            logger.info("errors: " + errorReporting.getErrors());
            violations.addAll(errorReporting.getErrors());
        }
        return new RuleResult(rule.name(), List.copyOf(violations), List.of(), null);
    }

}
