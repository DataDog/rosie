package io.codiga.analyzer.ast.languages.python;

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

import static io.codiga.analyzer.ast.languages.python.ExprToFunctionCall.transformExprToFunctionCall;
import static io.codiga.analyzer.ast.languages.python.PythonAstUtils.isFunctionCall;
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
                assertEquals(functionCall.functionName.value, "get");
                assertNotNull(functionCall.moduleOrObject);
                assertEquals(functionCall.moduleOrObject.value, "requests");
                assertEquals(functionCall.arguments.values[0].value.value, "w");
                assertNull(functionCall.arguments.values[0].name);
                assertEquals(functionCall.arguments.values[1].name.value, "verify");
                assertEquals(functionCall.arguments.values[1].value.value, "False");
                assertEquals(functionCall.arguments.values[2].name.value, "timeout");
                assertEquals(functionCall.arguments.values[2].value.value, "10");
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
                assertEquals(functionCall.functionName.value, "get");
                assertNull(functionCall.moduleOrObject);
                assertEquals(functionCall.arguments.values[0].value.value, "w");
                assertNull(functionCall.arguments.values[0].name);
                assertEquals(functionCall.arguments.values[1].name.value, "verify");
                assertEquals(functionCall.arguments.values[1].value.value, "False");
                assertEquals(functionCall.arguments.values[2].name.value, "timeout");
                assertEquals(functionCall.arguments.values[2].value.value, "10");
            }

        }
    }

}
