package io.codiga.server.e2e.python.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.codiga.constants.Languages.ENTITY_CHECKED_TRY_BLOCK;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NoGenericExceptionTest extends E2EBase {

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
            pass""";

    String ruleCode = """
        function visit(node) {
            const genericExceptions = node.exceptClauses.flatMap(e => e.exceptions.filter(e => e.str === "Exception"));

            for (var i = 0 ; i < genericExceptions.length ; i++) {
                exception = genericExceptions[i];
                const error = buildError(exception.start.line, exception.start.col, exception.end.line, exception.end.col, "generic exception", "WARNING", "BEST_PRACTICES");
                addError(error);
            }
        }
        """;

    @Test
    @DisplayName("Detect generic exceptions")
    public void testGenericExceptionError() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "python-generic-exceptions", RULE_TYPE_AST, ENTITY_CHECKED_TRY_BLOCK, null, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(5, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(5, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(8, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(17, response.ruleResponses.get(0).violations.get(0).end.col);
        assertEquals("generic exception", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals(0, response.ruleResponses.get(0).violations.get(0).fixes.size());
    }


    @Test
    @DisplayName("No issue when specific errors are used")
    public void testGenericExceptionNoError() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithNoError, Language.PYTHON, ruleCode, "python-generic-exceptions", RULE_TYPE_AST, ENTITY_CHECKED_TRY_BLOCK, null, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
    }
}
