package io.codiga.server.e2e.python.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static io.codiga.constants.Languages.ENTITY_CHECKED_TRY_BLOCK;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NoSilentExceptionTest extends E2EBase {
    String pythonCodeWithError = """
        a = 2
        b = 0
        try:
            c = a /b
        except Exception as e:
            pass""";
    String pythonCodeWithNoError = """
        a = 2
        b = 0
        try:
            c = a /b
        except ValueError as e:
            print(e)
            pass""";
    String ruleCode = """
        function visit(node, filename, code) {
            const allClausesWithPass = node.exceptClauses.filter(e => e.getCodeBlock() && e.getCodeBlock() === "pass");
            allClausesWithPass.forEach(c => {
                const error = buildError(c.start.line, c.start.col, c.end.line, c.end.col, "silent exception", "WARNING", "BEST_PRACTICES");
                addError(error);
            });
        }
        """;
    private Logger log = Logger.getLogger("Test");

    @Test
    @DisplayName("Do not use pass to ignore exceptions")
    public void testSilentExceptionFail() throws Exception {
        Response response = executeTest("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "python-no-silent-exceptions", RULE_TYPE_AST, ENTITY_CHECKED_TRY_BLOCK, true);
        log.info("response: " + response);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(5, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(6, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(9, response.ruleResponses.get(0).violations.get(0).end.col);
        assertEquals("silent exception", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals(0, response.ruleResponses.get(0).violations.get(0).fixes.size());
    }

    @Test
    @DisplayName("Do not use pass to ignore exceptions - do not fail")
    public void testSilentExceptionFailNoError() throws Exception {
        Response response = executeTest("bla.py", pythonCodeWithNoError, Language.PYTHON, ruleCode, "python-no-silent-exceptions", RULE_TYPE_AST, ENTITY_CHECKED_TRY_BLOCK, true);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
    }

}