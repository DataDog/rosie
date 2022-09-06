package io.codiga.server.e2e.python;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.Test;

import static io.codiga.server.constants.Languages.ENTITY_CHECKED_FUNCTION_CALL;
import static io.codiga.server.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RequestTimeoutTest extends E2EBase {

    String pythonCodeWithError = """
        import requests
        r = requests.get(w, verify=False)
        r = requests.get(w, verify=False, timeout=10)
                            """;

    String pythonCodeNoImport = """
        r = requests.get(w, verify=False)
        print("foo")
        r = requests.get(w, verify=False, timeout=10)
                            """;

    String ruleCode = """
        function visit(node) {
            const hasTimeout = node.arguments.filter(a => a.name && a.name == "timeout").length > 0;
            const useRequestsPackage = node.getImports().filter(i => i.packageName == "requests").length > 0;
            if(!hasTimeout && useRequestsPackage && node.functionName === "get" && node.moduleOrObject === "requests"){
                reportError(node.start.line, node.start.col, node.end.line, node.end.col, "timeout not defined", "CRITICAL", "SAFETY");
            }
        }
        """;

    @Test
    public void testPythonRequestTimeout() throws Exception {
        Response response = executeTest("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "python-timeout", RULE_TYPE_AST, ENTITY_CHECKED_FUNCTION_CALL);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("timeout not defined", response.ruleResponses.get(0).violations.get(0).message);
    }

    @Test
    public void testPythonRequestTimeoutWithoutPackage() throws Exception {
        Response response = executeTest("bla.py", pythonCodeNoImport, Language.PYTHON, ruleCode, "python-timeout", RULE_TYPE_AST, ENTITY_CHECKED_FUNCTION_CALL);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
    }
}
