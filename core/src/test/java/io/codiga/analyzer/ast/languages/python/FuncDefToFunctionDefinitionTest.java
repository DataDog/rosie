package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.python.PythonFunctionDefinition;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.python.FuncDefToFunctionDefinition.transformFuncDefToFunctionDefinition;
import static org.junit.jupiter.api.Assertions.*;

public class FuncDefToFunctionDefinitionTest extends PythonTestUtils {

    private Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Correctly map a function definition with different parameters")
    public void testTransformFunctionDefinition() {
        String code = """
            def bla(i: int, a: typing.List[str] = [], b):
                pass""";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Class_or_func_def_stmtContext.class);
        PythonParser.Class_or_func_def_stmtContext funcdefContext = (PythonParser.Class_or_func_def_stmtContext) exprNodes.get(0);
        PythonFunctionDefinition pythonFunctionDefinition = transformFuncDefToFunctionDefinition(funcdefContext, null).get();
        assertEquals("bla", pythonFunctionDefinition.name.str);
        assertFalse(pythonFunctionDefinition.isAsync);
        assertEquals("i", pythonFunctionDefinition.parameters.values[0].name.str);
        assertEquals("int", pythonFunctionDefinition.parameters.values[0].type.str);
        assertNull(pythonFunctionDefinition.parameters.values[0].defaultValue);

        assertEquals("a", pythonFunctionDefinition.parameters.values[1].name.str);
        assertEquals("typing.List[str]", pythonFunctionDefinition.parameters.values[1].type.str);
        assertEquals("[]", pythonFunctionDefinition.parameters.values[1].defaultValue.value);

        assertEquals("b", pythonFunctionDefinition.parameters.values[2].name.value);
        assertNull(pythonFunctionDefinition.parameters.values[2].type);
        assertNull(pythonFunctionDefinition.parameters.values[2].defaultValue);
    }

    @Test
    @DisplayName("Correctly map an async function definition with different parameters")
    public void testTransformAsyncFunctionDefinition() {
        String code = "async def bar(one): pass";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Class_or_func_def_stmtContext.class);
        PythonParser.Class_or_func_def_stmtContext funcdefContext = (PythonParser.Class_or_func_def_stmtContext) exprNodes.get(0);
        PythonFunctionDefinition pythonFunctionDefinition = transformFuncDefToFunctionDefinition(funcdefContext, null).get();

        assertEquals("bar", pythonFunctionDefinition.name.str);
        assertTrue(pythonFunctionDefinition.isAsync);
        assertEquals(1, pythonFunctionDefinition.start.line);
        assertEquals(1, pythonFunctionDefinition.start.col);

        assertEquals("one", pythonFunctionDefinition.parameters.values[0].name.str);
        assertNull(pythonFunctionDefinition.parameters.values[0].type);
        assertNull(pythonFunctionDefinition.parameters.values[0].defaultValue);

    }

    @Test
    @DisplayName("Correctly map a function definition with a decorator")
    public void testTransformFunctionWithDecorator() {
        String code = """
            @plop.bla.plip
            def bar(one): pass
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Class_or_func_def_stmtContext.class);
        PythonParser.Class_or_func_def_stmtContext funcdefContext = (PythonParser.Class_or_func_def_stmtContext) exprNodes.get(0);
        PythonFunctionDefinition pythonFunctionDefinition = transformFuncDefToFunctionDefinition(funcdefContext, null).get();

        assertEquals(1, pythonFunctionDefinition.decorators.length);
        assertEquals(0, pythonFunctionDefinition.decorators[0].arguments.length);
        assertEquals("plop.bla.plip", pythonFunctionDefinition.decorators[0].name.str);
        assertEquals("bar", pythonFunctionDefinition.name.str);
        assertEquals("bar", pythonFunctionDefinition.name.str);
        assertFalse(pythonFunctionDefinition.isAsync);
        assertEquals(1, pythonFunctionDefinition.start.line);
        assertEquals(1, pythonFunctionDefinition.start.col);

        assertEquals("one", pythonFunctionDefinition.parameters.values[0].name.str);
        assertNull(pythonFunctionDefinition.parameters.values[0].type);
        assertNull(pythonFunctionDefinition.parameters.values[0].defaultValue);
    }

    @Test
    @DisplayName("Correctly map a function definition with a decorator with arguments")
    public void testTransformFunctionWithDecoratorAndArguments() {
        String code = """
            @plop.bla.plip(foo = bar)
            def bar(one): pass
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Class_or_func_def_stmtContext.class);
        PythonParser.Class_or_func_def_stmtContext funcdefContext = (PythonParser.Class_or_func_def_stmtContext) exprNodes.get(0);
        PythonFunctionDefinition pythonFunctionDefinition = transformFuncDefToFunctionDefinition(funcdefContext, null).get();

        assertEquals(1, pythonFunctionDefinition.decorators.length);
        assertEquals(1, pythonFunctionDefinition.decorators[0].arguments.length);
        assertEquals("foo", pythonFunctionDefinition.decorators[0].arguments[0].name.str);
        assertEquals("bar", pythonFunctionDefinition.decorators[0].arguments[0].value.str);
        assertEquals("plop.bla.plip", pythonFunctionDefinition.decorators[0].name.str);
        assertEquals("bar", pythonFunctionDefinition.name.str);
        assertEquals("bar", pythonFunctionDefinition.name.str);
        assertFalse(pythonFunctionDefinition.isAsync);
        assertEquals(1, pythonFunctionDefinition.start.line);
        assertEquals(1, pythonFunctionDefinition.start.col);

        assertEquals("one", pythonFunctionDefinition.parameters.values[0].name.str);
        assertNull(pythonFunctionDefinition.parameters.values[0].type);
        assertNull(pythonFunctionDefinition.parameters.values[0].defaultValue);
    }

    @Test
    @DisplayName("Correctly map a function definition with a decorator with arguments")
    public void testTransformFunctionWithDecoratorAndArguments2() {
        String code = """
            @plop.bla.plip(foo)
            def bar(one): pass
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Class_or_func_def_stmtContext.class);
        PythonParser.Class_or_func_def_stmtContext funcdefContext = (PythonParser.Class_or_func_def_stmtContext) exprNodes.get(0);
        PythonFunctionDefinition pythonFunctionDefinition = transformFuncDefToFunctionDefinition(funcdefContext, null).get();

        assertEquals(1, pythonFunctionDefinition.decorators.length);
        assertEquals(1, pythonFunctionDefinition.decorators[0].arguments.length);
        assertNull(pythonFunctionDefinition.decorators[0].arguments[0].name);
        assertEquals("foo", pythonFunctionDefinition.decorators[0].arguments[0].value.str);
        assertEquals("plop.bla.plip", pythonFunctionDefinition.decorators[0].name.str);
        assertEquals("bar", pythonFunctionDefinition.name.str);
        assertEquals("bar", pythonFunctionDefinition.name.str);
        assertFalse(pythonFunctionDefinition.isAsync);
        assertEquals(1, pythonFunctionDefinition.start.line);
        assertEquals(1, pythonFunctionDefinition.start.col);

        assertEquals("one", pythonFunctionDefinition.parameters.values[0].name.str);
        assertNull(pythonFunctionDefinition.parameters.values[0].type);
        assertNull(pythonFunctionDefinition.parameters.values[0].defaultValue);
    }
}
