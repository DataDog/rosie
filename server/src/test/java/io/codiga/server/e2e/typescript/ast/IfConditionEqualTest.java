package io.codiga.server.e2e.typescript.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IfConditionEqualTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(IfConditionEqualTest.class);


    String code1 = """
        // Check the user's job title
        if (user.jobTitle = "manager") {
            // user.jobTitle is now incorrect
        }""";


    String code2 = """
        // Check the user's job title
        if (user.jobTitle == "manager") {
            // user.jobTitle is now incorrect
        }""";

    String fixedCode = """
        // Check the user's job title
        if (user.jobTitle === "manager") {
            // user.jobTitle is now incorrect
        }""";

    String ruleCode = """
        function visit(node) {
        console.log("here");
        console.log(node.condition.operator.value);
            if(node.condition.operator && (node.condition.operator.value === "=" | node.condition.operator.value === "==")){
                
                const editChangeFunctionName = buildEditUpdate(node.condition.operator.start.line, node.condition.operator.start.col, node.condition.operator.end.line, node.condition.operator.end.col, "===");
                const fix = buildFix("use ===", [editChangeFunctionName]);

                const error = buildError(node.condition.operator.start.line, node.condition.operator.start.col, node.condition.operator.end.line, node.condition.operator.end.col, "make sure you use ===", "CRITICAL", "SAFETY");
                addError(error.addFix(fix));
            }
        }
        """;

    @Test
    @DisplayName("make sure we use === instead of ==")
    public void testConditionEquality() throws Exception {
        Response response = executeTest("bla.ts", code1, Language.TYPESCRIPT, ruleCode, "replace-equal-equal", RuleType.AST_CHECK, EntityChecked.IF_STATEMENT, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(19, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(20, response.ruleResponses.get(0).violations.get(0).end.col);

        assertEquals(fixedCode, applyFix(code1, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }


    @Test
    @DisplayName("make sure we use === instead of =")
    public void testConditionAssign() throws Exception {
        Response response = executeTest("bla.ts", code2, Language.TYPESCRIPT, ruleCode, "replace-equal-equal", RuleType.AST_CHECK, EntityChecked.IF_STATEMENT, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(19, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(21, response.ruleResponses.get(0).violations.get(0).end.col);

        assertEquals(fixedCode, applyFix(code2, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }

}
