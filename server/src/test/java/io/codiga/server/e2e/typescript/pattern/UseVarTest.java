package io.codiga.server.e2e.typescript.pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.model.error.Category;
import io.codiga.model.error.Severity;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UseVarTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(UseVarTest.class);

    String code = """
        var myVariable = 1;""";


    String rule = """
        function visit(pattern, filename, code) {
            const variableName = pattern.variables.get("name");
            
            if (variableName) {
                const error = buildError(pattern.start.line, pattern.start.col, pattern.end.line, pattern.end.col, "should be a const", "info", "code_style");
                addError(error);
            }
            
        }
        """;


    String pattern = "...var ${name} = ...";

    @Test
    @DisplayName("Do not use var in typescript using a pattern, language typescript and rule javascript")
    public void testDoNotUseVarJavaScriptRule() throws Exception {
        Response response = executeTest("myfile.ts", code, Language.TYPESCRIPT, Language.JAVASCRIPT, rule, "do-not-use-var",
            RuleType.REGEX, null, pattern, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("should be a const", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals(Category.CODE_STYLE, response.ruleResponses.get(0).violations.get(0).category);
        assertEquals(Severity.NOTICE, response.ruleResponses.get(0).violations.get(0).severity);
    }


    @Test
    @DisplayName("Do not use var in typescript using a pattern, language typescript and rule typescript")
    public void testDoNotUseVarTypeScriptRule() throws Exception {
        Response response = executeTest("myfile.ts", code, Language.TYPESCRIPT, Language.TYPESCRIPT, rule, "do-not-use-var",
            RuleType.REGEX, null, pattern, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("should be a const", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals(Category.CODE_STYLE, response.ruleResponses.get(0).violations.get(0).category);
        assertEquals(Severity.NOTICE, response.ruleResponses.get(0).violations.get(0).severity);
    }

}
