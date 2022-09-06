package io.codiga.analyzer.pattern;

import io.codiga.analyzer.ast.languages.python.PythonTestUtils;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.model.pattern.PatternObject;
import io.codiga.model.pattern.PatternVariable;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class PatternMatcherTest extends PythonTestUtils {

    Logger logger = LoggerFactory.getLogger(PatternMatcherTest.class);
    String code = """
        def print_foo():
          with open("myfile.txt", "r") as myfile:
            content = myfile.read()
                    """;
    String ruleCode = """
        function visit(pattern) {
            console.log(pattern.position);
        }
        """;

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Extract the variables from the pattern")
    public void testVariablesExtraction() {
        String pattern = "open(${file}, \"r\")";
        AnalyzerRule rule = new AnalyzerRule("myrule", Language.PYTHON, RuleType.PATTERN, null, ruleCode, pattern);
        PatternMatcher patternMatcher = new PatternMatcher(code, rule);

        List<PatternVariable> variables = patternMatcher.getVariables();
        assertEquals(variables.size(), 1);
        assertEquals(variables.get(0).name(), "${file}");
        assertEquals(variables.get(0).start(), 6);
        assertEquals(variables.get(0).end(), 13);
    }

    @Test
    @DisplayName("Correctly transform the pattern into regular expression")
    public void testRegularExpressionGeneration() {
        String pattern = "open(${file}, \"${mode}\")";
        AnalyzerRule rule = new AnalyzerRule("myrule", Language.PYTHON, RuleType.PATTERN, null, ruleCode, pattern);
        PatternMatcher patternMatcher = new PatternMatcher(code, rule);

        List<PatternVariable> variables = patternMatcher.getVariables();
        assertEquals("open\\((.+), \"(.+)\"\\)", patternMatcher.getRegularExpression());
    }

    @Test
    @DisplayName("Correctly get the pattern object")
    public void testPatternObject() {
        String pattern = "open(${file}, \"${mode}\")";
        AnalyzerRule rule = new AnalyzerRule("myrule", Language.PYTHON, RuleType.PATTERN, null, ruleCode, pattern);
        PatternMatcher patternMatcher = new PatternMatcher(code, rule);
        List<PatternObject> patternObjects = patternMatcher.getPatternObjects();
        assertEquals(patternObjects.size(), 1);
        assertFalse(patternObjects.get(0).variables.isEmpty());
        assertEquals(patternObjects.get(0).variables.size(), 2);
    }
}
