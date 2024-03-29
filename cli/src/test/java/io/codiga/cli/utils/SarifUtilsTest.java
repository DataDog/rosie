package io.codiga.cli.utils;

import static io.codiga.cli.utils.SarifUtils.generateReport;
import static io.codiga.cli.utils.SarifUtils.uriReference;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.cli.model.ViolationWithFilename;
import io.codiga.cli.model.sarif.SarifReport;
import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.model.common.Position;
import io.codiga.model.error.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Stream;
import net.jimblackler.jsonschemafriend.Schema;
import net.jimblackler.jsonschemafriend.SchemaException;
import net.jimblackler.jsonschemafriend.SchemaStore;
import net.jimblackler.jsonschemafriend.Validator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
        var violation = ViolationWithFilename.builder()
            .start(new Position(1, 2))
            .end(new Position(2, 3))
            .message("error message")
            .severity(Severity.ERROR)
            .category(Category.BEST_PRACTICES)
            .filename("myfile")
            .rule("myrule")
            .build();

        assertTrue(checkCompliance(
            generateReport(
                List.of(new AnalyzerRule("myrule", "myshortdescription", "myruledescription", Language.PYTHON, RuleType.AST_CHECK, EntityChecked.ASSIGNMENT, "code", null, null, Map.of(), false)),
                List.of(new File("foo/bar").toPath()),
                List.of(violation),
                List.of())));
    }

    @Test
    @DisplayName("Check a report with a violation and an addition")
    public void textFixAddition() {
        var violation = ViolationWithFilename.builder()
            .start(new Position(1, 2))
            .end(new Position(2, 3))
            .message("error message")
            .severity(Severity.ERROR)
            .category(Category.BEST_PRACTICES)
            .filename("myfile")
            .rule("myrule")
            .fixes(
                List.of(
                    new Fix("my fix", List.of(new Edit(new Position(1, 2), null, EditType.ADD, "mycode"))))
            )
            .build();

        assertTrue(checkCompliance(
            generateReport(
                List.of(new AnalyzerRule("myrule", "myshortdescription", "myruledescription", Language.PYTHON, RuleType.AST_CHECK, EntityChecked.ASSIGNMENT, "code", null, null, Map.of(), false)),
                List.of(new File("foo/bar").toPath()),
                List.of(violation),
                List.of())));
    }

    @Test
    @DisplayName("Check a report with a violation and an update")
    public void textFixUpdate() {
        var violation = ViolationWithFilename.builder()
            .start(new Position(1, 2))
            .end(new Position(2, 3))
            .message("error message")
            .severity(Severity.ERROR)
            .category(Category.BEST_PRACTICES)
            .filename("myfile")
            .rule("myrule")
            .fixes(
                List.of(
                    new Fix("my fix", List.of(new Edit(new Position(1, 2), new Position(2, 4), EditType.UPDATE, "mycode"))))
            )
            .build();

        assertTrue(checkCompliance(
            generateReport(
                List.of(new AnalyzerRule("myrule", "myshortdescription","myruledescription", Language.PYTHON, RuleType.AST_CHECK, EntityChecked.ASSIGNMENT, "code", null, null, Map.of(), false)),
                List.of(new File("foo/bar").toPath()),
                List.of(violation),
                List.of())));
    }

    @Test
    @DisplayName("Check the correct position of ruleIndex")
    public void testCheckRuleIndex() {
        var violation1 = ViolationWithFilename.builder()
            .start(new Position(1, 2))
            .end(new Position(2, 3))
            .message("error message")
            .severity(Severity.ERROR)
            .category(Category.BEST_PRACTICES)
            .filename("myfile")
            .rule("myrule2")
            .fixes(
                List.of(
                    new Fix("my fix", List.of(new Edit(new Position(1, 2), new Position(2, 4), EditType.UPDATE, "mycode"))))
            )
            .build();

        var violation2 = ViolationWithFilename.builder()
            .start(new Position(1, 2))
            .end(new Position(2, 3))
            .message("error message")
            .severity(Severity.ERROR)
            .category(Category.BEST_PRACTICES)
            .filename("myfile")
            .rule("myrule3")
            .fixes(
                List.of(
                    new Fix("my fix", List.of(new Edit(new Position(1, 2), new Position(2, 4), EditType.UPDATE, "mycode"))))
            )
            .build();

        SarifReport sarifReport = generateReport(
            List.of(
                new AnalyzerRule("myrule1", "myshortdescription","myruledescription", Language.PYTHON, RuleType.AST_CHECK, EntityChecked.ASSIGNMENT, "code", null, null, Map.of(), false),
                new AnalyzerRule("myrule2", "myshortdescription","myruledescription", Language.PYTHON, RuleType.AST_CHECK, EntityChecked.ASSIGNMENT, "code", null, null, Map.of(), false)),
            List.of(new File("foo/bar").toPath()),
            List.of(violation1, violation2),
            List.of());

        assertEquals("myrule1", sarifReport.runs.get(0).tool.driver.rules.get(0).id);
        assertEquals("myshortdescription", sarifReport.runs.get(0).tool.driver.rules.get(0).shortDescription.text);
        assertEquals("myruledescription", sarifReport.runs.get(0).tool.driver.rules.get(0).fullDescription.text);
        assertEquals("myrule2", sarifReport.runs.get(0).tool.driver.rules.get(1).id);
        assertEquals("myshortdescription", sarifReport.runs.get(0).tool.driver.rules.get(1).shortDescription.text);
        assertEquals("myruledescription", sarifReport.runs.get(0).tool.driver.rules.get(1).fullDescription.text);

        assertEquals("myrule2", sarifReport.runs.get(0).results.get(0).ruleId);
        assertEquals(1, sarifReport.runs.get(0).results.get(0).ruleIndex);

        assertEquals("myrule3", sarifReport.runs.get(0).results.get(1).ruleId);
        assertEquals(-1, sarifReport.runs.get(0).results.get(1).ruleIndex);

    }


    @Test
    @DisplayName("Check a report with a violation and one deletion")
    public void textFixDelete() {
        var violation = ViolationWithFilename.builder()
            .start(new Position(1, 2))
            .end(new Position(2, 3))
            .message("error message")
            .severity(Severity.ERROR)
            .category(Category.BEST_PRACTICES)
            .filename("myfile")
            .rule("myrule")
            .fixes(
                List.of(
                    new Fix("my fix", List.of(new Edit(new Position(1, 2), new Position(2, 4), EditType.REMOVE, null))))
            )
            .build();

        assertTrue(checkCompliance(
            generateReport(
                List.of(new AnalyzerRule("myrule", "myshortdescription","myruledescription", Language.PYTHON, RuleType.AST_CHECK, EntityChecked.ASSIGNMENT, "code", null, null, Map.of(), false)),
                List.of(new File("foo/bar").toPath()),
                List.of(violation),
                List.of())));
    }

    @Test
    @DisplayName("Violations that starts at line 0 are invalid")
    public void testInvalidLineNumbers() {
        var violation = ViolationWithFilename.builder()
            .start(new Position(0, 2))
            .end(new Position(2, 3))
            .message("error message")
            .severity(Severity.ERROR)
            .category(Category.BEST_PRACTICES)
            .filename("myfile")
            .rule("myrule")
            .build();

        assertFalse(checkCompliance(
            generateReport(
                List.of(new AnalyzerRule("myrule", "myshortdescription", "myruledescription", Language.PYTHON, RuleType.AST_CHECK, EntityChecked.ASSIGNMENT, "code", null, null, Map.of(), false)),
                List.of(new File("foo/bar").toPath()),
                List.of(violation),
                List.of())));
    }

    @Test
    @DisplayName("Check a report with a rule that has an shouldUseAiFix tag of true")
    public void testShouldAiFixTag() {
        var violation = ViolationWithFilename.builder()
            .start(new Position(0, 2))
            .end(new Position(2, 3))
            .message("error message")
            .severity(Severity.ERROR)
            .category(Category.BEST_PRACTICES)
            .filename("myfile")
            .rule("myrule")
            .build();

        assertFalse(checkCompliance(
            generateReport(
                List.of(new AnalyzerRule("myrule", "myshortdescription", "myruledescription", Language.PYTHON, RuleType.AST_CHECK, EntityChecked.ASSIGNMENT, "code", null, null, Map.of(), true)),
                List.of(new File("foo/bar").toPath()),
                List.of(violation),
                List.of())));
    }

    public static Stream<Arguments> generateURIs() {
        return Stream.of(
                Arguments.of("foo/bar", "foo/bar"),
                Arguments.of("/foo/bar","foo/bar"),
                Arguments.of("/foo xyz/bar", "foo%20xyz/bar")
        );
    }

    @ParameterizedTest
    @MethodSource("generateURIs")
    @DisplayName("URI-references generated are valid.")
    public void testUriReferenceGeneration(String uri, String expectedUriReference) {
        assertEquals(expectedUriReference, uriReference(uri).toString());
    }
}
