package io.codiga.server.e2e.pattern;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.server.constants.Languages.RULE_TYPE_PATTERN;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PatternPythonReadOnlyFileTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(PatternPythonReadOnlyFileTest.class);

    String code = """
        def print_foo():
          with open("myfile.txt", "r") as myfile:
            content = myfile.read()
        """;
    String ruleCode = """
        function visit(pattern) {
            console.log("BLI");
            console.log(pattern);
            reportError(pattern.start.line, pattern.start.col, pattern.end.line, pattern.end.col, "file with read-only defined", "CRITICAL", "SAFETY");
        }
        """;

    @Test
    @DisplayName("Remove the read-only flag when opening a file")
    public void testPythonPatternReadOnlyFlag() throws Exception {
        String pattern = "open(${file}, \"r\")";
        Response response = executeTestWithPattern("bla.py", code, Language.PYTHON, ruleCode, "remove-file-read-only", RULE_TYPE_PATTERN, null, pattern);

        logger.info("response: " + response);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("file with read-only defined", response.ruleResponses.get(0).violations.get(0).message);
    }
}
