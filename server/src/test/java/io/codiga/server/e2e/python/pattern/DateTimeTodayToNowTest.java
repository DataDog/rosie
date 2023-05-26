package io.codiga.server.e2e.python.pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.model.error.Category;
import io.codiga.model.error.EditType;
import io.codiga.model.error.Severity;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


    String regex = "datetime.today()";

    @Test
    @DisplayName("Replace datetime.today() with datetime.now()")
    public void testNotImplementedError() throws Exception {
        Response response = executeTest("bla.py", code, Language.PYTHON, ruleCodeUpdate, "no-datetime-today()",
            RuleType.REGEX, null, regex, true);
        logger.info(String.format("response: %s", response));
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(3, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("use datetime.now() instead of datetime.today()", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals(Category.BEST_PRACTICES, response.ruleResponses.get(0).violations.get(0).category);
        assertEquals(Severity.NOTICE, response.ruleResponses.get(0).violations.get(0).severity);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.size());
        assertEquals("use datetime.now()", response.ruleResponses.get(0).violations.get(0).fixes.get(0).description);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.size());
        assertEquals(EditType.UPDATE, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).editType);
        assertEquals(7, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.col);
        assertEquals(3, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.line);
        assertEquals(23, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.col);
        assertEquals(3, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.line);
        assertEquals("datetime.now()", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).content);

        // finally check the verified code
        assertEquals(fixedCode, applyFix(code, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }


}
