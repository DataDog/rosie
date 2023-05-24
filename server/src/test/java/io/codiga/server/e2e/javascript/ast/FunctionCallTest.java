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

public class FunctionCallTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(FunctionCallTest.class);


    String code = """
        var bla = 1;
        var foo = bar(baz);""";

    String fixedCode = """
        var bla = 1;
        var foo = baz(baz);""";

    String ruleCode = """
        function visit(node) {
            if(node.functionName && node.functionName.value === "bar"){
                
                const editChangeFunctionCall = buildEditUpdate(node.functionName.start.line, node.functionName.start.col, node.functionName.end.line, node.functionName.end.col, "baz");
                const fix = buildFix("use baz", [editChangeFunctionCall]);

                const error = buildError(node.functionName.start.line, node.functionName.start.col, node.functionName.end.line, node.functionName.end.col, "do not use function bar", "CRITICAL", "SAFETY");
                addError(error.addFix(fix));
            }
        }
        """;

    @Test
    @DisplayName("change call to bar into call to baz")
    public void testFunctionCallRule() throws Exception {
        Response response = executeTest("bla.js", code, Language.JAVASCRIPT, ruleCode, "replace-bar-baz", RuleType.AST_CHECK, EntityChecked.FUNCTION_CALL, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(11, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(14, response.ruleResponses.get(0).violations.get(0).end.col);

        assertEquals(fixedCode, applyFix(code, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }


}
