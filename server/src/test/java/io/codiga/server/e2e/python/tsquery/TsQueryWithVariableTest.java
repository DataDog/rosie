package io.codiga.server.e2e.python.tsquery;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TsQueryWithVariableTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(TsQueryWithVariableTest.class);

    String code = """
            def foo(arg1):
                pass""";


    String fixedCode = """
            def bar(arg1):
                pass""";


    String tsQuery = """
                (function_definition
                	name: (identifier) @name
                  parameters: (parameters) @params
                )
            """;

    String ruleCodeUpdate = """
            function visit(node, filename, code) {
                const newname = node.context.variables.get("newname");
                const functionName = node.captures["name"];

                if(functionName) {
                    const error = buildError(functionName.start.line, functionName.start.col, functionName.end.line, functionName.end.col, 
                                             "invalid name", "CRITICAL", "security");

                    const edit = buildEdit(functionName.start.line, functionName.start.col, functionName.end.line, functionName.end.col, "update", newname);
                    const fix = buildFix("use bar", [edit]);
                    addError(error.addFix(fix));
                }            
            }
            """;


    @Test
    @DisplayName("Test basic tree-sitter query with a variable")
    public void testTsQueryWithVariable() throws Exception {
        Response response = executeTestTsQuery(
                "bla.py",
                code,
                Language.PYTHON,
                ruleCodeUpdate,
                "ts-query-test",
                tsQuery,
                Map.of("newname", "bar"),
                true);
        logger.info(response.toString());

        assertEquals(1, response.ruleResponses.size());
        assertEquals(fixedCode, applyFix(code, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }

}
