package io.codiga.server.e2e.python.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClassNameTest extends E2EBase {

    String pythonCodeWithError = """
        class FooBar: pass""";


    String pythonCodeWithNoError = """
        class MyClass: pass""";

    String ruleCode = """
        function visit(node) {
            if(node.name && node.name.value.indexOf("Foo") !== -1){
               
                const error = buildError(node.name.start.line, node.name.start.col, 
                                         node.name.end.line, node.name.end.col, 
                                         "class name should not contain Foo", "CRITICAL", "SAFETY");
                
                addError(error);
            }
        }
        """;

    @Test
    @DisplayName("class names should not contain foo - contains errors")
    public void testPythonClassNameError() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "class-name", RuleType.AST_CHECK, EntityChecked.CLASS_DEFINITION, null, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertTrue(response.ruleResponses.get(0).executionTimeMs > 0);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(7, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(13, response.ruleResponses.get(0).violations.get(0).end.col);
        assertEquals("class name should not contain Foo", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals(0, response.ruleResponses.get(0).violations.get(0).fixes.size());
    }


    @Test
    @DisplayName("class names should not contain foo - no errors")
    public void testPythonClassNameNoError() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithNoError, Language.PYTHON, ruleCode, "class-name", RuleType.AST_CHECK, EntityChecked.CLASS_DEFINITION, null, false);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
    }

}
