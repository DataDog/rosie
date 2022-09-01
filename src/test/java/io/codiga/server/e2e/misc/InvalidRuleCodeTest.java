package io.codiga.server.e2e.misc;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.server.constants.Languages.RULE_TYPE_FUNCTION_CALL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidRuleCodeTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(InvalidRuleCodeTest.class);

    String pythonCode = """            
        r = requests.get(w, verify=False)
        r = requests.get(w, verify=False, timeout=10)
                            """;

    String ruleCode = """
        function visit(node) {
            const hasTimeout = node.arguments().filter(a => a.name && a.name == "timeout").length > 0;

            if(!hasTimeout){
                reportError(node.line, 10, node.line, 11, "timeout not defined", "CRITICAL", "SAFETY");
            }
        }
        """;

    @Test
    public void testInvalidRuleCode() throws Exception {
        Response response = executeTest("bla.py", pythonCode, Language.PYTHON, ruleCode, "invalid-rule", RULE_TYPE_FUNCTION_CALL);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
        assertEquals("Unknown identifier: arguments", response.ruleResponses.get(0).executionError);
    }
}
