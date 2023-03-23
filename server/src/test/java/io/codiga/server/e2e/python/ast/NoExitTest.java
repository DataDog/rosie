package io.codiga.server.e2e.python.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.codiga.constants.Languages.ENTITY_CHECKED_FUNCTION_CALL;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NoExitTest extends E2EBase {

    String pythonCodeWithError = """
        print("bla")
        exit(0)""";

    String pythonCodeFixed = """
        import sys
        print("bla")
        sys.exit(0)""";

    String ruleCode = """
        function visit(node) {
            if(node.functionName.value === "exit"){
                const hasOneArgument = node.arguments.values && node.arguments.values.length === 1;
                
                const error = buildError(node.start.line, node.start.col, node.end.line, node.end.col, "do not use exit()", "CRITICAL", "SAFETY");
                
                const argumentValue = node.arguments.values[0].value.str;
                const newFunctionCall = `sys.exit(${argumentValue})`;
                const editReplaceFunctionCall = buildEditUpdate(node.start.line, node.start.col, node.end.line, node.end.col, newFunctionCall)
                
                const editAddImport = buildEditAdd(1, 1, "import sys\\n");
               
                const fix = buildFix("replace with sys.exit()", [editReplaceFunctionCall, editAddImport]);
                addError(error.addFix(fix));
            }
        }
        """;

    @Test
    @DisplayName("Do not use exit()")
    public void testPythonNoExit() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "subprocess-with-shell", RULE_TYPE_AST, ENTITY_CHECKED_FUNCTION_CALL, null, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("do not use exit()", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.size());
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.size());


        assertEquals("replace with sys.exit()", response.ruleResponses.get(0).violations.get(0).fixes.get(0).description);
        assertEquals("update", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).editType);
        assertEquals("sys.exit(0)", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).content);
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.line);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.col);
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.line);
        assertEquals(8, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.col);


        assertEquals("add", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(1).editType);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(1).start.line);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(1).start.col);
        assertEquals("import sys\n", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(1).content);

        assertEquals(pythonCodeFixed, applyFix(pythonCodeWithError, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }


}
