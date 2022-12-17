package io.codiga.server.e2e.javascript.pattern;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.constants.Languages.RULE_TYPE_PATTERN;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UseStatePattern extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(UseStatePattern.class);

    String code = """
        const [getVariable, setVariable] = useState(defaultValue);""";


    String rule = """
        function visit(pattern, filename, code) {
            const getVariable = pattern.variables.get("GET");
            const setVariable = pattern.variables.get("SET");
            const defaultValueVariable = pattern.variables.get("DEFAULTVALUE");
            console.log("PLOP");
            if (getVariable && setVariable && defaultValueVariable) {
              
                const error = buildError(pattern.start.line, pattern.start.col, pattern.end.line, pattern.end.col, "should be a const", "info", "code_style");
                addError(error);
            }
            
        }
        """;


    String pattern = "const [${GET}, ${SET}] = useState(${DEFAULTVALUE})";

    @Test
    @DisplayName("match multiple variables")
    public void testDoNotUseVar() throws Exception {
        Response response = executeTest("myfile.js", code, Language.JAVASCRIPT, rule, "do-not-use-var",
            RULE_TYPE_PATTERN, null, pattern, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("should be a const", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals("CODE_STYLE", response.ruleResponses.get(0).violations.get(0).category);
        assertEquals("INFORMATIONAL", response.ruleResponses.get(0).violations.get(0).severity);
    }

}
