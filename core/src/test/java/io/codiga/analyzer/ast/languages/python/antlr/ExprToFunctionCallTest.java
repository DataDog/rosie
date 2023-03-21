package io.codiga.analyzer.ast.languages.python.antlr;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.python.PythonFunctionCall;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.python.PythonAstUtils.isFunctionCall;
import static io.codiga.parser.antlr.python.transformations.ExprToFunctionCall.transformExprToFunctionCall;
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
    @DisplayName("Correctly transform a function call with a long trailing parent")
    public void testTransformFunctionCallLongTrailing() {
        String code = """
            connection = mysql.connector.connect(
              host=host,
              user=user,
              passwd="password",
              database=database,
              charset='utf8mb4',
              use_pure=True,
              connection_timeout=5)
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.ExprContext.class);
        for (ParseTree node : exprNodes) {
            if (isFunctionCall(node)) {
                Optional<PythonFunctionCall> functionCallOptional = transformExprToFunctionCall((PythonParser.ExprContext) node, null);
                assertTrue(functionCallOptional.isPresent());
                PythonFunctionCall functionCall = functionCallOptional.get();
                assertEquals(((AstString) functionCall.functionName).value, "connect");
                assertNotNull(functionCall.moduleOrObject);
                assertEquals(functionCall.moduleOrObject.value, "mysql.connector");
                assertEquals("host", ((AstString) functionCall.arguments.values[0].value).value);
                assertEquals("host", functionCall.arguments.values[0].name.value);
                assertEquals("user", ((AstString) functionCall.arguments.values[1].value).value);
                assertEquals("user", functionCall.arguments.values[1].name.value);
                assertEquals("\"password\"", ((AstString) functionCall.arguments.values[2].value).value);
                assertEquals("passwd", functionCall.arguments.values[2].name.value);
                assertEquals("database", ((AstString) functionCall.arguments.values[3].value).value);
                assertEquals("database", functionCall.arguments.values[3].name.value);
                assertEquals("'utf8mb4'", ((AstString) functionCall.arguments.values[4].value).value);
                assertEquals("charset", functionCall.arguments.values[4].name.value);
                assertEquals("True", ((AstString) functionCall.arguments.values[5].value).value);
                assertEquals("use_pure", functionCall.arguments.values[5].name.value);
                assertEquals("5", ((AstString) functionCall.arguments.values[6].value).value);
                assertEquals("connection_timeout", functionCall.arguments.values[6].name.value);
            }
        }
    }


    @Test
    @DisplayName("Correctly transform a function for request.get()")
    public void testTransformFunctionWithDotNotation() {
        String code = "r = requests.get(w, verify=False, timeout=10)";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.ExprContext.class);

        for (ParseTree node : exprNodes) {
            if (isFunctionCall(node)) {
                Optional<PythonFunctionCall> functionCallOptional = transformExprToFunctionCall((PythonParser.ExprContext) node, null);
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
    }

    @Test
    @DisplayName("Correctly transform a function for get()")
    public void testTransformWithoutDotNotation() {
        String code = "r = get(w, verify=False, timeout=10)";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.ExprContext.class);

        for (ParseTree node : exprNodes) {
            if (isFunctionCall(node)) {
                Optional<PythonFunctionCall> functionCallOptional = transformExprToFunctionCall((PythonParser.ExprContext) node, null);
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

        }
    }

}
