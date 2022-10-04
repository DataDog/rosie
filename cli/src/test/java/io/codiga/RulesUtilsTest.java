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

import static io.codiga.cli.RulesUtils.getRulesFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RulesUtilsTest {

    ClassLoader classLoader = getClass().getClassLoader();
    private Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Parse all rules from a file")
    public void testRulesParsing() throws IOException {
        String file = "src/test/resources/rules.json";
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
}
