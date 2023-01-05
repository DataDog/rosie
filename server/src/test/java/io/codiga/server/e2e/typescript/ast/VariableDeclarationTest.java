package io.codiga.server.e2e.typescript.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.constants.Languages.ENTITY_CHECKED_VARIABLE_DECLARATION;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class VariableDeclarationTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(VariableDeclarationTest.class);


    String code = """
        const foo: any = 42;
        """;


    String ruleCode = """
        function visit(node) {
            if(node && node.type && node.type.astType === "string" && node.type.value === "any") {
                const error = buildError(node.type.start.line, node.type.start.col, node.type.end.line, node.type.end.col, "do not use any type", "CRITICAL", "SAFETY");
                addError(error);
            }
        }
        """;

    @Test
    @DisplayName("change call to bar into call to baz")
    public void testFunctionCallRule() throws Exception {
        Response response = executeTest("bla.ts", code, Language.TYPESCRIPT, ruleCode, "replace-foo-bar", RULE_TYPE_AST, ENTITY_CHECKED_VARIABLE_DECLARATION, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(12, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(15, response.ruleResponses.get(0).violations.get(0).end.col);
        assertEquals("do not use any type", response.ruleResponses.get(0).violations.get(0).message);

    }


}
