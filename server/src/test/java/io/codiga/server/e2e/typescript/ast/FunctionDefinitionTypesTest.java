package io.codiga.server.e2e.typescript.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunctionDefinitionTypesTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(FunctionDefinitionTypesTest.class);


    String code = """
        function foo(bla: any, bli) {
            console.log("foobar");
        }""";


    String ruleCode = """
        function visit(node) {
            if(node.name && node.parameters && node.parameters.values){
            
                node.parameters.values.forEach(parameter => {
                console.log(parameter);
                    if(parameter && parameter.type && parameter.type.astType === "string" && parameter.type.value === "any") {
                        const error = buildError(parameter.start.line, parameter.start.col, parameter.end.line, parameter.end.col, "do not use any", "CRITICAL", "SAFETY");
                        addError(error);
                    }
                });
            }
        }
        """;

    @Test
    @DisplayName("do not use any as type for a function parameter")
    public void testFunctionParameterAny() throws Exception {
        Response response = executeTest("bla.ts", code, Language.TYPESCRIPT, ruleCode, "replace-foo-bar", RuleType.AST_CHECK, EntityChecked.FUNCTION_DEFINITION, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(14, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(22, response.ruleResponses.get(0).violations.get(0).end.col);
        assertEquals("do not use any", response.ruleResponses.get(0).violations.get(0).message);

    }


}
