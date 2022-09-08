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


public class Chmod777Test extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(Chmod777Test.class);

    String code = """
        os.chmod("/path/to/file", stat.S_IRUSR | stat.S_IROTH | stat.S_IWOTH | stat.S_IXOTH)""";


    String fixedCode = """
        os.chmod("/path/to/file", stat.S_IRUSR | stat.S_IROTH | stat.S_IXOTH)""";

    String ruleCodeUpdate = """
        function visit(pattern) {
            console.log("BLA");
            const filename = pattern.variables.get("file").value;
            const mode = pattern.variables.get("mode");
            
            console.log(pattern);
            
            if (mode.value.includes("stat.S_IWOTH")) {
                   console.log(mode.start);
                   console.log(mode.end);
                const error = buildError(mode.start.line, mode.start.col, mode.end.line, mode.end.col, "file can be written by others", "CRITICAL", "security");
                const filename = pattern.variables.get("file").value;
                const modes = mode.value.replaceAll(" ", "").split("|").filter(e => e !== "stat.S_IWOTH");
                const newModes = modes.join(" | ");
                const edit = buildEdit(mode.start.line, mode.start.col, mode.end.line, mode.end.col, "update", newModes);
                const fix = buildFix("remove the write flag", [edit]);
                addError(error.addFix(fix));
            }
            
        }
        """;


    String pattern = "os.chmod(\"${file}\", ${mode})";

    @Test
    @DisplayName("Remove other permissions for write")
    public void testPythonRemoveWriteUsers() throws Exception {
        Response response = executeTestWithPattern("bla.py", code, Language.PYTHON, ruleCodeUpdate, "remove-write-flag-others",
            RULE_TYPE_PATTERN, null, pattern, true);

        logger.info("response: " + response);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("file can be written by others", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals("SECURITY", response.ruleResponses.get(0).violations.get(0).category);
        assertEquals("CRITICAL", response.ruleResponses.get(0).violations.get(0).severity);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.size());
        assertEquals("remove the write flag", response.ruleResponses.get(0).violations.get(0).fixes.get(0).description);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.size());
        assertEquals("update", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).editType);
        assertEquals(27, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.col);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.line);
        assertEquals(84, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.col);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.line);
        assertEquals("stat.S_IRUSR | stat.S_IROTH | stat.S_IXOTH", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).content);

        // finally check the verified code
        assertEquals(fixedCode, applyFix(code, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }

}
