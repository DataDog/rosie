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
            const error = buildError(pattern.start.line, pattern.start.col, pattern.end.line, pattern.end.col, "file with read-only defined", "WARN", "bestpractices");
            const filename = pattern.variables.get("file").value;
            const edit = buildEdit(pattern.start.line, pattern.start.col, pattern.end.line, pattern.end.col, "replace", `open(\\"${filename}\\")`);
            const fix = buildFix("remove the read-only flag", [edit]);
            
            addError(error.addFix(fix));
        }
        """;

    @Test
    @DisplayName("Remove the read-only flag when opening a file")
    public void testPythonPatternReadOnlyFlag() throws Exception {
        String pattern = "open(\"${file}\", \"r\")";
        Response response = executeTestWithPattern("bla.py", code, Language.PYTHON, ruleCode, "remove-file-read-only", RULE_TYPE_PATTERN, null, pattern);

        logger.info("response: " + response);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("file with read-only defined", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals("BEST_PRACTICE", response.ruleResponses.get(0).violations.get(0).category);
        assertEquals("WARNING", response.ruleResponses.get(0).violations.get(0).severity);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.size());
        assertEquals("remove the read-only flag", response.ruleResponses.get(0).violations.get(0).fixes.get(0).description);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.size());
        assertEquals("update", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).editType);
        assertEquals(8, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.col);
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.line);
        assertEquals(31, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.col);
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.line);
        assertEquals("open(\"myfile.txt\")", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).content);
    }
}
