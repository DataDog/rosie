package io.codiga.analyzer.ast.languages.python.treesitter;

import ai.serenade.treesitter.Node;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.ImportStatement.transformImportStatement;
import static org.junit.jupiter.api.Assertions.*;

public class ImportStatementTest extends PythonTestUtils {

    private final Logger log = Logger.getLogger("Test");

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
        Node rootNode = parseCode(code);
//        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.IMPORT_STATEMENT.label);
        assertEquals(1, nodes.size());
        var importStatement = transformImportStatement(nodes.get(0), parsingContext);

        var bla = List.of();
        // before Java 5
        for (int i = 0; i < bla.size(); i++) {
            Object o = bla.get(0);
        }


        assertTrue(importStatement.isPresent());
        assertEquals(1, importStatement.get().packages.length);
        assertEquals("collections.abc", importStatement.get().packages[0].name.str);
        assertNull(importStatement.get().packages[0].as);
    }

    @Test
    @DisplayName("Correctly map a triple import with as")
    public void testTransformTripleImport() {
        String code = "import collections.abc as foo, requests, shutil";

        Node rootNode = parseCode(code);
//        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.IMPORT_STATEMENT.label);
        assertEquals(1, nodes.size());
        var importStatement = transformImportStatement(nodes.get(0), parsingContext);
        assertTrue(importStatement.isPresent());

        assertEquals(3, importStatement.get().packages.length);
        assertEquals("collections.abc", importStatement.get().packages[0].name.str);
        assertEquals("foo", importStatement.get().packages[0].as.value);

        assertEquals("requests", importStatement.get().packages[1].name.str);
        assertNull(importStatement.get().packages[1].as);

        assertEquals("shutil", importStatement.get().packages[2].name.str);
        assertNull(importStatement.get().packages[2].as);
    }

}
