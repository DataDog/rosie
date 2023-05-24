package io.codiga.server.e2e.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        Response response = executeTest("bla.py", pythonCode, Language.PYTHON, ruleCode, "get-variables-rule", RuleType.AST_CHECK, EntityChecked.FUNCTION_CALL, null, Map.of("foo", "bar"), true);

        assertEquals(1, response.ruleResponses.size());
        assertEquals("foo=bar\nsantaClaus=null\n", response.ruleResponses.get(0).output);
    }
}
