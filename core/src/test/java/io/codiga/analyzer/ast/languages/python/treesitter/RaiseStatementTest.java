package io.codiga.analyzer.ast.languages.python.treesitter;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.python.PythonFunctionCall;
import io.codiga.model.ast.python.RaiseStatement;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.model.ast.common.AstElement.AST_ELEMENT_TYPE_FUNCTION_CALL;
import static io.codiga.parser.treesitter.python.transformation.RaiseStatementTransformation.transformRaiseStatement;
import static org.junit.jupiter.api.Assertions.*;

public class RaiseStatementTest extends PythonTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("correctly map a raise statement")
    public void testRaise() {
        String code = """
            raise MyException()
            raise MyException(arg1, arg2)
            raise Exception('spam', 'eggs')
            raise cls()
            raise
            raise ValueError
            raise RuntimeError('Failed to open database') from exc
            """;

        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);
        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.RAISE_STATEMENT.label);
        assertEquals(7, nodes.size());

        Optional<RaiseStatement> raiseStatementOptional = Optional.empty();
        // first statement
        raiseStatementOptional = transformRaiseStatement(nodes.get(0), parsingContext);
        assertTrue(raiseStatementOptional.isPresent());
        assertEquals(AST_ELEMENT_TYPE_FUNCTION_CALL, raiseStatementOptional.get().exception.astType);
        assertEquals("MyException", ((AstString) ((PythonFunctionCall) raiseStatementOptional.get().exception).functionName).value);
        assertEquals(0, ((PythonFunctionCall) raiseStatementOptional.get().exception).arguments.values.length);
        assertNull(raiseStatementOptional.get().as);
        assertNull(raiseStatementOptional.get().as);

        // second statement
        raiseStatementOptional = transformRaiseStatement(nodes.get(1), parsingContext);
        assertTrue(raiseStatementOptional.isPresent());
        assertEquals(AST_ELEMENT_TYPE_FUNCTION_CALL, raiseStatementOptional.get().exception.astType);
        assertEquals("MyException", ((AstString) ((PythonFunctionCall) raiseStatementOptional.get().exception).functionName).value);
        assertEquals(2, ((PythonFunctionCall) raiseStatementOptional.get().exception).arguments.values.length);
        assertNull(raiseStatementOptional.get().as);

        // fifth statement
        raiseStatementOptional = transformRaiseStatement(nodes.get(4), parsingContext);
        assertTrue(raiseStatementOptional.isPresent());
        assertNull(raiseStatementOptional.get().exception);
        assertNull(raiseStatementOptional.get().as);

        // seventh statement
        raiseStatementOptional = transformRaiseStatement(nodes.get(6), parsingContext);
        assertNotNull(raiseStatementOptional.get().as);
        assertEquals("exc", ((AstString) raiseStatementOptional.get().as).value);

    }

}
