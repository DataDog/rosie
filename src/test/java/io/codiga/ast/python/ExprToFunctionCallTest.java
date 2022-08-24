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

import static io.codiga.ast.AstUtils.isFunctionCall;
import static io.codiga.ast.python.ExprToFunctionCall.transformExprToFunctionCall;
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
                Optional<FunctionCall> functionCallOptional = transformExprToFunctionCall((PythonParser.ExprContext) node);
                assertTrue(functionCallOptional.isPresent());
                FunctionCall functionCall = functionCallOptional.get();
                assertEquals(functionCall.functionName(), "get");
                assertFalse(functionCall.moduleOrObject().isEmpty());
                assertEquals(functionCall.moduleOrObject().get(), "requests");
                assertEquals(functionCall.arguments().get(0).value(), "w");
                assertFalse(functionCall.arguments().get(0).name().isPresent());
                assertEquals(functionCall.arguments().get(1).name().get(), "verify");
                assertEquals(functionCall.arguments().get(1).value(), "False");
                assertEquals(functionCall.arguments().get(2).name().get(), "timeout");
                assertEquals(functionCall.arguments().get(2).value(), "10");
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
                Optional<FunctionCall> functionCallOptional = transformExprToFunctionCall((PythonParser.ExprContext) node);
                assertTrue(functionCallOptional.isPresent());
                FunctionCall functionCall = functionCallOptional.get();
                assertEquals(functionCall.functionName(), "get");
                assertTrue(functionCall.moduleOrObject().isEmpty());
                assertEquals(functionCall.arguments().get(0).value(), "w");
                assertFalse(functionCall.arguments().get(0).name().isPresent());
                assertEquals(functionCall.arguments().get(1).name().get(), "verify");
                assertEquals(functionCall.arguments().get(1).value(), "False");
                assertEquals(functionCall.arguments().get(2).name().get(), "timeout");
                assertEquals(functionCall.arguments().get(2).value(), "10");
            }

        }
    }

}
