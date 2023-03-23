package io.codiga.analyzer.ast.languages.python.antlr;

import io.codiga.model.ast.python.ImportStatement;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static io.codiga.parser.antlr.python.transformations.ImportStmtToImportStatement.transformImportStmtToImportStatement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ImportStmtToImportStatementTest extends PythonTestUtils {

    private Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Correctly map a single import")
    public void testTransformSimpleImport() {
        String code = "import collections.abc";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Import_stmtContext.class);
        PythonParser.Import_stmtContext import_stmtContext = (PythonParser.Import_stmtContext) exprNodes.get(0);
        ImportStatement importStatement = transformImportStmtToImportStatement(import_stmtContext, null).get();
        assertEquals(1, importStatement.packages.length);
        assertEquals("collections.abc", importStatement.packages[0].name.str);
        assertNull(importStatement.packages[0].as);
    }

    @Test
    @DisplayName("Correctly map a triple import with as")
    public void testTransformTripleImport() {
        String code = "import collections.abc as foo, requests, shutil";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Import_stmtContext.class);
        PythonParser.Import_stmtContext import_stmtContext = (PythonParser.Import_stmtContext) exprNodes.get(0);
        ImportStatement importStatement = transformImportStmtToImportStatement(import_stmtContext, null).get();
        assertEquals(3, importStatement.packages.length);
        assertEquals("collections.abc", importStatement.packages[0].name.str);
        assertEquals("foo", importStatement.packages[0].as.value);

        assertEquals("requests", importStatement.packages[1].name.str);
        assertNull(importStatement.packages[1].as);

        assertEquals("shutil", importStatement.packages[2].name.str);
        assertNull(importStatement.packages[2].as);
    }

}
