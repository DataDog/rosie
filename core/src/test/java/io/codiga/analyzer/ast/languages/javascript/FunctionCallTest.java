package io.codiga.analyzer.ast.languages.javascript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionCall;
import io.codiga.model.ast.javascript.JavaScriptObject;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptFunctionCallTransformation.isFunctionCall;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptFunctionCallTransformation.transformArgumentsExpressionToFunctionCall;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionCallTest extends JavaScriptTestUtils {

    private Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

//    @Test
//    @DisplayName("get a function call")
//    public void testFunctionCallWithContent() {
//        String code = """
//                myFunc({...props}) {
//                   console.log(props);
//                }
//            """;
//
//        ParseTree root = parseCode(code);
//
//        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.ExprContext.class);
//        for (ParseTree node : exprNodes) {
//            if (isFunctionCall(node)) {
//                Optional<PythonFunctionCall> functionCallOptional = transformExprToFunctionCall((PythonParser.ExprContext) node, null);
//                assertTrue(functionCallOptional.isPresent());
//                PythonFunctionCall functionCall = functionCallOptional.get();
//                assertEquals(functionCall.functionName.value, "connect");
//                assertNotNull(functionCall.moduleOrObject);
//                assertEquals(functionCall.moduleOrObject.value, "mysql.connector");
//                assertEquals("host", functionCall.arguments.values[0].value.value);
//                assertEquals("host", functionCall.arguments.values[0].name.value);
//                assertEquals("user", functionCall.arguments.values[1].value.value);
//                assertEquals("user", functionCall.arguments.values[1].name.value);
//                assertEquals("\"password\"", functionCall.arguments.values[2].value.value);
//                assertEquals("passwd", functionCall.arguments.values[2].name.value);
//                assertEquals("database", functionCall.arguments.values[3].value.value);
//                assertEquals("database", functionCall.arguments.values[3].name.value);
//                assertEquals("'utf8mb4'", functionCall.arguments.values[4].value.value);
//                assertEquals("charset", functionCall.arguments.values[4].name.value);
//                assertEquals("True", functionCall.arguments.values[5].value.value);
//                assertEquals("use_pure", functionCall.arguments.values[5].name.value);
//                assertEquals("5", functionCall.arguments.values[6].value.value);
//                assertEquals("connection_timeout", functionCall.arguments.values[6].name.value);
//            }
//        }
//    }

    @Test
    @DisplayName("Get a value as argument")
    public void testFunctionCallValue() {
        String code = """
            myFunc(1, "bar", myVariable)
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.ArgumentsExpressionContext.class);

        for (ParseTree node : nodes) {
            if (isFunctionCall(node)) {
                Optional<FunctionCall> functionCallOptional = transformArgumentsExpressionToFunctionCall((JavaScriptParser.ArgumentsExpressionContext) node, null);
                assertTrue(functionCallOptional.isPresent());
                FunctionCall functionCall = functionCallOptional.get();
                assertEquals(((AstString) functionCall.functionName).value, "myFunc");
                assertEquals(((AstString) functionCall.arguments.values[0].value).value, "1");
                assertEquals(((AstString) functionCall.arguments.values[1].value).value, "\"bar\"");
                assertEquals(((AstString) functionCall.arguments.values[2].value).value, "myVariable");
            }
        }
    }


    @Test
    @DisplayName("Get an object as argument")
    public void testSimpleObject() {
        String code = """
            myFunc({arg1: "foo", arg2: "baz"})
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.ArgumentsExpressionContext.class);

        for (ParseTree node : nodes) {
            if (isFunctionCall(node)) {
                Optional<FunctionCall> functionCallOptional = transformArgumentsExpressionToFunctionCall((JavaScriptParser.ArgumentsExpressionContext) node, null);
                assertTrue(functionCallOptional.isPresent());
                FunctionCall functionCall = functionCallOptional.get();
                assertEquals(((AstString) functionCall.functionName).value, "myFunc");
            }
        }
    }

    @Test
    @DisplayName("correctly get nested objects as function arguments")
    public void testArgumentsNestedObjects() {
        String code = """
            myFunc({foo: 42, baz: {nested: "baz" }})
                        
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.ArgumentsExpressionContext.class);

        for (ParseTree node : nodes) {
            if (isFunctionCall(node)) {
                Optional<FunctionCall> functionCallOptional = transformArgumentsExpressionToFunctionCall((JavaScriptParser.ArgumentsExpressionContext) node, null);
                assertTrue(functionCallOptional.isPresent());
                FunctionCall functionCall = functionCallOptional.get();
                assertEquals(((AstString) functionCall.functionName).value, "myFunc");
                assertEquals(1, functionCall.arguments.values.length);
                AstElement firstArgument = functionCall.arguments.values[0].value;
                JavaScriptObject jsObject = (JavaScriptObject) firstArgument;

                assertEquals(((AstString) jsObject.elements[0].name).value, "foo");
                assertEquals(((AstString) jsObject.elements[0].value).value, "42");

                assertEquals(((AstString) jsObject.elements[1].name).value, "baz");
                JavaScriptObject nestedObject = (JavaScriptObject) jsObject.elements[1].value;
                assertEquals(((AstString) nestedObject.elements[0].name).value, "nested");
                assertEquals(((AstString) nestedObject.elements[0].value).value, "\"baz\"");
            }
        }
    }

}
