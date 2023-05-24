package io.codiga.server.e2e.python.pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.model.error.EditType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatternPythonReadOnlyFileTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(PatternPythonReadOnlyFileTest.class);

    String code = """
        def print_foo():
          with open("myfile.txt", "r") as myfile:
            content = myfile.read()""";

    String fixedCode = """
        def print_foo():
          with open("myfile.txt") as myfile:
            content = myfile.read()""";

    String ruleCodeUpdate = """
        function visit(pattern) {
            const error = buildError(pattern.start.line, pattern.start.col, pattern.end.line, pattern.end.col, "file with read-only defined", "WARN", "bestpractices");
            const filename = pattern.variables.get("file").value;
            const edit = buildEdit(pattern.start.line, pattern.start.col, pattern.end.line, pattern.end.col, "replace", `open(\\"${filename}\\")`);
            const fix = buildFix("remove the read-only flag", [edit]);
            
            addError(error.addFix(fix));
        }
        """;


    @Test
    @DisplayName("Remove the read-only flag when opening a file - update version")
    public void testPythonPatternReadOnlyFlagUpdate() throws Exception {
        String regex = "open(\"${file}\", \"r\")";
        Response response = executeTest("bla.py", code, Language.PYTHON, ruleCodeUpdate, "remove-file-read-only", RuleType.REGEX, null, regex, false);

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
        assertEquals(EditType.UPDATE, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).editType);
        assertEquals(8, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.col);
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.line);
        assertEquals(31, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.col);
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.line);
        assertEquals("open(\"myfile.txt\")", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).content);

        // finally check the verified code
        assertEquals(fixedCode, applyFix(code, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }

}
