package io.codiga.server.e2e.python.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.Test;

import static io.codiga.server.constants.Languages.ENTITY_CHECKED_FUNCTION_DEFINITION;
import static io.codiga.server.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class FunctionWithEmptyArrayAsParameterTest extends E2EBase {

    String pythonCodeWithError = """
        print("bli")
        def newFunction(arg1, arg2: int, arg3 = []):
            print("bla")""";


    String ruleCode = """
        function visit(node) {
            const parametersWithEmptyArray = node.parameters.values.filter(p => p && p.defaultValue && p.defaultValue.value === "[]");

            for(var i = 0 ; i < parametersWithEmptyArray.length ; i++) {
                const parameter = parametersWithEmptyArray[i];
                console.log(parameter.name.value);
                console.log(parameter.start.col);
                const error = buildError(parameter.defaultValue.start.line, parameter.defaultValue.start.col, parameter.defaultValue.end.line, parameter.defaultValue.end.col, "cannot use default initializer [] in function", "CRITICAL", "SAFETY");
                addError(error);
            }
        }
        """;

    @Test
    public void testPythonRequestTimeout() throws Exception {
        Response response = executeTest("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "no-array-default-value", RULE_TYPE_AST, ENTITY_CHECKED_FUNCTION_DEFINITION, true);
        logger.info("response:" + response);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(40, response.ruleResponses.get(0).violations.get(0).start.col);

        assertEquals(2, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(42, response.ruleResponses.get(0).violations.get(0).end.col);
    }


}
