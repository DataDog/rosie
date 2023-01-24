package io.codiga.server.e2e.javascript_typescript.ast;

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
        var foo = 42;
        """;


    String ruleCode = """
        function visit(node) {
            if(node && node.modifier && node.modifier.value === "var") {
                const error = buildError(node.modifier.start.line, node.modifier.start.col, node.modifier.end.line, node.modifier.end.col, "do not use var", "CRITICAL", "SAFETY");
                addError(error);
            }
        }
        """;

    @Test
    @DisplayName("do not use the var modifier")
    public void testFunctionCallRule() throws Exception {
        JAVASCRIPT_TYPESCRIPT.forEach(l -> {
            Response response = executeTest("bla.ts", code, l, ruleCode, "no-var", RULE_TYPE_AST, ENTITY_CHECKED_VARIABLE_DECLARATION, null, true);
            logger.info(response.toString());
            assertEquals(1, response.ruleResponses.size());
            assertEquals(1, response.ruleResponses.get(0).violations.size());
            assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
            assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.col);
            assertEquals(1, response.ruleResponses.get(0).violations.get(0).end.line);
            assertEquals(4, response.ruleResponses.get(0).violations.get(0).end.col);
            assertEquals("do not use var", response.ruleResponses.get(0).violations.get(0).message);
        });

    }


}
