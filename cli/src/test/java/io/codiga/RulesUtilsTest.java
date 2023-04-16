package io.codiga;

import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static io.codiga.cli.utils.RulesUtils.getRulesFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class RulesUtilsTest {

    ClassLoader classLoader = getClass().getClassLoader();
    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Parse all rules from a file")
    public void testRulesParsing() throws IOException {
        String file = "src/test/resources/1rule.json";
        List<AnalyzerRule> rules = getRulesFromFile(file);
        assert (rules.size() == 1);
        AnalyzerRule rule = rules.stream().findFirst().get();
        assertEquals(rule.entityChecked(), EntityChecked.FUNCTION_CALL);
        assertEquals(rule.language(), Language.PYTHON);
    }

    @Test
    @DisplayName("Parse all rules from a file with two rules in a single file")
    public void testRulesParsingTwoRules() throws IOException {
        String file = "src/test/resources/2rules.json";
        List<AnalyzerRule> rules = getRulesFromFile(file);
        assert (rules.size() == 2);
        AnalyzerRule rule = rules.stream().findFirst().get();
        assertEquals(rule.entityChecked(), EntityChecked.FUNCTION_CALL);
        assertEquals(rule.language(), Language.PYTHON);
    }

    @Test
    @DisplayName("Parse all with new elements checked")
    public void testJavascriptRules() throws IOException {
        String file = "src/test/resources/jsrules.json";
        List<AnalyzerRule> rules = getRulesFromFile(file);
        assert (rules.size() == 4);
        assertEquals(rules.get(0).entityChecked(), EntityChecked.HTML_ELEMENT);
        assertEquals(rules.get(0).language(), Language.JAVASCRIPT);
        assertEquals(rules.get(1).entityChecked(), EntityChecked.CLASS_DEFINITION);
        assertEquals(rules.get(1).language(), Language.JAVASCRIPT);
        assertEquals(rules.get(2).entityChecked(), EntityChecked.INTERFACE);
        assertEquals(rules.get(2).language(), Language.JAVASCRIPT);
        assertEquals(rules.get(3).entityChecked(), EntityChecked.FUNCTION_EXPRESSION);
        assertEquals(rules.get(3).language(), Language.JAVASCRIPT);

    }

    @Test
    @DisplayName("Parse all variables from a rule")
    public void testRulesWithVariables() throws IOException {
        String file = "src/test/resources/rules-with-variables.json";
        List<AnalyzerRule> rules = getRulesFromFile(file);
        assert (rules.size() == 1);
        AnalyzerRule rule = rules.stream().findFirst().get();
        assert (rule.variables().size() == 2);
        assertEquals(rule.variables().get("key1"), "value1");
        assertEquals(rule.variables().get("key2"), "value2");
        assertNull(rule.variables().get("key3"));
    }

    @Test
    @DisplayName("Parse empty variables from a rule")
    public void testRulesWithNoVariables() throws IOException {
        String file = "src/test/resources/rules-with-no-variables.json";
        List<AnalyzerRule> rules = getRulesFromFile(file);
        assert (rules.size() == 1);
        AnalyzerRule rule = rules.stream().findFirst().get();
        assert (rule.variables() == null);
    }
}
