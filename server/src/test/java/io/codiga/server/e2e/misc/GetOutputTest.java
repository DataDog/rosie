package io.codiga.server.e2e.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetOutputTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(GetOutputTest.class);

    String pythonCode = """            
        r = requests.get(w, verify=False)
        r = requests.get(w, verify=False, timeout=10)
                            """;

    String ruleCode = """
        function visit(node) {
            console.log("bla");
        }
        """;

    @Test
    @DisplayName("Get the script output when the request specifies it")
    public void testGetOutputWhenRequested() throws Exception {
        Response response = executeTest("bla.py", pythonCode, Language.PYTHON, ruleCode, "get-output", RuleType.AST_CHECK, EntityChecked.FUNCTION_CALL, null, true);

        assertEquals(1, response.ruleResponses.size());
        assertEquals("bla\nbla\n", response.ruleResponses.get(0).output);
    }

    @Test
    @DisplayName("Get <null> from output if output logging is disabled")
    public void testGetOutputWhenNotRequested() throws Exception {
        Response response = executeTest("bla.py", pythonCode, Language.PYTHON, ruleCode, "get-output", RuleType.AST_CHECK, EntityChecked.FUNCTION_CALL, null, false);

        assertEquals(1, response.ruleResponses.size());
        assertNull(response.ruleResponses.get(0).output);
    }
}
