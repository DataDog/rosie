package io.codiga.server.e2e.javascript.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.constants.Languages.ENTITY_CHECKED_IF_CONDITION;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
        Response response = executeTest("bla.js", code1, Language.JAVASCRIPT, ruleCode, "replace-equal-equal", RULE_TYPE_AST, ENTITY_CHECKED_IF_CONDITION, true);
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
        Response response = executeTest("bla.js", code2, Language.JAVASCRIPT, ruleCode, "replace-equal-equal", RULE_TYPE_AST, ENTITY_CHECKED_IF_CONDITION, true);
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
