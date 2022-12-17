package io.codiga.server.e2e.python.pattern;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.constants.Languages.RULE_TYPE_PATTERN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class InvalidPatternTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(InvalidPatternTest.class);

    String code = """
        os.chmod("/path/to/file", stat.S_IRUSR | stat.S_IROTH | stat.S_IWOTH | stat.S_IXOTH)""";


    String fixedCode = """
        os.chmod("/path/to/file", stat.S_IRUSR | stat.S_IROTH | stat.S_IXOTH)""";

    String ruleCodeUpdate = """
        function visit(pattern, filename, code) {
            console.log("BLA");
            
        }
        """;


    String pattern = "os.chmod(\"${}\", [a-0z-+]+ ${mode})";

    /**
     * Note: this test is no longer valid since we are catching when the pattern
     * will be invalid and escaping the characters. We should update this test
     * if we catch an example when the pattern is invalid again.
     *
     * @throws Exception
     */
    @Test
    @DisplayName("Test Invalid Pattern")
    public void testInvalidPattern() throws Exception {
        Response response = executeTest("bla.py", code, Language.PYTHON, ruleCodeUpdate, "remove-write-flag-others",
            RULE_TYPE_PATTERN, null, pattern, true);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).errors.size());
//        assertEquals(1, response.ruleResponses.get(0).errors.size());
//        assertEquals(ERROR_INVALID_PATTERN, response.ruleResponses.get(0).errors.get(0));
        assertNull(response.ruleResponses.get(0).executionError);
    }
}
