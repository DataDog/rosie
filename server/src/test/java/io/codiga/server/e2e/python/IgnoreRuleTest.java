package io.codiga.server.e2e.python;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.constants.Languages.ENTITY_CHECKED_FUNCTION_CALL;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IgnoreRuleTest extends E2EBase {


    private static final Logger logger = LoggerFactory.getLogger(IgnoreRuleTest.class);

    String pythonCodeWithError = """
        import requests
        # codiga-disable
        r = requests.get(w, verify=False)
        r = requests.get(w, verify=False, timeout=10)
                            """;


    String ruleCode = """
        function visit(node) {
            const hasTimeout = node.arguments.filter(a => a.name && a.name == "timeout").length > 0;
            const useRequestsPackage = node.context.imports.filter(i => i.packageNames).filter(i => i.packageName == "requests").length > 0;
            if(!hasTimeout && useRequestsPackage && node.functionName === "get" && node.moduleOrObject === "requests"){
                reportError(node.start.line, node.start.col, node.end.line, node.end.col, "timeout not defined", "CRITICAL", "SAFETY");
            }
        }
        """;

    @Test
    public void testPythonRequestTimeout() throws Exception {
        Response response = executeTest("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "python-timeout", RULE_TYPE_AST, ENTITY_CHECKED_FUNCTION_CALL, false);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
    }
}
