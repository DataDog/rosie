package io.codiga.server.e2e.python.pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpreadOperatorTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(SpreadOperatorTest.class);

    String codeWithTwoErrors = """
        import requests
                
                
        def my_function(arg1):     
            request1 = requests.get("https://www.api.com/endpoint", verify=False)
            request2 = requests.get("https://www.api.com/endpoint", verify=False)
        """;

    String codeWithTwoErrorsFirstErrorFixed = """
        import requests
                
                
        def my_function(arg1):     
            request1 = requests.get("https://www.api.com/endpoint", verify=True)
            request2 = requests.get("https://www.api.com/endpoint", verify=False)""";

    String codeWithTwoErrorsSecondErrorFixed = """
        import requests
                
                
        def my_function(arg1):     
            request1 = requests.get("https://www.api.com/endpoint", verify=False)
            request2 = requests.get("https://www.api.com/endpoint", verify=True)""";


    String ruleCodeUpdate = """
        function visit(pattern, filename, code) {
            if(filename.includes("_test.py") || filename.startsWith("test_")) {
                return;
            }
            const verifyVariable = pattern.variables.get("verify");
            if (verifyVariable && verifyVariable.value) {
                console.log(verifyVariable.value);
                if (verifyVariable.value === "False") {
                    console.log(verifyVariable.start.line);
                    console.log(verifyVariable.start.col);
                    const error = buildError(pattern.start.line, pattern.start.col, pattern.end.line, pattern.end.col, "use verify True", "WARN", "security");
                    const edit = buildEditUpdate(verifyVariable.start.line, verifyVariable.start.col, verifyVariable.end.line, verifyVariable.end.col, "True");
                    const fix = buildFix("use True", [edit]);
                    addError(error.addFix(fix));
                }
            }
        }
        """;


    String regex = "...requests.get(...verify=${verify}...)";


    @Test
    @DisplayName("Report two issues")
    public void testPythonAssertTwoViolations() throws Exception {
        Response response = executeTest("bla.py", codeWithTwoErrors, Language.PYTHON, ruleCodeUpdate, "requests",
            RuleType.REGEX, null, regex, true);
        // finally check the verified code
        logger.info("" + response);
        assertEquals(2, response.ruleResponses.get(0).violations.size());
        assertEquals(5, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(6, response.ruleResponses.get(0).violations.get(1).start.line);
        assertEquals(codeWithTwoErrorsFirstErrorFixed, applyFix(codeWithTwoErrors, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
        assertEquals(codeWithTwoErrorsSecondErrorFixed, applyFix(codeWithTwoErrors, response.ruleResponses.get(0).violations.get(1).fixes.get(0)));


    }


}
