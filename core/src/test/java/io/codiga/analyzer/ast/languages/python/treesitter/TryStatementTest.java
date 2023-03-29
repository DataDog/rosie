package io.codiga.analyzer.ast.languages.python.treesitter;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.Sequence;
import io.codiga.model.ast.python.TryStatement;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.model.ast.common.AstElement.AST_ELEMENT_TYPE_CONTINUE;
import static io.codiga.parser.treesitter.python.transformation.TryStatementTransformation.transformTryStatement;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeChildren;
import static org.junit.jupiter.api.Assertions.*;

public class TryStatementTest extends PythonTestUtils {

    private final Logger log = Logger.getLogger("Test");

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

        Node rootNode = parseCode(code);
//        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);
        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.TRY_STATEMENT.label);
        assertEquals(1, nodes.size());

        Optional<TryStatement> tryStatementOptional = transformTryStatement(nodes.get(0), parsingContext);

        assertTrue(tryStatementOptional.isPresent());
        TryStatement tryStatement = tryStatementOptional.get();

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

        Node rootNode = parseCode(code);
//        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);
        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.TRY_STATEMENT.label);
        assertEquals(1, nodes.size());

        Optional<TryStatement> tryStatementOptional = transformTryStatement(nodes.get(0), parsingContext);

        assertTrue(tryStatementOptional.isPresent());
        TryStatement tryStatement = tryStatementOptional.get();
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

        Node rootNode = parseCode(code);
//        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);
        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = getNodeChildren(rootNode, TreeSitterPythonTypes.TRY_STATEMENT);
        assertEquals(1, nodes.size());

        Optional<TryStatement> tryStatementOptional = transformTryStatement(nodes.get(0), parsingContext);

        assertTrue(tryStatementOptional.isPresent());
        TryStatement tryStatement = tryStatementOptional.get();
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
                break
                        """;

        Node rootNode = parseCode(code);
//        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);
        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.TRY_STATEMENT.label);
        assertEquals(1, nodes.size());

        Optional<TryStatement> tryStatementOptional = transformTryStatement(nodes.get(0), parsingContext);

        assertTrue(tryStatementOptional.isPresent());
        TryStatement tryStatement = tryStatementOptional.get();
        assertNotNull(tryStatement.exceptClauses);
        assertNull(tryStatement.exceptClauses[0].as);
        assertEquals(2, tryStatement.exceptClauses[0].exceptions.length);
        assertEquals("URLError", tryStatement.exceptClauses[0].exceptions[0].str);
        assertEquals("ValueError", tryStatement.exceptClauses[0].exceptions[1].str);
        assertEquals(1, tryStatement.exceptClauses[1].exceptions.length);
        assertEquals("SocketTimeout", tryStatement.exceptClauses[1].exceptions[0].str);
        assertEquals("sequence", tryStatement.exceptClauses[1].content.astType);
        assertEquals("sequence", tryStatement.finallyClause.content.astType);
        assertEquals("break", ((Sequence) tryStatement.finallyClause.content).elements[0].astType);
    }


    @Test
    @DisplayName("Transform a simple exception with an alias")
    public void testSimpleGeneralExceptionWithAlias() {
        String code = """
            a = 2
            b = 0
            try:
                c = a /b
            except Exception as e:
                pass""";

        Node rootNode = parseCode(code);
//        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);
        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.TRY_STATEMENT.label);
        assertEquals(1, nodes.size());

        Optional<TryStatement> tryStatementOptional = transformTryStatement(nodes.get(0), parsingContext);

        assertTrue(tryStatementOptional.isPresent());
        TryStatement tryStatement = tryStatementOptional.get();
        assertNotNull(tryStatement.exceptClauses);
        assertNotNull(tryStatement.exceptClauses[0].as);
        assertEquals("e", tryStatement.exceptClauses[0].as.value);
        assertEquals(1, tryStatement.exceptClauses[0].exceptions.length);
        assertEquals("Exception", tryStatement.exceptClauses[0].exceptions[0].value);
    }

    @Test
    @DisplayName("Transform a simple exception without an alias")
    public void testSimpleGeneralExceptionWithoutAlias() {
        String code = """
            a = 2
            b = 0
            try:
                c = a /b
            except Exception:
                continue""";

        Node rootNode = parseCode(code);
//        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);
        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.TRY_STATEMENT.label);
        assertEquals(1, nodes.size());

        Optional<TryStatement> tryStatementOptional = transformTryStatement(nodes.get(0), parsingContext);

        assertTrue(tryStatementOptional.isPresent());
        TryStatement tryStatement = tryStatementOptional.get();
        assertNotNull(tryStatement.exceptClauses);
        assertNull(tryStatement.exceptClauses[0].as);
        assertEquals(AST_ELEMENT_TYPE_CONTINUE, ((Sequence) tryStatement.exceptClauses[0].content).elements[0].astType);
        assertEquals(1, tryStatement.exceptClauses[0].exceptions.length);
        assertEquals("Exception", tryStatement.exceptClauses[0].exceptions[0].value);
    }
}
