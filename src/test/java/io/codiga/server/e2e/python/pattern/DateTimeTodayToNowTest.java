package io.codiga.server.e2e.python.pattern;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.server.constants.Languages.RULE_TYPE_PATTERN;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DateTimeTodayToNowTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(DateTimeTodayToNowTest.class);

    String code = """
        from datetime import datetime
        print("foo")
        bla = datetime.today()""";


    String fixedCode = """
        from datetime import datetime
        print("foo")
        bla = datetime.now()""";

    String ruleCodeUpdate = """
        function visit(pattern, filename, code) {
            const error = buildError(pattern.start.line, pattern.start.col, pattern.end.line, pattern.end.col, "use datetime.now() instead of datetime.today()", "INFO", "BEST_PRACTICES");
            const edit = buildEdit(pattern.start.line, pattern.start.col, pattern.end.line, pattern.end.col, "update", "datetime.now()");
            const fix = buildFix("use datetime.now()", [edit]);
            addError(error.addFix(fix));
        }
        """;


    String pattern = "datetime.today()";

    @Test
    @DisplayName("Replace datetime.today() with datetime.now()")
    public void testNotImplementedError() throws Exception {
        Response response = executeTestWithPattern("bla.py", code, Language.PYTHON, ruleCodeUpdate, "no-datetime-today()",
            RULE_TYPE_PATTERN, null, pattern, true);
        logger.info(String.format("response: %s", response));
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(3, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("use datetime.now() instead of datetime.today()", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals("BEST_PRACTICE", response.ruleResponses.get(0).violations.get(0).category);
        assertEquals("INFORMATIONAL", response.ruleResponses.get(0).violations.get(0).severity);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.size());
        assertEquals("use datetime.now()", response.ruleResponses.get(0).violations.get(0).fixes.get(0).description);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.size());
        assertEquals("update", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).editType);
        assertEquals(7, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.col);
        assertEquals(3, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.line);
        assertEquals(23, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.col);
        assertEquals(3, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.line);
        assertEquals("datetime.now()", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).content);

        // finally check the verified code
        assertEquals(fixedCode, applyFix(code, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }


}
