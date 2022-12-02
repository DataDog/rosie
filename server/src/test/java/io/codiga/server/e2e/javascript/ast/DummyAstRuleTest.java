package io.codiga.server.e2e.javascript.ast;

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


public class DummyAstRuleTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(DummyAstRuleTest.class);


    String code = """
        var bla = 1;""";


    String ruleCode = """
        function visit(node) {
        }
        """;

    @Test
    @DisplayName("never return anything for an AST rule")
    public void testDummyRule() throws Exception {
        Response response = executeTest("bla.js", code, Language.JAVASCRIPT, ruleCode, "visit-code", RULE_TYPE_AST, ENTITY_CHECKED_CLASS_DEFINITION, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
    }


}
