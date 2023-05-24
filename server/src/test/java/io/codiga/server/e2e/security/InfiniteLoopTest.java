package io.codiga.server.e2e.security;

import static io.codiga.model.RuleErrorCode.ERROR_RULE_TIMEOUT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.Test;

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
        Response response = executeTest("bla.py", pythonCode, Language.PYTHON, ruleCode, "python-infinite", RuleType.AST_CHECK, EntityChecked.FUNCTION_CALL, null, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(ERROR_RULE_TIMEOUT, response.ruleResponses.get(0).errors.get(0));
        assertTrue(response.ruleResponses.get(0).executionTimeMs > 0);
    }
}
