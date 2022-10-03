package io.codiga.server.e2e.python.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.Test;

import static io.codiga.constants.Languages.ENTITY_CHECKED_FOR_LOOP;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ForLoopNoRangeLenIterationTest extends E2EBase {

    String pythonCodeWithError = """
        for i in range(len(tab)):
            
            bla(   tab[i]   )""";

    String pythonCodeFixed = """
        for i in tab:
            
            bla(   i   )""";

    String ruleCode = """
        function visit(node, filename, code) {
            if(node.list && node.list.str) {
                const pattern =  /range\\(\\s*len\\(\\s*([a-zA-Z0-9]+)\\s*\\)/;
                const found = node.list.str.match(pattern);
                if (found && found.length === 2){
                    const forcode = code.substring(node.statements.startIndex, node.statements.stopIndex);
                    
                    const listname = found[1];
                    const forVariable = node.variables[0].atom.str;

                    const toReplace = `${listname}\\[${forVariable}\\]`;
                    
                    const newCodeInsideFor = forcode.replaceAll(toReplace, forVariable);
                    console.log("====");
                    console.log(newCodeInsideFor);
                    console.log("====");
                    const replaceListEdit = buildEditUpdate(node.list.start.line, node.list.start.col, node.list.end.line, node.list.end.col, listname);
                    const replaceForContentEdit = buildEditUpdate(node.statements.start.line, node.statements.start.col, node.statements.end.line, node.statements.end.col, newCodeInsideFor);
                    const fix = buildFix("use the list directly", [replaceForContentEdit, replaceListEdit]);
                    const error = buildError(node.list.start.line, node.list.start.col, node.list.end.line, node.list.end.col, `do not use range(len(${listname}))`, "INFO", "BEST_PRACTICE");
                    addError(error.addFix(fix));
                }
                
            }
            
        }
        """;

    @Test
    public void testPythonNoForWithRangeAndLen() throws Exception {
        Response response = executeTest("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "no-for-range-len", RULE_TYPE_AST, ENTITY_CHECKED_FOR_LOOP, true);
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
