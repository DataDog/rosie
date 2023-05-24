package io.codiga.server.e2e.python.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NoAssertTupleTest extends E2EBase {

    String pythonCodeErrors = """
        assert (1, 2)
        """;

    String pythonCodeNoError = """
        assert x
        """;
    String ruleCode = """
        function visit(node) {
            if(node.astType === "assert" && node.value.astType === "tuple") {
               reportError(node.start.line, node.start.col, 
                           node.end.line, node.end.col, 
                           "do not assert on tuples", "CRITICAL", "SAFETY");
            }
        }
        """;

    @Test
    @DisplayName("Detect assert on tuples - errors reported")
    public void testAssertOnTuples() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeErrors, Language.PYTHON, ruleCode, "assert-no-tuple", RuleType.AST_CHECK, EntityChecked.ANY, null, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertTrue(response.ruleResponses.get(0).executionTimeMs > 0);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("do not assert on tuples", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals(0, response.ruleResponses.get(0).violations.get(0).fixes.size());
    }

    @Test
    @DisplayName("Detect assert on tuples - no error")
    public void testAssertOnTupleNoError() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeNoError, Language.PYTHON, ruleCode, "assert-no-tuple", RuleType.AST_CHECK, EntityChecked.ANY, null, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
    }

}
