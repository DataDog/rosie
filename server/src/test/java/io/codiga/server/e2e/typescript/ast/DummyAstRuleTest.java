package io.codiga.server.e2e.typescript.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.constants.Languages.ENTITY_CHECKED_CLASS_DEFINITION;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DummyAstRuleTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(DummyAstRuleTest.class);


    String code = """
        var bla = 1;""";


    String ruleCode = """
        function visit(node) {
        }
        """;

    @Test
    @DisplayName("never return anything for an AST rule - code is TypeScript and rule is JavaScript")
    public void testDummyRuleJavaScript() throws Exception {
        Response response = executeTest("bla.ts", code, Language.TYPESCRIPT, Language.JAVASCRIPT, ruleCode, "visit-code", RULE_TYPE_AST, ENTITY_CHECKED_CLASS_DEFINITION, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertTrue(response.ruleResponses.get(0).errors.isEmpty());
    }

    @Test
    @DisplayName("never return anything for an AST rule - code and rule are both typescript")
    public void testDummyRuleTypeScript() throws Exception {
        Response response = executeTest("bla.ts", code, Language.TYPESCRIPT, Language.TYPESCRIPT, ruleCode, "visit-code", RULE_TYPE_AST, ENTITY_CHECKED_CLASS_DEFINITION, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertTrue(response.ruleResponses.get(0).errors.isEmpty());
    }
}
