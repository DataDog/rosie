package io.codiga.server.e2e.typescript.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.constants.Languages.ENTITY_CHECKED_FOR_LOOP;
import static io.codiga.constants.Languages.RULE_TYPE_AST;


public class ForLoopTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(ForLoopTest.class);


    String code = """
        for (var foo = 0; i < 10; i--) {
        }""";


    String fixedCode = """
        for (var bar = 0; i < 10; i--) {
        }""";

    String ruleCode = """
        function visit(node) {
        console.log("bla");
            if(node.init && node.init.values && node.init.values.length > 1 == node.init.values[0].left.value === "foo"){
                console.log(node.init.values[0].left.value);

                const editChangeName = buildEditUpdate(node.init.values[0].left.start.line, node.init.values[0].left.start.col, node.init.values[0].left.end.line, node.init.values[0].left.end.col, "bar");
                const fix = buildFix("use bar", [editChangeName]);

                const error = buildError(node.init.start.line, node.init.start.col, node.init.end.line, node.init.end.col, "do not use foo for initializer", "CRITICAL", "SAFETY");
                addError(error.addFix(fix));
            }
        }
        """;

    @Test
    @DisplayName("test loop detection")
    public void testConditionEquality() throws Exception {
        Response response = executeTest("bla.ts", code, Language.TYPESCRIPT, ruleCode, "replace-equal-equal", RULE_TYPE_AST, ENTITY_CHECKED_FOR_LOOP, null, true);
        logger.info(response.toString());
        // FIXME julien - fix the parity of for loop for TypeScript and JavaScript
//        assertEquals(1, response.ruleResponses.size());
//        assertEquals(1, response.ruleResponses.get(0).violations.size());
//        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
//        assertEquals(6, response.ruleResponses.get(0).violations.get(0).start.col);
//        assertEquals(1, response.ruleResponses.get(0).violations.get(0).end.line);
//        assertEquals(17, response.ruleResponses.get(0).violations.get(0).end.col);
//
//        assertEquals(fixedCode, applyFix(code, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }


}
