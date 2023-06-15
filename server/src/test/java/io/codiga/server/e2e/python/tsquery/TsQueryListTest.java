package io.codiga.server.e2e.python.tsquery;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TsQueryListTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(TsQueryListTest.class);

    String code = """
            class Foo:
                def foo(arg1):
                    pass
                def foo2(arg2):
                    pass""";


    String fixedCode = """
            def bar(arg1):
                pass""";


    String tsQuery = """
                (class_definition
                    body: (block
                        (function_definition
                            name: (identifier) @name
                          parameters: (parameters) @params
                        )+ @funcs
                    )
                )@class
            """;

    String ruleCodeUpdate = """
            function visit(node, filename, code) {
                    
                const functions = node.capturesList["funcs"];
                console.log(functions.length);
                
                functions.forEach(f => {
                    if(f) {
                        const functionName = f.children.filter(c => c.fieldName === "name")[0];
                        const name = getCodeForNode(functionName, code);
                        
                        const error = buildError(functionName.start.line, functionName.start.col, functionName.end.line, functionName.end.col, 
                                                 "invalid name", "CRITICAL", "security");
    
                        addError(error);
                    }       
                });
                         
            }
            """;


    @Test
    @DisplayName("Test basic tree-sitter query")
    public void testBasicTreeSitterQuery() throws Exception {
        Response response = executeTestTsQuery(
                "bla.py",
                code,
                Language.PYTHON,
                ruleCodeUpdate,
                "ts-query-test",
                tsQuery,
                true);
        logger.info(response.toString());

        assertEquals(1, response.ruleResponses.size());
        assertEquals(2, response.ruleResponses.get(0).violations.size());
        logger.info("output: " + response.ruleResponses.stream().map(r -> r.output).toList());
    }

}
