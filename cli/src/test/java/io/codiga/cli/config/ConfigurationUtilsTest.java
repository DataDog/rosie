package io.codiga.cli.config;

import static io.codiga.cli.utils.SarifUtils.generateReport;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.cli.model.ViolationWithFilename;
import io.codiga.cli.model.sarif.SarifReport;
import io.codiga.cli.utils.SarifUtils;
import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.model.common.Position;
import io.codiga.model.error.Category;
import io.codiga.model.error.Edit;
import io.codiga.model.error.EditType;
import io.codiga.model.error.Fix;
import io.codiga.model.error.Severity;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import net.jimblackler.jsonschemafriend.Schema;
import net.jimblackler.jsonschemafriend.SchemaException;
import net.jimblackler.jsonschemafriend.SchemaStore;
import net.jimblackler.jsonschemafriend.Validator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConfigurationUtilsTest {

    private static final String VALID_FILE = "src/test/resources/yaml-configuration/valid1.yaml";
    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
        // Nothing to initialize
    }

    @AfterAll
    public static void done() {
        // Nothing to clean up.
    }

    @Test
    @DisplayName("Configuration is easily loaded")
    public void testConfigurationValid() {
        var c = ConfigurationUtils.getConfigurationFromFile(new File(VALID_FILE));
        assertTrue(c.isPresent());
        assertEquals(3, c.get().rulesets.size());
        assertEquals(3, c.get().ignorePaths.size());
        assertTrue(c.get().rulesets.contains("ruleset1"));
        assertTrue(c.get().rulesets.contains("ruleset2"));
        assertTrue(c.get().rulesets.contains("ruleset3"));
        assertTrue(c.get().ignorePaths.contains("path/to/ignore"));
        assertTrue(c.get().ignorePaths.contains("it/can/be/a/file"));
        assertTrue(c.get().ignorePaths.contains("or/a/glob/for/*/multiple/directories"));
    }

}
