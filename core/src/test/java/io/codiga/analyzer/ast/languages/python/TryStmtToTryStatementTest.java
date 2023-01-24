package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.common.Sequence;
import io.codiga.model.ast.python.TryStatement;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.python.transformations.TryStmtToTryStatement.transformStmtToTryStatement;
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
        assertNotNull(tryStatement.exceptClauses[0]);
        assertNull(tryStatement.exceptClauses[0].as);
        assertEquals("SocketTimeout", tryStatement.exceptClauses[0].exceptions[0].str);
        assertEquals("ValueError", tryStatement.exceptClauses[0].exceptions[1].str);
        assertEquals(2, tryStatement.exceptClauses[0].exceptions.length);
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
        assertNotNull(tryStatement.exceptClauses[0]);
        assertNotNull(tryStatement.exceptClauses[0].as);
        assertEquals("e", tryStatement.exceptClauses[0].as.str);
        assertEquals("SocketTimeout", tryStatement.exceptClauses[0].exceptions[0].str);
        assertEquals("ValueError", tryStatement.exceptClauses[0].exceptions[1].str);
        assertEquals(2, tryStatement.exceptClauses[0].exceptions.length);
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
        assertNotNull(tryStatement.exceptClauses[0]);
        assertNull(tryStatement.exceptClauses[0].as);
        assertEquals(0, tryStatement.exceptClauses[0].exceptions.length);
    }

    @Test
    @DisplayName("Correctly transform a try statement with no exception")
    public void testMultipleExceptions() {
        String code = """
            try:
                client_obj.get_url(url)
            except (URLError, ValueError):
                client_obj.remove_url(url)
            except SocketTimeout:
                client_obj.handle_url_timeout(url)
            finally:
                pass
                        """;

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Try_stmtContext.class);
        PythonParser.Try_stmtContext stmtContext = (PythonParser.Try_stmtContext) exprNodes.get(0);
        TryStatement tryStatement = transformStmtToTryStatement(stmtContext, null).get();
        assertNotNull(tryStatement.exceptClauses);
        assertNull(tryStatement.exceptClauses[0].as);
        assertEquals(2, tryStatement.exceptClauses[0].exceptions.length);
        assertEquals("URLError", tryStatement.exceptClauses[0].exceptions[0].str);
        assertEquals("ValueError", tryStatement.exceptClauses[0].exceptions[1].str);
        assertEquals(1, tryStatement.exceptClauses[1].exceptions.length);
        assertEquals("SocketTimeout", tryStatement.exceptClauses[1].exceptions[0].str);
        assertEquals("sequence", tryStatement.exceptClauses[1].content.astType);
        assertEquals("sequence", tryStatement.finallyClause.content.astType);
        assertEquals("pass", ((Sequence) tryStatement.finallyClause.content).elements[0].astType);
    }
}
