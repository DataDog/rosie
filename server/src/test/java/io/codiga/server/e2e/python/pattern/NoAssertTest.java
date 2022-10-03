package io.codiga.server.e2e.python.pattern;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static io.codiga.constants.Languages.RULE_TYPE_PATTERN;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NoAssertTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(NoAssertTest.class);

    String code = """
        x = 1
        print(foo)
        assert x == 1
        print(bar)""";


    String fixedCode = """
        x = 1
        print(foo)
                
        print(bar)""";

    String ruleCodeUpdate = """
        function visit(pattern, filename, code) {
            if(filename.includes("_test.py") || filename.startsWith("test_")) {
                return;
            }
            
            const error = buildError(pattern.start.line, pattern.start.col, pattern.end.line, pattern.end.col, "no assert in production code", "WARN", "codestyle");
            const edit = buildEditRemove(pattern.start.line, pattern.start.col, pattern.end.line, pattern.end.col);
            const fix = buildFix("remove assert", [edit]);
            addError(error.addFix(fix));
        }
        """;


    String pattern = "assert ${arguments}";

    @Test
    @DisplayName("Remove other permissions for write")
    public void testPythonRemoveWriteUsers() throws Exception {
        Response response = executeTestWithPattern("bla.py", code, Language.PYTHON, ruleCodeUpdate, "no-assert",
            RULE_TYPE_PATTERN, null, pattern, true);
        // finally check the verified code
        assertEquals(fixedCode, applyFix(code, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }

    @Test
    @DisplayName("Ignore for test file")
    public void testPythonRemoveWriteIgnoredForTests() throws Exception {
        for (String filename : List.of("bla_test.py", "test_bla.py")) {
            Response response = executeTestWithPattern(filename, code, Language.PYTHON, ruleCodeUpdate, "no-assert",
                RULE_TYPE_PATTERN, null, pattern, true);
            assertEquals(1, response.ruleResponses.size());
            assertEquals(0, response.ruleResponses.get(0).violations.size());
        }
    }
}
