package io.codiga.server.e2e.python.pattern;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.constants.Languages.RULE_TYPE_PATTERN;
import static io.codiga.model.RuleErrorCode.ERROR_INVALID_PATTERN;
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

    @Test
    @DisplayName("Test Invalid Pattern")
    public void testInvalidPattern() throws Exception {
        Response response = executeTestWithPattern("bla.py", code, Language.PYTHON, ruleCodeUpdate, "remove-write-flag-others",
            RULE_TYPE_PATTERN, null, pattern, true);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(ERROR_INVALID_PATTERN, response.ruleResponses.get(0).errors.get(0));
        assertNull(response.ruleResponses.get(0).executionError);
    }
}
