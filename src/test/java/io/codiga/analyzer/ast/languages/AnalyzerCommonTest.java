package io.codiga.analyzer.ast.languages;

import io.codiga.analyzer.ast.common.AnalyzerCommon;
import io.codiga.analyzer.ast.languages.python.PythonTestUtils;
import io.codiga.analyzer.rule.AnalyzerRule;
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
        AnalyzerCommon analyzerCommon = new AnalyzerCommon() {
            @Override
            public RuleResult execute(String filename, String code, AnalyzerRule rule, boolean logOutput) {
                return null;
            }
        };
        List<Long> commentsLine = analyzerCommon.getCommentsLine(code, "#");
        assertEquals(1, commentsLine.size());
        assertEquals(1, commentsLine.get(0));
    }
}
