package io.codiga.server.e2e.javascript.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.constants.Languages.ENTITY_CHECKED_TRY_BLOCK;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TryBlockTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(TryBlockTest.class);


    String code = """
        try{
            foo = 42;
        } catch (foo) {
            console.log("foobar");
        } finally {
            console.log("finally");
        }""";

    String fixedCode = """
        try{
            foo = 42;
        } catch (bar) {
            console.log("foobar");
        } finally {
            console.log("finally");
        }""";

    String ruleCode = """
        function visit(node) {
            if(node.catchBlock && node.catchBlock.exception && node.catchBlock.exception.value === "foo"){
                
                const editChangeName = buildEditUpdate(node.catchBlock.exception.start.line,
                                                       node.catchBlock.exception.start.col,
                                                       node.catchBlock.exception.end.line,
                                                       node.catchBlock.exception.end.col,
                                                       "bar");
                const fix = buildFix("use bar", [editChangeName]);

                const error = buildError(node.catchBlock.start.line, node.catchBlock.start.col, node.catchBlock.end.line, node.catchBlock.end.col, "do not use name foo", "CRITICAL", "SAFETY");
                addError(error.addFix(fix));
            }
        }
        """;

    @Test
    @DisplayName("change call to bar into call to baz")
    public void testFunctionCallRule() throws Exception {
        Response response = executeTestWithTreeSitter("bla.js", code, Language.JAVASCRIPT, ruleCode, "replace-foo-bar", RULE_TYPE_AST, ENTITY_CHECKED_TRY_BLOCK, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(3, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(3, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(5, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).end.col);

        assertEquals(fixedCode, applyFix(code, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }


}
