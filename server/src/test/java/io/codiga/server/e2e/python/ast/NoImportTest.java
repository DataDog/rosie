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

public class NoImportTest extends E2EBase {

    String pythonCodeWithErrorSimpleImport = """
        import flask
        """;

    String pythonCodeWithErrorImportFrom = """
        from random import random""";

    String ruleCode = """
        function visit(node) {
            if(node.pkg && node.pkg.value && node.pkg.value === "random" &&
               node.elements && node.elements.length > 0 && node.elements.map(e => e.name.value).includes("random")) {
               reportError(node.start.line, node.start.col, node.end.line, node.end.col, "do not use random", "CRITICAL", "SAFETY");
             }
             
             if(node.packages && node.packages.length > 0 && node.packages.filter(p => p.name && p.name.value).map(p => p.name.value).includes("flask")) {
               reportError(node.start.line, node.start.col, node.end.line, node.end.col, "do not use flask", "CRITICAL", "SAFETY");
             }
        }
        """;

    @Test
    @DisplayName("Detect bad import")
    public void testPythonNoImport() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithErrorImportFrom, Language.PYTHON, ruleCode, "subprocess-with-shell", RuleType.AST_CHECK, EntityChecked.IMPORT_STATEMENT, null, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertTrue(response.ruleResponses.get(0).executionTimeMs > 0);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("do not use random", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals(0, response.ruleResponses.get(0).violations.get(0).fixes.size());
    }

    @Test
    @DisplayName("Detect bad import using from")
    public void testPythonNoImportFrom() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithErrorSimpleImport, Language.PYTHON, ruleCode, "subprocess-with-shell", RuleType.AST_CHECK, EntityChecked.IMPORT_STATEMENT, null, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertTrue(response.ruleResponses.get(0).executionTimeMs > 0);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("do not use flask", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals(0, response.ruleResponses.get(0).violations.get(0).fixes.size());
    }

}
