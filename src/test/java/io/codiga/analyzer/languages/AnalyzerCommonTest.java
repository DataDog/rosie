package io.codiga.analyzer.languages;

import io.codiga.ast.python.PythonTestUtils;
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
        AnalyzerCommon analyzerCommon = new AnalyzerCommon();
        List<Long> commentsLine = analyzerCommon.getCommentsLine(code, "#");
        assertEquals(1, commentsLine.size());
        assertEquals(1, commentsLine.get(0));
    }
}
