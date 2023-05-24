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

public class NoIfWithEqualTrueTest extends E2EBase {
    private final Logger log = Logger.getLogger("Test");
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
                
            if(node.condition && node.condition.leftSide && node.condition.leftSide.astType === "string" && node.condition.rightSide && node.condition.rightSide.astType === "string" && node.condition.rightSide.value === "True"){
                const error = buildError(node.condition.start.line, node.condition.start.col, node.condition.end.line, node.condition.end.col, "do not make equal with true", "INFO", "BEST_PRACTICE");

                const editReplaceCondition = buildEditUpdate(node.condition.start.line,
                                                             node.condition.start.col, 
                                                             node.condition.rightSide.end.line, 
                                                             node.condition.rightSide.end.col, 
                                                             node.condition.leftSide.value)


                const fix = buildFix("remove True", [editReplaceCondition]);
                addError(error.addFix(fix));
            }
        }
        """;

    @Test
    @DisplayName("Do not use == True in an if condition")
    public void testPythonIfTrue() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "no-if-with-equal-true", RuleType.AST_CHECK, EntityChecked.IF_STATEMENT, null, true);

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
