package io.codiga.server.e2e.python.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import java.util.logging.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NoSilentExceptionTest extends E2EBase {
    private final Logger log = Logger.getLogger("Test");
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
            const allClausesWithPass = node.exceptClauses.filter(e => e.content && e.content.elements && e.content.elements.length === 1 &&  e.content.elements[0].astType === "pass");
            allClausesWithPass.forEach(c => {
                const error = buildError(c.start.line, c.start.col, c.end.line, c.end.col, "silent exception", "WARNING", "BEST_PRACTICES");
                addError(error);
            });
        }
        """;

    @Test
    @DisplayName("Do not use pass to ignore exceptions")
    public void testSilentExceptionFail() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "python-no-silent-exceptions", RuleType.AST_CHECK, EntityChecked.TRY_BLOCK, null, true);
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
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithNoError, Language.PYTHON, ruleCode, "python-no-silent-exceptions", RuleType.AST_CHECK, EntityChecked.TRY_BLOCK, null, true);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
    }

}
