package io.codiga.cli.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.cli.model.ViolationWithFilename;
import io.codiga.cli.model.sarif.SarifReport;
import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.model.common.Position;
import io.codiga.model.error.Category;
import io.codiga.model.error.Severity;
import net.jimblackler.jsonschemafriend.Schema;
import net.jimblackler.jsonschemafriend.SchemaException;
import net.jimblackler.jsonschemafriend.SchemaStore;
import net.jimblackler.jsonschemafriend.Validator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static io.codiga.cli.utils.SarifUtils.generateReport;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SarifUtilsTest {

    private static final String SARIF_SCHEMA_PATH = "src/test/resources/sarif-standard/sarif-schema-2.1.0.json";
    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
        // Nothing to initialize
    }

    @AfterAll
    public static void done() {
        // Nothing to clean up.
    }

    private boolean checkCompliance(SarifReport sarifReport) {
        boolean pass = true;
        try {
            String schemaContent = Files.readString(new File(SARIF_SCHEMA_PATH).toPath());

            SchemaStore schemaStore = new SchemaStore(); // Initialize a SchemaStore.
            Schema schema = schemaStore.loadSchemaJson(schemaContent); // Load the schema.
            Validator validator = new Validator(); // Create a validator.
            ObjectMapper objectMapper = SarifUtils.getSarifObjectMapper();
            String jsonObject = objectMapper.writeValueAsString(sarifReport);
            validator.validateJson(schema, jsonObject); // Will not throw an exception.
        } catch (SchemaException | IOException e) {
            e.printStackTrace();
            pass = false;
        }
        return pass;
    }


    @Test
    @DisplayName("Empty reports are working fine")
    public void testEmptyReport() {
        assertTrue(checkCompliance(generateReport(List.of(), List.of(), List.of(), List.of())));
    }

    @Test
    @DisplayName("Check reports with all entries are correct")
    public void testNormalReport() {
        assertTrue(checkCompliance(
            generateReport(
                List.of(new AnalyzerRule("myrule", Language.PYTHON, RuleType.AST_CHECK, EntityChecked.ASSIGNMENT, "code", null, null, Map.of())),
                List.of(new File("foo/bar").toPath()),
                List.of(new ViolationWithFilename(new Position(0, 1), new Position(1, 2), "error message", Severity.CRITICAL, Category.BEST_PRACTICE, "myfile", "myrule")),
                List.of())));
    }
}
