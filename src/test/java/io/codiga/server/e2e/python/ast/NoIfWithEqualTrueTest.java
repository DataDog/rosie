package io.codiga.server.e2e.python.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static io.codiga.server.constants.Languages.ENTITY_CHECKED_IF_CONDITION;
import static io.codiga.server.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NoIfWithEqualTrueTest extends E2EBase {
    String pythonCodeWithError = """
        bla = 1
        if bla == True:
            print("hello")""";
    String pythonCodeFixed = """
        bla = 1
        if bla:
            print("hello")""";
    String ruleCode = """
        function visit(node) {
            if(node.condition && node.condition.rightSide && node.condition.rightSide.expression && node.condition.rightSide.expression.atom && node.condition.rightSide.expression.atom.str === "True"){
                const error = buildError(node.condition.start.line, node.condition.start.col, node.condition.end.line, node.condition.end.col, "do not make equal with true", "INFO", "BEST_PRACTICE");

                const editReplaceCondition = buildEditUpdate(node.condition.start.line, node.condition.start.col, 
                node.condition.rightSide.expression.atom.end.line, node.condition.rightSide.expression.atom.end.col, node.condition.leftSide.getText())


                const fix = buildFix("remove True", [editReplaceCondition]);
                addError(error.addFix(fix));
            }
        }
        """;
    private Logger log = Logger.getLogger("Test");

    @Test
    @DisplayName("Do not use eval()")
    public void testPythonNoEval() throws Exception {
        Response response = executeTest("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "no-if-with-equal-true", RULE_TYPE_AST, ENTITY_CHECKED_IF_CONDITION, true);

        log.info("response: " + response);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(4, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(15, response.ruleResponses.get(0).violations.get(0).end.col);
        assertEquals("do not make equal with true", response.ruleResponses.get(0).violations.get(0).message);

        assertEquals(pythonCodeFixed, applyFix(pythonCodeWithError, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }


}
