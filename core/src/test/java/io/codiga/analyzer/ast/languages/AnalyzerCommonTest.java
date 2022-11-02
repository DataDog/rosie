package io.codiga.analyzer.ast.languages;

import io.codiga.analyzer.ast.common.AnalyzerCommon;
import io.codiga.analyzer.ast.common.AnalyzerContext;
import io.codiga.analyzer.ast.languages.python.PythonTestUtils;
import io.codiga.analyzer.config.AnalyzerConfiguration;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.errorreporting.ErrorReportingDummy;
import io.codiga.metrics.MetricsDummy;
import io.codiga.model.Language;
import io.codiga.model.error.RuleResult;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnalyzerCommonTest extends PythonTestUtils {

    private Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Correctly get the comments lines in Python")
    public void testTransformFunctionWithDotNotation() {
        String code = """
            # codiga-disable
            r = requests.get(w, verify=False, timeout=10)
            """;

        AnalyzerConfiguration configuration = new AnalyzerConfiguration(2000);
        AnalyzerCommon analyzerCommon = new AnalyzerCommon(new MetricsDummy(), new ErrorReportingDummy(), configuration) {
            @Override
            public RuleResult execute(AnalyzerContext analyzerContext, AnalyzerRule rule) {
                return null;
            }

            @Override
            public void prepareExecution(String filename, String code, AnalyzerRule rule, boolean logOutput) {

            }

            @Override
            public AnalyzerContext buildContext(Language language, String filename, String code, List<AnalyzerRule> rules, boolean logOutput) {
                return new AnalyzerContext(language, filename, code, rules, logOutput);
            }
        };
        List<Long> commentsLine = analyzerCommon.getCommentsLine(code, "#");
        assertEquals(1, commentsLine.size());
        assertEquals(1, commentsLine.get(0));
    }
}
