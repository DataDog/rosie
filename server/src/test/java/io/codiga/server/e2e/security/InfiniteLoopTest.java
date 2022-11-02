package io.codiga.server.e2e.security;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.Test;

import static io.codiga.constants.Languages.ENTITY_CHECKED_FUNCTION_CALL;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static io.codiga.model.RuleErrorCode.ERROR_RULE_TIMEOUT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InfiniteLoopTest extends E2EBase {
    String pythonCode = """            
        r = requests.get(w, verify=False)
                    """;

    String ruleCode = """
        function visit(node) {
            var a = 0;
            while(true) {
                a = a + 1;
                a = a - 1;
            }
        }
        """;

    @Test
    public void testInfiniteLoop() throws Exception {
        Response response = executeTest("bla.py", pythonCode, Language.PYTHON, ruleCode, "python-infinite", RULE_TYPE_AST, ENTITY_CHECKED_FUNCTION_CALL, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(ERROR_RULE_TIMEOUT, response.ruleResponses.get(0).errors.get(0));
        assertTrue(response.ruleResponses.get(0).executionTimeMs > 0);
    }
}
