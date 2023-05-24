package io.codiga.analyzer.pattern;

import io.codiga.analyzer.ast.languages.python.antlr.PythonTestUtils;
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
        AnalyzerRule rule = new AnalyzerRule("myrule", "myruledescription", Language.PYTHON, RuleType.REGEX, null, ruleCode, pattern, null, null);
        PatternMatcher patternMatcher = new PatternMatcher(code, rule);

        List<PatternVariable> variables = patternMatcher.getVariablesFromPattern(pattern);
        assertEquals(variables.size(), 1);
        assertEquals(variables.get(0).name(), "${file}");
        assertEquals(variables.get(0).start(), 6);
        assertEquals(variables.get(0).end(), 13);
    }

    @Test
    @DisplayName("Correctly transform the pattern into regular expression")
    public void testRegularExpressionGeneration() {
        String pattern = "open(${file}, \"${mode}\")";
        AnalyzerRule rule = new AnalyzerRule("myrule","myruledescription", Language.PYTHON, RuleType.REGEX, null, ruleCode, pattern, null, null);
        PatternMatcher patternMatcher = new PatternMatcher(code, rule);

        List<PatternVariable> variables = patternMatcher.getVariablesFromPattern(pattern);
        assertEquals("open\\((.+), \"(.+)\"\\)", patternMatcher.getRegularExpression(variables));
    }

    @Test
    @DisplayName("Correctly get the pattern object with one line")
    public void testPatternObjectOneLine() {
        String pattern = "open(${file}, \"${mode}\")";
        String localCode = "open(\"foo\", \"r\")";
        AnalyzerRule rule = new AnalyzerRule("myrule","myruledescription", Language.PYTHON, RuleType.REGEX, null, ruleCode, pattern, null, null);
        PatternMatcher patternMatcher = new PatternMatcher(localCode, rule);
        List<PatternObject> patternObjects = patternMatcher.getPatternObjects();
        assertEquals(patternObjects.size(), 1);
        assertFalse(patternObjects.get(0).javaVariables.isEmpty());
        assertEquals(patternObjects.get(0).javaVariables.size(), 2);
        assertEquals(1, patternObjects.get(0).start.line);
        assertEquals(1, patternObjects.get(0).start.col);
        assertEquals(1, patternObjects.get(0).end.line);
        assertEquals(17, patternObjects.get(0).end.col);
        assertEquals(0, patternObjects.get(0).startIndex);
        assertEquals(16, patternObjects.get(0).endIndex);

        assertEquals(1, patternObjects.get(0).javaVariables.get("file").start.line);
        assertEquals(6, patternObjects.get(0).javaVariables.get("file").start.col);
        assertEquals(1, patternObjects.get(0).javaVariables.get("file").end.line);
        assertEquals(11, patternObjects.get(0).javaVariables.get("file").end.col);

        assertEquals(1, patternObjects.get(0).javaVariables.get("mode").start.line);
        assertEquals(14, patternObjects.get(0).javaVariables.get("mode").start.col);
        assertEquals(1, patternObjects.get(0).javaVariables.get("mode").end.line);
        assertEquals(15, patternObjects.get(0).javaVariables.get("mode").end.col);
    }

    @Test
    @DisplayName("Correctly get the pattern object with two lines")
    public void testPatternObjectTwoLines() {
        String pattern = "open(${file}, \"${mode}\")";
        String localCode = "bla\nbli open(\"foo\", \"r\")";
        AnalyzerRule rule = new AnalyzerRule("myrule","myruledescription", Language.PYTHON, RuleType.REGEX, null, ruleCode, pattern, null, null);
        PatternMatcher patternMatcher = new PatternMatcher(localCode, rule);
        List<PatternObject> patternObjects = patternMatcher.getPatternObjects();
        assertEquals(1, patternObjects.size());
        assertFalse(patternObjects.get(0).javaVariables.isEmpty());
        assertEquals(patternObjects.get(0).javaVariables.size(), 2);
        assertEquals(2, patternObjects.get(0).start.line);
        assertEquals(5, patternObjects.get(0).start.col);
        assertEquals(2, patternObjects.get(0).end.line);
        assertEquals(21, patternObjects.get(0).end.col);
        assertEquals(8, patternObjects.get(0).startIndex);
        assertEquals(24, patternObjects.get(0).endIndex);

        assertEquals(2, patternObjects.get(0).javaVariables.get("file").start.line);
        assertEquals(10, patternObjects.get(0).javaVariables.get("file").start.col);
        assertEquals(2, patternObjects.get(0).javaVariables.get("file").end.line);
        assertEquals(15, patternObjects.get(0).javaVariables.get("file").end.col);

        assertEquals(2, patternObjects.get(0).javaVariables.get("mode").start.line);
        assertEquals(18, patternObjects.get(0).javaVariables.get("mode").start.col);
        assertEquals(2, patternObjects.get(0).javaVariables.get("mode").end.line);
        assertEquals(19, patternObjects.get(0).javaVariables.get("mode").end.col);
    }
}
