package io.codiga.server.e2e.python.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.Test;

import static io.codiga.constants.Languages.ENTITY_CHECKED_FUNCTION_CALL;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RequestTimeoutMultipleAlternativesTest extends E2EBase {

    String pythonCodeWithError = """
        import requests
        r = requests.get(w, verify=False)
        r = requests.get(w, verify=False, timeout=10)""";

    String pythonCodeWithErrorFixes = """
        import requests
        r = requests.get(w, verify=False, timeout=5)
        r = requests.get(w, verify=False, timeout=10)""";

    String pythonCodeNoImport = """
        r = requests.get(w, verify=False)
        print("foo")
        r = requests.get(w, verify=False, timeout=10)""";

    String ruleCode = """
        function visit(node) {
            const hasTimeout = node.arguments.values.filter(a => a.name && a.name.value == "timeout").length > 0;
            const arguments = node.arguments.values;
            const nbArguments = node.arguments.values.length;
            const allPackages = node.context.imports.filter(i => i.packages).flatMap(i => i.packages.map(p => p.name.str));
            const useRequestsPackage = allPackages.filter(i => i === "requests").length > 0;
            if(!hasTimeout && useRequestsPackage && node.functionName.value === "get" && node.moduleOrObject.value === "requests"){
                const error = buildError(node.start.line, node.start.col, node.end.line, node.end.col, "timeout not defined", "CRITICAL", "SAFETY");
                const lineToInsert = arguments[arguments.length - 1].end.line;
                const colToInsert = arguments[arguments.length - 1].end.col;
                const edit1 = buildEditAdd(lineToInsert, colToInsert, ", timeout=5")
                const edit2 = buildEditAdd(lineToInsert, colToInsert, ", timeout=10")
                const fix1 = buildFix("add timeout argument", [edit1]);
                const fix2 = buildFix("add timeout argument", [edit2]);
                addError(error.addFix(fix1).addFix(fix2));
            }
        }
        """;

    @Test
    public void testPythonRequestTimeout() throws Exception {
        Response response = executeTest("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "python-timeout", RULE_TYPE_AST, ENTITY_CHECKED_FUNCTION_CALL, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(2, response.ruleResponses.get(0).violations.get(0).start.line);

        assertEquals(2, response.ruleResponses.get(0).violations.get(0).fixes.size());
    }


}
