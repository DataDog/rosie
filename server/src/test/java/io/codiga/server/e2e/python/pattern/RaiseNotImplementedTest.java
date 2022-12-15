package io.codiga.server.e2e.python.pattern;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.constants.Languages.RULE_TYPE_PATTERN;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RaiseNotImplementedTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(RaiseNotImplementedTest.class);

    String code = """
        a = 1
        b = 2
        raise NotImplemented
        c = 3""";


    String fixedCode = """
        a = 1
        b = 2
        raise NotImplementedError
        c = 3""";

    String ruleCodeUpdate = """
        function visit(pattern, filename, code) {
            const exceptionName = pattern.variables.get("exceptionName");
            if (exceptionName && exceptionName.value === "NotImplemented") {
                const error = buildError(pattern.start.line, pattern.start.col, pattern.end.line, pattern.end.col, "raise NotImplemented is not a valid error", "INFO", "BEST_PRACTICES");
                const edit = buildEdit(pattern.start.line, pattern.start.col, pattern.end.line, pattern.end.col, "update", "raise NotImplementedError");
                const fix = buildFix("raise NotImplementedError", [edit]);
                addError(error.addFix(fix));
            }
        }
        """;


    String pattern = "raise ${exceptionName}";

    @Test
    @DisplayName("Remove NotImplemented by NotImplementedError")
    public void testNotImplementedError() throws Exception {
        Response response = executeTest("bla.py", code, Language.PYTHON, ruleCodeUpdate, "not-implemented",
            RULE_TYPE_PATTERN, null, pattern, true);
        logger.info(String.format("response: %s", response));
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(3, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("raise NotImplemented is not a valid error", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals("BEST_PRACTICE", response.ruleResponses.get(0).violations.get(0).category);
        assertEquals("INFORMATIONAL", response.ruleResponses.get(0).violations.get(0).severity);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.size());
        assertEquals("raise NotImplementedError", response.ruleResponses.get(0).violations.get(0).fixes.get(0).description);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.size());
        assertEquals("update", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).editType);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.col);
        assertEquals(3, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.line);
        assertEquals(21, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.col);
        assertEquals(3, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.line);
        assertEquals("raise NotImplementedError", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).content);

        // finally check the verified code
        assertEquals(fixedCode, applyFix(code, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }


}
