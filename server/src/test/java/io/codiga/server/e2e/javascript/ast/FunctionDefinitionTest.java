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

public class FunctionDefinitionTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(FunctionDefinitionTest.class);


    String code = """
        function foo(bla, bli) {
            console.log("foobar");
        }""";

    String fixedCode = """
        function bar(bla, bli) {
            console.log("foobar");
        }""";

    String ruleCode = """
        function visit(node) {
            if(node.name && node.name.value && node.name.value === "foo"){
                
                const editChangeFunctionName = buildEditUpdate(node.name.start.line, node.name.start.col, node.name.end.line, node.name.end.col, "bar");
                const fix = buildFix("use bar", [editChangeFunctionName]);

                const error = buildError(node.name.start.line, node.name.start.col, node.name.end.line, node.name.end.col, "do not use function foo", "CRITICAL", "SAFETY");
                addError(error.addFix(fix));
            }
        }
        """;

    @Test
    @DisplayName("change call to bar into call to baz")
    public void testFunctionCallRule() throws Exception {
        Response response = executeTest("bla.js", code, Language.JAVASCRIPT, ruleCode, "replace-foo-bar", RuleType.AST_CHECK, EntityChecked.FUNCTION_DEFINITION, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(10, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(13, response.ruleResponses.get(0).violations.get(0).end.col);

        assertEquals(fixedCode, applyFix(code, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }


}
