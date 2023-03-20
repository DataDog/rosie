package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.python.FromStatement;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static io.codiga.parser.antlr.python.transformations.ImportFromToFromStatement.transformFromStmtToFromStatement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.From_stmtContext.class);
        PythonParser.From_stmtContext from_stmtContext = (PythonParser.From_stmtContext) exprNodes.get(0);
        FromStatement fromStatement = transformFromStmtToFromStatement(from_stmtContext, null).get();
        assertEquals("collections", fromStatement.pkg.str);
        assertEquals(2, fromStatement.elements.length);
        assertEquals("bar", fromStatement.elements[0].name.str);
        assertEquals("baz", fromStatement.elements[1].name.str);
    }

    @Test
    @DisplayName("Correctly map a from import with as")
    public void testTransformFromStatementWithAs() {
        String code = "from collections import bar as boz, baz";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.From_stmtContext.class);
        PythonParser.From_stmtContext from_stmtContext = (PythonParser.From_stmtContext) exprNodes.get(0);
        FromStatement fromStatement = transformFromStmtToFromStatement(from_stmtContext, null).get();
        assertEquals("collections", fromStatement.pkg.str);
        assertEquals(2, fromStatement.elements.length);
        assertEquals("bar", fromStatement.elements[0].name.str);
        assertEquals("boz", fromStatement.elements[0].as.str);
        assertEquals("baz", fromStatement.elements[1].name.str);
        assertNull(fromStatement.elements[1].as);
    }
}
