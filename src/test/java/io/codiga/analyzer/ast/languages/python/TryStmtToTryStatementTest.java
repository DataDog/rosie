package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.python.TryStatement;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.python.TryStmtToTryStatement.transformStmtToTryStatement;
import static org.junit.jupiter.api.Assertions.*;

public class TryStmtToTryStatementTest extends PythonTestUtils {

    private Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Correctly transform a try statement with multiple exceptions")
    public void testTransformTryStatementMultipleExceptions() {
        String code = """
            try:
                pass
            except (SocketTimeout, ValueError):
                pass
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Try_stmtContext.class);
        PythonParser.Try_stmtContext stmtContext = (PythonParser.Try_stmtContext) exprNodes.get(0);
        TryStatement tryStatement = transformStmtToTryStatement(stmtContext, null).get();
        assertNotNull(tryStatement.exceptClause);
        assertNull(tryStatement.exceptClause.as);
        assertEquals("SocketTimeout", tryStatement.exceptClause.exceptions[0].str);
        assertEquals("ValueError", tryStatement.exceptClause.exceptions[1].str);
        assertEquals(2, tryStatement.exceptClause.exceptions.length);
    }

    @Test
    @DisplayName("Correctly transform a try statement with multiple exceptions and as")
    public void testTransformTryStatementMultipleExceptionsAndAs() {
        String code = """
            try:
                pass
            except (SocketTimeout, ValueError) as e:
                pass
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Try_stmtContext.class);
        PythonParser.Try_stmtContext stmtContext = (PythonParser.Try_stmtContext) exprNodes.get(0);
        TryStatement tryStatement = transformStmtToTryStatement(stmtContext, null).get();
        assertNotNull(tryStatement.exceptClause);
        assertNotNull(tryStatement.exceptClause.as);
        assertEquals("e", tryStatement.exceptClause.as.str);
        assertEquals("SocketTimeout", tryStatement.exceptClause.exceptions[0].str);
        assertEquals("ValueError", tryStatement.exceptClause.exceptions[1].str);
        assertEquals(2, tryStatement.exceptClause.exceptions.length);
    }

    @Test
    @DisplayName("Correctly transform a try statement with no exception")
    public void testTransformTryStatementNoException() {
        String code = """
            try:
                pass
            except:
                pass
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Try_stmtContext.class);
        PythonParser.Try_stmtContext stmtContext = (PythonParser.Try_stmtContext) exprNodes.get(0);
        TryStatement tryStatement = transformStmtToTryStatement(stmtContext, null).get();
        assertNotNull(tryStatement.exceptClause);
        assertNull(tryStatement.exceptClause.as);
        assertEquals(0, tryStatement.exceptClause.exceptions.length);
    }

}
