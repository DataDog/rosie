package io.codiga.server.e2e.python.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NestedBlocksTest extends E2EBase {

    String pythonCodeWithFourNestedBlocks = """
for v in bla:
    if bar:
        if baz:
            if wiz:
                pass""";

    String ruleCode = """
        const checkError = (element, nestingLevel, maxNestintLevel) => {
           if (nestingLevel >= maxNestintLevel) {
             const error = buildError(
               element.start.line,
               element.start.col,
               element.end.line,
               element.end.col,
               "too many nesting level",
               "WARNING",
               "DESIGN"
             );
         
             addError(error);
           }
         };
         
         const check = (element, nestingLevel, maxNestintLevel) => {
           if (!element) {
             return;
           }
           if (element.astType === "ifstatement") {
             checkError(element, nestingLevel, maxNestintLevel);
             check(element.statements, nestingLevel + 1, maxNestintLevel);
             element.elifStatements.forEach((e) => {
               check(e.statements, nestingLevel + 1, maxNestintLevel);
             });
             check(element.elseStatements, nestingLevel + 1, maxNestintLevel);
           }
         
           if (element.astType === "forstatement") {
             checkError(element, nestingLevel);
             check(element.statements, nestingLevel + 1, maxNestintLevel);
           }
         
           if (element.astType === "sequence") {
             element.elements.forEach((e) => check(e, nestingLevel, maxNestintLevel));
           }
         
           if (element.astType === "elifstatement") {
             element.statements.elements.forEach((e) =>
               check(e, nestingLevel, maxNestintLevel)
             );
           }
           if (element.astType === "elsestatement") {
             element.statements.elements.forEach((e) =>
               check(e, nestingLevel, maxNestintLevel)
             );
           }
         };
         
         function visit(node, filename, code) {
           let maxNestingLevel = node.context.variables.get("maxNestingLevel");
           maxNestingLevel = maxNestingLevel === null ? 4 : Number(maxNestingLevel);
         
           check(node, 1, maxNestingLevel);
         }""";

    @Test
    @DisplayName("Check the nested blocks depth")
    public void testPythonNestedBlocksAccepted() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithFourNestedBlocks, Language.PYTHON, ruleCode, "nested-blocks", RuleType.AST_CHECK, EntityChecked.ANY, null, Map.of("maxNestingLevel", "5"), false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
        assertEquals(0, response.ruleResponses.get(0).errors.size());
        assertTrue(response.ruleResponses.get(0).executionTimeMs > 0);
    }

    @Test
    @DisplayName("Catch nested blocks depth with lowered max level variable")
    public void testPythonNestedBlocksLowerMaxLevel() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithFourNestedBlocks, Language.PYTHON, ruleCode, "nested-blocks", RuleType.AST_CHECK, EntityChecked.ANY, null, Map.of("maxNestingLevel", "4"), false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(0, response.ruleResponses.get(0).errors.size());
        assertEquals(4, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(13, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(5, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(21, response.ruleResponses.get(0).violations.get(0).end.col);
        assertEquals("too many nesting level", response.ruleResponses.get(0).violations.get(0).message);
        assertTrue(response.ruleResponses.get(0).executionTimeMs > 0);
    }
}
