package io.codiga.server.e2e.security;

import static io.codiga.model.RuleErrorCode.ERROR_RULE_EXECUTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InvalidRuleCodeTest extends E2EBase {
    String pythonCode = """            
        r = requests.get(w, verify=False)
                    """;

    String ruleCode = """
        function visit(node) {
            var a = 0;
            const bla = 1;
            const bla = 2;
            while(true) {
                a = a + 1;
                a = a - 1;
            }
        }
        """;

    @Test
    @DisplayName("Report execution error when javascript code is invalid")
    public void testInvalidCode() throws Exception {
        Response response = executeTest("bla.py", pythonCode, Language.PYTHON, ruleCode, "python-infinite", RuleType.AST_CHECK, EntityChecked.FUNCTION_CALL, null, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(ERROR_RULE_EXECUTION, response.ruleResponses.get(0).errors.get(0));
        assertEquals("SyntaxError: Unnamed:4:10 Variable \"bla\" has already been declared\n    const bla = 2;\n          ^\n", response.ruleResponses.get(0).executionError);
    }
}
