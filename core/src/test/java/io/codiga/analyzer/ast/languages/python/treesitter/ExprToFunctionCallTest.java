package io.codiga.analyzer.ast.languages.python.treesitter;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.python.PythonFunctionCall;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.ExprToFunctionCall.transformExprToFunctionCall;
import static org.junit.jupiter.api.Assertions.*;

public class ExprToFunctionCallTest extends io.codiga.analyzer.ast.languages.python.treesitter.PythonTestUtils {

    private static Logger LOGGER = Logger.getLogger(ExprToFunctionCallTest.class.getName());

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }


    @Test
    @DisplayName("Correctly transform a function for get()")
    public void testTransformWithoutDotNotation() {
        String code = "r = get(w, verify=False, timeout=10)";

        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);
        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, "call");
        assertEquals(1, nodes.size());

        Node node = nodes.get(0);

        Optional<PythonFunctionCall> functionCallOptional = transformExprToFunctionCall(node, parsingContext);
        assertTrue(functionCallOptional.isPresent());
        PythonFunctionCall functionCall = functionCallOptional.get();
        assertEquals(((AstString) functionCall.functionName).value, "get");
        assertNull(functionCall.moduleOrObject);
        assertEquals(((AstString) functionCall.arguments.values[0].value).value, "w");
        assertNull(functionCall.arguments.values[0].name);
        assertEquals(functionCall.arguments.values[1].name.value, "verify");
        assertEquals(((AstString) functionCall.arguments.values[1].value).value, "False");
        assertEquals(functionCall.arguments.values[2].name.value, "timeout");
        assertEquals(((AstString) functionCall.arguments.values[2].value).value, "10");
    }


    @Test
    @DisplayName("Correctly transform a function for request.get()")
    public void testTransformFunctionWithDotNotation() {
        String code = "r = requests.get(w, verify=False, timeout=10)";

        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, "call");
        assertEquals(1, nodes.size());

        Node node = nodes.get(0);
        Optional<PythonFunctionCall> functionCallOptional = transformExprToFunctionCall(node, parsingContext);
        assertTrue(functionCallOptional.isPresent());
        PythonFunctionCall functionCall = functionCallOptional.get();
        assertEquals(((AstString) functionCall.functionName).value, "get");
        assertNotNull(functionCall.moduleOrObject);
        assertEquals(((AstString) functionCall.moduleOrObject).value, "requests");
        assertEquals(((AstString) functionCall.arguments.values[0].value).value, "w");
        assertNull(functionCall.arguments.values[0].name);
        assertEquals(functionCall.arguments.values[1].name.value, "verify");
        assertEquals(((AstString) functionCall.arguments.values[1].value).value, "False");
        assertEquals(functionCall.arguments.values[2].name.value, "timeout");
        assertEquals(((AstString) functionCall.arguments.values[2].value).value, "10");

    }

}
