package io.codiga.analyzer.ast.languages.typescript;

import io.codiga.model.ast.javascript.JavaScriptImport;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptImportStatementToImport.transformImportStatementToImport;
import static org.junit.jupiter.api.Assertions.*;

public class ImportTest extends TypeScriptTestUtils {

    private final Logger log = Logger.getLogger("Test");

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

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.ImportStatementContext.class);

        assertEquals(1, nodes.size());

        Optional<JavaScriptImport> importOptional = transformImportStatementToImport((TypeScriptParser.ImportStatementContext) nodes.get(0), null);
        assertTrue(importOptional.isPresent());
        assertEquals("\"module-name\"", importOptional.get().pkg.value);
        assertEquals(1, importOptional.get().importedNames.length);
        assertEquals("defaultExport", importOptional.get().importedNames[0].name.value);
        assertNull(importOptional.get().importedNames[0].as);

    }

    @Test
    @DisplayName("import star with alias")
    public void testImportAllAlias() {
        String code = """
            import * as name from "module-name";
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.ImportStatementContext.class);
        assertEquals(1, nodes.size());

        Optional<JavaScriptImport> importOptional = transformImportStatementToImport((TypeScriptParser.ImportStatementContext) nodes.get(0), null);
        assertTrue(importOptional.isPresent());
        assertEquals("\"module-name\"", importOptional.get().pkg.value);
        assertEquals(1, importOptional.get().importedNames.length);
        assertEquals("*", importOptional.get().importedNames[0].name.value);
        assertEquals("name", importOptional.get().importedNames[0].as.value);
    }

}
