package io.codiga.server.e2e.python.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.codiga.constants.Languages.ENTITY_CHECKED_FUNCTION_CALL;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CommandInjectionFromVariableTest extends E2EBase {

    String pythonCodeWithError = """
        from flask import request
        import os

        @app.route('/ping')
        def ping():
            address = request.args.get("address")
            cmd = f"ping -c 1 {address}"
            os.popen(cmd)
            """;


    String ruleCode = """                
        function visit(node, filename, context) {
            if(!node || !node.functionName || node.functionName.value !== "popen") {
                return;
            }
            const firstArgumentName = node.arguments.values[0].value.value;
            
            if (node.context.currentFunction) {
                const assignments = node.context.currentFunction.assignments.filter(a => a.left && a.left.value && a.left.value === firstArgumentName);
                if(assignments.length > 0) {
                    const assignment = assignments[0];
                    if (assignment.right.value.startsWith('f"')) {
                        reportError(node.start.line, node.start.col, node.end.line, node.end.col, "command injection", "CRITICAL", "SAFETY");
                    }
                }
            }
        }
        """;

    @Test
    @DisplayName("Detect injection with a variable")
    public void testInjectionThroughVariable() throws Exception {
        Response response = executeTest("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "shell-injection", RULE_TYPE_AST, ENTITY_CHECKED_FUNCTION_CALL, null, true);
        logger.info("response:" + response);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals("command injection", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals(8, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(5, response.ruleResponses.get(0).violations.get(0).start.col);

        assertEquals(8, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(18, response.ruleResponses.get(0).violations.get(0).end.col);
    }
}
