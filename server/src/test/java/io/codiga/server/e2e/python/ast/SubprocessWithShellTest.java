package io.codiga.server.e2e.python.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.Test;

public class SubprocessWithShellTest extends E2EBase {

    String pythonCodeWithError = """
        import subprocess
        subprocess.Popen('/bin/ls %s' % ('something',), shell=True)
                            """;

    String pythonCodeNoImport = """
        subprocess.Popen('/bin/ls %s' % ('something',), shell=True)
                            """;

    String ruleCode = """
        function visit(node) {
            const hasShellTrue = node.arguments.values.filter(a => a.name && a.name.value == "shell" && a.value && a.value.value == "True").length > 0;
            const allPackages = node.context.imports.filter(i => i.packages).flatMap(i => i.packages.map(p => p.name.str));
            const useSubprocessPackage = allPackages.filter(i => i === "subprocess").length > 0;
            if(hasShellTrue && useSubprocessPackage && node.functionName.value === "Popen" && node.moduleOrObject.value === "subprocess"){
                reportError(node.start.line, node.start.col, node.end.line, node.end.col, "shell defined with true", "CRITICAL", "SAFETY");
            }
        }
        """;

    @Test
    public void testPythonSubprocessWithShell() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "subprocess-with-shell", RuleType.AST_CHECK, EntityChecked.FUNCTION_CALL, null, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("shell defined with true", response.ruleResponses.get(0).violations.get(0).message);
    }

    @Test
    public void testPythonSubprocessWithShellWithoutPackage() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeNoImport, Language.PYTHON, ruleCode, "subprocess-with-shell", RuleType.AST_CHECK, EntityChecked.FUNCTION_CALL, null, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
    }
}
