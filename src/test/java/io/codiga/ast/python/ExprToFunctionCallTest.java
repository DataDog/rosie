package io.codiga.ast.python;

import io.codiga.model.ast.FunctionCall;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.ast.python.ExprToFunctionCall.transformExprToFunctionCall;
import static io.codiga.ast.python.PythonAstUtils.isFunctionCall;
import static org.junit.jupiter.api.Assertions.*;

public class ExprToFunctionCallTest extends PythonTestUtils {

    private Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Correctly transform a function for request.get()")
    public void testTransformFunctionWithDotNotation() {
        String code = "r = requests.get(w, verify=False, timeout=10)";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.ExprContext.class);

        for (ParseTree node : exprNodes) {
            if (isFunctionCall(node)) {
                Optional<FunctionCall> functionCallOptional = transformExprToFunctionCall((PythonParser.ExprContext) node, null);
                assertTrue(functionCallOptional.isPresent());
                FunctionCall functionCall = functionCallOptional.get();
                assertEquals(functionCall.functionName, "get");
                assertNotNull(functionCall.moduleOrObject);
                assertEquals(functionCall.moduleOrObject, "requests");
                assertEquals(functionCall.arguments[0].value, "w");
                assertNull(functionCall.arguments[0].name);
                assertEquals(functionCall.arguments[1].name, "verify");
                assertEquals(functionCall.arguments[1].value, "False");
                assertEquals(functionCall.arguments[2].name, "timeout");
                assertEquals(functionCall.arguments[2].value, "10");
            }
        }
    }

    @Test
    @DisplayName("Correctly transform a function for get()")
    public void testTransformWithoutDotNotation() {
        String code = "r = get(w, verify=False, timeout=10)";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.ExprContext.class);

        for (ParseTree node : exprNodes) {
            if (isFunctionCall(node)) {
                Optional<FunctionCall> functionCallOptional = transformExprToFunctionCall((PythonParser.ExprContext) node, null);
                assertTrue(functionCallOptional.isPresent());
                FunctionCall functionCall = functionCallOptional.get();
                assertEquals(functionCall.functionName, "get");
                assertNull(functionCall.moduleOrObject);
                assertEquals(functionCall.arguments[0].value, "w");
                assertNull(functionCall.arguments[0].name);
                assertEquals(functionCall.arguments[1].name, "verify");
                assertEquals(functionCall.arguments[1].value, "False");
                assertEquals(functionCall.arguments[2].name, "timeout");
                assertEquals(functionCall.arguments[2].value, "10");
            }

        }
    }

}
