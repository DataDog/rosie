package io.codiga.server.e2e.python.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.codiga.constants.Languages.ENTITY_CHECKED_ASSIGNMENT;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class NoAssignmentTo42 extends E2EBase {

    String pythonCodeWithError = """
        v = 42
        """;


    String ruleCode = """
        function visit(node) {
            if(node.right && node.right.astType === "string" && node.right.value === "42") {
                reportError(node.right.start.line, node.right.start.col, node.right.end.line, node.right.end.col, "no 42 as value", "CRITICAL", "SAFETY");
            }
        }
        """;

    @Test
    @DisplayName("Report 42 value as an error")
    public void testNo42AsValue() throws Exception {
        Response response = executeTest("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "no-42-as-value", RULE_TYPE_AST, ENTITY_CHECKED_ASSIGNMENT, null, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertTrue(response.ruleResponses.get(0).executionTimeMs > 0);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(5, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(7, response.ruleResponses.get(0).violations.get(0).end.col);
        assertEquals("no 42 as value", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals(0, response.ruleResponses.get(0).violations.get(0).fixes.size());
    }


}
