package io.codiga.server.e2e.python.tsquery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TsQueryPresenceTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(TsQueryPresenceTest.class);

    String code1 = """
            def foo(arg1):
                pass""";

    String code2 = """
            def foo():
                pass""";


    String tsQuery = """
(function_definition
  parameters: (parameters
     (identifier)? @id
  )
)
            """;

    String ruleCodeUpdate = """
            function visit(node, filename, code) {
                    
                if(node.captures.has("id")) {
                    console.log("has the key");
                    const c = node.captures["id"];
                    const error = buildError(c.start.line, c.start.col, c.end.line, c.end.col, 
                                             "invalid name", "CRITICAL", "security");
                    addError(error);
                } else {
                   console.log("does not have the key");
                }
            }
            """;


    @Test
    @DisplayName("Test to make sure that the node is present")
    public void testPresent() throws Exception {
        Response response = executeTestTsQuery(
                "bla.py",
                code1,
                Language.PYTHON,
                ruleCodeUpdate,
                "ts-query-test",
                tsQuery,
                true);
        logger.info(response.toString());

        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertTrue(response.ruleResponses.get(0).output.contains("has the key"));
    }


    @Test
    @DisplayName("Test to check that the node is absent")
    public void testAbsent() throws Exception {
        Response response = executeTestTsQuery(
            "bla.py",
            code2,
            Language.PYTHON,
            ruleCodeUpdate,
            "ts-query-test",
            tsQuery,
            true);
        logger.info(response.toString());

        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
        assertTrue(response.ruleResponses.get(0).output.contains("does not have the key"));
    }
}
