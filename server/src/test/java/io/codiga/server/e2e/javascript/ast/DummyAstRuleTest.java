package io.codiga.server.e2e.javascript.ast;

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
        Response response = executeTest("bla.js", code, Language.JAVASCRIPT, ruleCode, "visit-code", RuleType.AST_CHECK, EntityChecked.CLASS_DEFINITION, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
    }


}
