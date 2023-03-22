package io.codiga.analyzer.ast.languages.python.treesitter;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.FromStatement;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.ImportFromStatement.transformImportFromStatement;
import static org.junit.jupiter.api.Assertions.*;

public class FromStmtToFromStatementTest extends PythonTestUtils {

    private Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Correctly map a from import")
    public void testTransformFromStatement() {
        String code = "from collections import bar, baz";

        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.IMPORT_FROM_STATEMENT.label);
        assertEquals(1, nodes.size());
        Optional<FromStatement> fromStatementOptional = transformImportFromStatement(nodes.get(0), parsingContext);

        assertTrue(fromStatementOptional.isPresent());
        FromStatement fromStatement = fromStatementOptional.get();
        assertEquals("collections", fromStatement.pkg.str);
        assertEquals(2, fromStatement.elements.length);
        assertEquals("bar", fromStatement.elements[0].name.str);
        assertEquals("baz", fromStatement.elements[1].name.str);
    }

    @Test
    @DisplayName("Correctly map a from import with as")
    public void testTransformFromStatementWithAs() {
        String code = "from collections import bar as boz, baz";
        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.IMPORT_FROM_STATEMENT.label);
        assertEquals(1, nodes.size());
        Optional<FromStatement> fromStatementOptional = transformImportFromStatement(nodes.get(0), parsingContext);

        assertTrue(fromStatementOptional.isPresent());
        FromStatement fromStatement = fromStatementOptional.get();

        assertEquals("collections", fromStatement.pkg.str);
        assertEquals(2, fromStatement.elements.length);
        assertEquals("bar", fromStatement.elements[0].name.str);
        assertEquals("boz", fromStatement.elements[0].as.str);
        assertEquals("baz", fromStatement.elements[1].name.str);
        assertNull(fromStatement.elements[1].as);
    }
}
