package io.codiga.server.e2e.python.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.codiga.constants.Languages.ENTITY_CHECKED_FUNCTION_CALL;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class NoEvalTest extends E2EBase {

    String pythonCodeWithError = """
        print("bla")
        eval('[1, 2, 3]')""";


    String pythonCodeWithTwoErrors = """
        print("bla")
        eval('[1, 2, 3]')
        eval('[1, 2, 3]')""";

    String pythonCodeFixed = """
        from ast import literal_eval
        print("bla")
        literal_eval('[1, 2, 3]')""";

    String ruleCode = """
        function visit(node) {
            if(node.functionName.value === "eval"){
                const hasOneArgument = node.arguments.values && node.arguments.values.length === 1;
                
                const error = buildError(node.start.line, node.start.col, node.end.line, node.end.col, "do not use eval as this is unsafe", "CRITICAL", "SAFETY");
                
                const argumentValue = node.arguments.values[0].value.str;
                const newFunctionCall = `literal_eval(${argumentValue})`;
                const editReplaceFunctionCall = buildEditUpdate(node.start.line, node.start.col, node.end.line, node.end.col, newFunctionCall)
                
                const editAddImport = buildEditAdd(1, 1, "from ast import literal_eval\\n");
                
               
                const fix = buildFix("replace with literal_eval", [editReplaceFunctionCall, editAddImport]);
                addError(error.addFix(fix));
            }
        }
        """;

    @Test
    @DisplayName("Do not use eval()")
    public void testPythonNoEval() throws Exception {
        Response response = executeTest("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "no-eval", RULE_TYPE_AST, ENTITY_CHECKED_FUNCTION_CALL, null, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertTrue(response.ruleResponses.get(0).executionTimeMs > 0);
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("do not use eval as this is unsafe", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.size());
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.size());

        assertEquals("update", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).editType);
        assertEquals("literal_eval('[1, 2, 3]')", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).content);
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.line);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.col);
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.line);
        assertEquals(18, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.col);


        assertEquals("add", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(1).editType);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(1).start.line);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(1).start.col);
        assertEquals("from ast import literal_eval\n", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(1).content);

        assertEquals(pythonCodeFixed, applyFix(pythonCodeWithError, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }


    @Test
    @DisplayName("Make sure we catch multiple instances of the issue")
    public void testPythonNoEvalMultiple() throws Exception {
        Response response = executeTest("bla.py", pythonCodeWithTwoErrors, Language.PYTHON, ruleCode, "no-eval", RULE_TYPE_AST, ENTITY_CHECKED_FUNCTION_CALL, null, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(2, response.ruleResponses.get(0).violations.size());
        assertTrue(response.ruleResponses.get(0).executionTimeMs > 0);

        // first issue
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("do not use eval as this is unsafe", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.size());
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.size());

        assertEquals("update", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).editType);
        assertEquals("literal_eval('[1, 2, 3]')", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).content);
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.line);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.col);
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.line);
        assertEquals(18, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.col);


        assertEquals("add", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(1).editType);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(1).start.line);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(1).start.col);
        assertEquals("from ast import literal_eval\n", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(1).content);

        // second issue
        assertEquals(3, response.ruleResponses.get(0).violations.get(1).start.line);
        assertEquals("do not use eval as this is unsafe", response.ruleResponses.get(0).violations.get(1).message);
        assertEquals(1, response.ruleResponses.get(0).violations.get(1).fixes.size());
        assertEquals(2, response.ruleResponses.get(0).violations.get(1).fixes.get(0).edits.size());

        assertEquals("update", response.ruleResponses.get(0).violations.get(1).fixes.get(0).edits.get(0).editType);
        assertEquals("literal_eval('[1, 2, 3]')", response.ruleResponses.get(0).violations.get(1).fixes.get(0).edits.get(0).content);
        assertEquals(3, response.ruleResponses.get(0).violations.get(1).fixes.get(0).edits.get(0).start.line);
        assertEquals(1, response.ruleResponses.get(0).violations.get(1).fixes.get(0).edits.get(0).start.col);
        assertEquals(3, response.ruleResponses.get(0).violations.get(1).fixes.get(0).edits.get(0).end.line);
        assertEquals(18, response.ruleResponses.get(0).violations.get(1).fixes.get(0).edits.get(0).end.col);


        assertEquals("add", response.ruleResponses.get(0).violations.get(1).fixes.get(0).edits.get(1).editType);
        assertEquals(1, response.ruleResponses.get(0).violations.get(1).fixes.get(0).edits.get(1).start.line);
        assertEquals(1, response.ruleResponses.get(0).violations.get(1).fixes.get(0).edits.get(1).start.col);
        assertEquals("from ast import literal_eval\n", response.ruleResponses.get(0).violations.get(1).fixes.get(0).edits.get(1).content);

    }
}
