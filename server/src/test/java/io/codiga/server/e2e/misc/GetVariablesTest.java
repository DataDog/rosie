package io.codiga.server.e2e.misc;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static io.codiga.constants.Languages.ENTITY_CHECKED_FUNCTION_CALL;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetVariablesTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(InvalidRuleCodeTest.class);

    String pythonCode = """            
        r = requests.get(w, verify=False)
                            """;

    String ruleCode = """
        function visit(node) {
            const variableThatExists = node.context.variables.get("foo");
            const variableThatDoesNotExist = node.context.variables.get("santaClaus");
            console.log("foo=" + variableThatExists);
            console.log("santaClaus=" + variableThatDoesNotExist);
        }
        """;

    @Test
    public void testGetVariables() throws Exception {
        Response response = executeTest("bla.py", pythonCode, Language.PYTHON, ruleCode, "get-variables-rule", RULE_TYPE_AST, ENTITY_CHECKED_FUNCTION_CALL, null, Map.of("foo", "bar"), true);

        assertEquals(1, response.ruleResponses.size());
        assertEquals("foo=bar\nsantaClaus=null\n", response.ruleResponses.get(0).output);
    }
}
