package io.codiga.server.e2e.typescript.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static io.codiga.constants.Languages.ENTITY_CHECKED_FUNCTION_DEFINITION;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MaxParametersFunctionDefinitionTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(MaxParametersFunctionDefinitionTest.class);


    String javascriptCode = """
        function e(foo: any, bar: any, baz: any, qux: any) {
        }""";


    String ruleCode = """
        function visit(node, filename, code) {
          let maxParams = node.context.variables.get("maxParams");
          maxParams = maxParams === null ? 5 : Number(maxParams);
        
          if (!node.parameters) return;
          if (node.parameters?.values.length <= maxParams) return;
                    
          const error = buildError(
            node.name.start.line,
            node.name.start.col,
            node.name.end.line,
            node.name.end.col,
            `Having this many parameters can be difficult to manage. Consider splitting this function.`,
            "INFORMATIONAL",
            "BEST_PRACTICES"
          );
          addError(error);
        }""";

    @Test
    @DisplayName("Check that the number of parameters is below the max set")
    public void testMaxParametersInFunctionAcceptable() throws Exception {
        Response response = executeTestWithTreeSitter("bla.ts", javascriptCode, Language.TYPESCRIPT, ruleCode, "max-params-function-def", RULE_TYPE_AST, ENTITY_CHECKED_FUNCTION_DEFINITION, null, Map.of("maxParams", "4"), true);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
        assertEquals(0, response.ruleResponses.get(0).errors.size());
        assertTrue(response.ruleResponses.get(0).executionTimeMs > 0);
    }

    @Test
    @DisplayName("Catch violations for the number of parameters when the maxParams variable is set")
    public void testMaxParametersInFunctionLowerMaxParams() throws Exception {
        Response response = executeTestWithTreeSitter("bla.ts", javascriptCode, Language.TYPESCRIPT, ruleCode, "max-params-function-def", RULE_TYPE_AST, ENTITY_CHECKED_FUNCTION_DEFINITION, null, Map.of("maxParams", "3"), true);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).errors.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(10, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(11, response.ruleResponses.get(0).violations.get(0).end.col);
        assertEquals("Having this many parameters can be difficult to manage. Consider splitting this function.", response.ruleResponses.get(0).violations.get(0).message);
        assertTrue(response.ruleResponses.get(0).executionTimeMs > 0);
    }
}
