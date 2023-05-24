package io.codiga.server.e2e.python.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClassInitMethodRequiredTest extends E2EBase {

    String pythonCodeWithNoError = """
        @dataclass
        class Foo:
        	def foo(bar):
        		pass
        		""";

    String pythonCodeWithError = """
        class Foo:
        	def foo(bar):
        		pass""";

    String ruleCode = """                
        function visit(node, filename, code) {
          const hasDecorators = node.decorators.length > 0;
          const hasInitMethod = node.content.elements
            .filter(e => e.astType === "functiondefinition")
            .filter(e => e.name && e.name.value === "__init__")
            .length > 0;

          if (!hasDecorators && !hasInitMethod && node.name) {
            const error = buildError(node.name.start.line, node.name.start.col,
              node.name.end.line, node.name.end.col,
              `Class ${node.name.value} should have an init method`, "CRITICAL", "SECURITY");

            addError(error);
          }

        }""";

    @Test
    @DisplayName("Detect missing __init__ function for a regular class")
    public void testViolation() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "init-required", RuleType.AST_CHECK, EntityChecked.CLASS_DEFINITION, null, true);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
    }

    @Test
    @DisplayName("Do not report any violation when there is a decorator on the class")
    public void testNoViolation() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithNoError, Language.PYTHON, ruleCode, "init-required", RuleType.AST_CHECK, EntityChecked.CLASS_DEFINITION, null, true);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
    }
}
