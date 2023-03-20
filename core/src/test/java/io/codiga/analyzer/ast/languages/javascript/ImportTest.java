package io.codiga.analyzer.ast.languages.javascript;

import io.codiga.model.ast.javascript.JavaScriptImport;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.antlr.javascript.transformations.JavaScriptImportStatementToImport.transformImportStatementToImport;
import static org.junit.jupiter.api.Assertions.*;

public class ImportTest extends JavaScriptTestUtils {

    private Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }


    @Test
    @DisplayName("default import")
    public void testDefaultImport() {
        String code = """
            import defaultExport from "module-name";
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.ImportStatementContext.class);

        for (ParseTree node : nodes) {
            Optional<JavaScriptImport> importOptional = transformImportStatementToImport((JavaScriptParser.ImportStatementContext) node, null);
            assertTrue(importOptional.isPresent());
            assertEquals("\"module-name\"", importOptional.get().pkg.value);
            assertEquals(1, importOptional.get().importedNames.length);
            assertEquals("defaultExport", importOptional.get().importedNames[0].name.value);
            assertNull(importOptional.get().importedNames[0].as);
        }
    }

    @Test
    @DisplayName("import star with alias")
    public void testImportAllAlias() {
        String code = """
            import * as name from "module-name";
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.ImportStatementContext.class);

        for (ParseTree node : nodes) {
            Optional<JavaScriptImport> importOptional = transformImportStatementToImport((JavaScriptParser.ImportStatementContext) node, null);
            assertTrue(importOptional.isPresent());
            assertEquals("\"module-name\"", importOptional.get().pkg.value);
            assertEquals(1, importOptional.get().importedNames.length);
            assertEquals("*", importOptional.get().importedNames[0].name.value);
            assertEquals("name", importOptional.get().importedNames[0].as.value);
        }
    }


    @Test
    @DisplayName("import star with alias")
    public void testMultipleImports() {
        String code = """
            import { export1 as alias1, export2 } from "module-name";
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.ImportStatementContext.class);

        for (ParseTree node : nodes) {
            Optional<JavaScriptImport> importOptional = transformImportStatementToImport((JavaScriptParser.ImportStatementContext) node, null);
            assertTrue(importOptional.isPresent());
            assertEquals("\"module-name\"", importOptional.get().pkg.value);
            assertEquals(2, importOptional.get().importedNames.length);
            assertEquals("export1", importOptional.get().importedNames[0].name.value);
            assertEquals("alias1", importOptional.get().importedNames[0].as.value);
            assertEquals("export2", importOptional.get().importedNames[1].name.value);
            assertNull(importOptional.get().importedNames[1].as);
        }
    }
}
