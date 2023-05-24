package io.codiga.server.e2e.python.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.Test;

public class ForLoopNoRangeLenIterationTest extends E2EBase {

    String pythonCodeWithError = """
        for i in range(len(tab)):
            
            bla(   tab[i]   )""";

    String pythonCodeFixed = """
        for i in tab:
            
            bla(   i   )""";

    String ruleCode = """
        function visit(node, filename, code) {

                
            if(node.right && node.right.astType === "functioncall" && node.right.functionName.value === "range" && node.right.arguments && node.right.arguments.values[0].value.functionName.value === "len") {
                const arg = node.right.arguments.values[0].value.arguments.values[0].value.str;
                const v = node.left.str;

                

                if (arg){

                    const replaceListEdit = buildEditUpdate(node.right.start.line, node.right.start.col, node.right.end.line, node.right.end.col, arg);
                    const replaceForContentEdit = buildEditUpdate(node.statements.elements[0].arguments.values[0].start.line, 
                                                                  node.statements.elements[0].arguments.values[0].start.col, 
                                                                  node.statements.elements[0].arguments.values[0].end.line, 
                                                                  node.statements.elements[0].arguments.values[0].end.col, v);
                    const fix = buildFix("use the list directly", [replaceForContentEdit, replaceListEdit]);
                
                    const error = buildError(node.right.start.line, node.right.start.col, node.right.end.line, node.right.end.col, `do not use range(len(${arg}))`, "INFO", "BEST_PRACTICE");
                    addError(error.addFix(fix));
                }
                
            }
            
        }
        """;

    @Test
    public void testPythonNoForWithRangeAndLen() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "no-for-range-len", RuleType.AST_CHECK, EntityChecked.FOR_LOOP, null, true);
        logger.info("response:" + response);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(10, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(25, response.ruleResponses.get(0).violations.get(0).end.col);
        assertEquals("do not use range(len(tab))", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals(pythonCodeFixed, applyFix(pythonCodeWithError, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }


}
