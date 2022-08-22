
package io.codiga.ast;

import io.codiga.model.ast.FunctionCall;
import io.codiga.parser.python.CodigaVisitor;
import io.codiga.parser.python.gen.PythonLexer;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileInputStream;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.ast.AstUtils.isFunctionCall;
import static io.codiga.ast.ExprToFunctionCall.transformExprToFunctionCall;
import static org.junit.jupiter.api.Assertions.*;

public class ExprToFunctionCallTest extends TestUtils{

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

        System.out.println("expr nodes");
        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.ExprContext.class);

        for (ParseTree node: exprNodes) {
            System.out.println(String.format("%s - %s", node.getText(), node.getClass()));
            if(isFunctionCall(node)) {
                System.out.println("is function call");
                Optional<FunctionCall> functionCallOptional = transformExprToFunctionCall((PythonParser.ExprContext) node);
                System.out.println(functionCallOptional);
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

        System.out.println("expr nodes");
        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.ExprContext.class);

        for (ParseTree node: exprNodes) {
            System.out.println(String.format("%s - %s", node.getText(), node.getClass()));
            if(isFunctionCall(node)) {
                Optional<FunctionCall> functionCallOptional = transformExprToFunctionCall((PythonParser.ExprContext) node);
                System.out.println(functionCallOptional);
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
