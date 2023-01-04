package io.codiga.analyzer.ast.languages.typescript;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionCall;
import io.codiga.model.ast.javascript.AstStringWithSpreadOperator;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptFunctionCallTransformation.isFunctionCall;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptFunctionCallTransformation.transformArgumentsExpressionToFunctionCall;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIdentifierExpressionTransformation.isIdentifierExpressionFunctionCall;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIdentifierExpressionTransformation.transformIdentifierExpressionToFunctionCall;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionCallTest extends TypeScriptTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }


    @Test
    @DisplayName("Get a function call via expression")
    public void testFunctionCallExpression() {
        String code = """
            var bla = 1;
            var foo = baz(baz);
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.IdentifierExpressionContext.class);
        assertEquals(2, nodes.size());
        for (ParseTree node : nodes) {
            if (isIdentifierExpressionFunctionCall((TypeScriptParser.IdentifierExpressionContext) node)) {
                Optional<FunctionCall> functionCallOptional = transformIdentifierExpressionToFunctionCall((TypeScriptParser.IdentifierExpressionContext) node, null);
                assertTrue(functionCallOptional.isPresent());
                FunctionCall functionCall = functionCallOptional.get();
                assertEquals(((AstString) functionCall.functionName).value, "baz");
                assertEquals(1, functionCall.arguments.values.length);
                assertEquals(((AstString) functionCall.arguments.values[0].value).value, "baz");

            }
        }
    }


    @Test
    @DisplayName("function call with spread")
    public void testFunctionCallExpressionWithSpread() {
        String code = """
            myFunc(1, "bar", ...myVariable)
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.ArgumentsExpressionContext.class);
        assertEquals(1, nodes.size());
        for (ParseTree node : nodes) {
            if (isFunctionCall(node)) {
                Optional<FunctionCall> functionCallOptional = transformArgumentsExpressionToFunctionCall((TypeScriptParser.ArgumentsExpressionContext) node, null);
                assertTrue(functionCallOptional.isPresent());
                FunctionCall functionCall = functionCallOptional.get();
                assertEquals(((AstString) functionCall.functionName).value, "myFunc");
                assertEquals(((AstString) functionCall.arguments.values[0].value).value, "1");
                assertFalse(((AstStringWithSpreadOperator) functionCall.arguments.values[0].value).isSpread);
                assertEquals(((AstString) functionCall.arguments.values[1].value).value, "\"bar\"");
                assertFalse(((AstStringWithSpreadOperator) functionCall.arguments.values[1].value).isSpread);
                assertEquals(((AstStringWithSpreadOperator) functionCall.arguments.values[2].value).value, "myVariable");
                assertTrue(((AstStringWithSpreadOperator) functionCall.arguments.values[2].value).isSpread);
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

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.ArgumentsExpressionContext.class);
        // FIXME - julien
//        assertEquals(1, nodes.size());
//        for (ParseTree node : nodes) {
//            if (isFunctionCall((TypeScriptParser.ArgumentsExpressionContext) node)) {
//                Optional<FunctionCall> functionCallOptional = transformArgumentsExpressionToFunctionCall((TypeScriptParser.ArgumentsExpressionContext) node, null);
//                assertTrue(functionCallOptional.isPresent());
//                FunctionCall functionCall = functionCallOptional.get();
//                assertEquals(((AstString) functionCall.functionName).value, "myFunc");
//                assertEquals(1, functionCall.arguments.values.length);
//            }
//        }
    }

    @Test
    @DisplayName("correctly get nested objects as function arguments")
    public void testArgumentsNestedObjects() {
        String code = """
            myFunc({foo: 42, baz: {nested: "baz" }})
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.ArgumentsExpressionContext.class);
        // FIXME - julien
//        assertEquals(1, nodes.size());
//        for (ParseTree node : nodes) {
//            if (isFunctionCall((TypeScriptParser.ArgumentsExpressionContext) node)) {
//                Optional<FunctionCall> functionCallOptional = JavaScriptFunctionCallTransformation.transformArgumentsExpressionToFunctionCall((JavaScriptParser.ArgumentsExpressionContext) node, null);
//                assertTrue(functionCallOptional.isPresent());
//                FunctionCall functionCall = functionCallOptional.get();
//                assertEquals(((AstString) functionCall.functionName).value, "myFunc");
//                assertEquals(1, functionCall.arguments.values.length);
//                AstElement firstArgument = functionCall.arguments.values[0].value;
//                JavaScriptObject jsObject = (JavaScriptObject) firstArgument;
//
//                assertEquals(((AstString) jsObject.elements[0].name).value, "foo");
//                assertEquals(((AstString) jsObject.elements[0].value).value, "42");
//
//                assertEquals(((AstString) jsObject.elements[1].name).value, "baz");
//                JavaScriptObject nestedObject = (JavaScriptObject) jsObject.elements[1].value;
//                assertEquals(((AstString) nestedObject.elements[0].name).value, "nested");
//                assertEquals(((AstString) nestedObject.elements[0].value).value, "\"baz\"");
//            }
//        }
    }
}
