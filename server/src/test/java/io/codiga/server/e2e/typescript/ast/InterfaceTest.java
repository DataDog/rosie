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

public class InterfaceTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(InterfaceTest.class);


    String code = """
        interface Foo {
          label: string;
        }""";

    String fixedCode = """
        interface Bar {
          label: string;
        }""";
    String ruleCode = """
        function visit(node) {
        console.log(node.name);
            if(node.name && node.name.value === "Foo"){
                const edit = buildEditUpdate(node.name.start.line, node.name.start.col, node.name.end.line, node.name.end.col, "Bar");
                const fix = buildFix("use Bar", [edit]);
                
                const error = buildError(node.name.start.line, node.name.start.col, node.name.end.line, node.name.end.col, "do not use Foo", "CRITICAL", "SAFETY");
                addError(error.addFix(fix));
            }
        }
        """;

    @Test
    @DisplayName("do not have an interface with Foo")
    public void testInterfaceFoo() throws Exception {
        Response response = executeTest("bla.ts", code, Language.TYPESCRIPT, ruleCode, "interface-name", RuleType.AST_CHECK, EntityChecked.INTERFACE, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(11, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(14, response.ruleResponses.get(0).violations.get(0).end.col);

        assertEquals(fixedCode, applyFix(code, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }


}
