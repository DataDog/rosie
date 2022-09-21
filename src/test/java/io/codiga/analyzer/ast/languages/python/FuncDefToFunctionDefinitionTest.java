package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.common.FunctionDefinition;
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

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.FuncdefContext.class);
        PythonParser.FuncdefContext funcdefContext = (PythonParser.FuncdefContext) exprNodes.get(0);
        FunctionDefinition functionDefinition = transformFuncDefToFunctionDefinition(funcdefContext, null).get();
        assertEquals("bla", functionDefinition.name.str);
        assertFalse(functionDefinition.isAsync);
        assertEquals("i", functionDefinition.parameters.values[0].name.str);
        assertEquals("int", functionDefinition.parameters.values[0].type.str);
        assertNull(functionDefinition.parameters.values[0].defaultValue);

        assertEquals("a", functionDefinition.parameters.values[1].name.str);
        assertEquals("typing.List[str]", functionDefinition.parameters.values[1].type.str);
        assertEquals("[]", functionDefinition.parameters.values[1].defaultValue.value);

        assertEquals("b", functionDefinition.parameters.values[2].name.value);
        assertNull(functionDefinition.parameters.values[2].type);
        assertNull(functionDefinition.parameters.values[2].defaultValue);
    }

    @Test
    @DisplayName("Correctly map an async function definition with different parameters")
    public void testTransformAsyncFunctionDefinition() {
        String code = "async def bar(one): pass";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.FuncdefContext.class);
        PythonParser.FuncdefContext funcdefContext = (PythonParser.FuncdefContext) exprNodes.get(0);
        FunctionDefinition functionDefinition = transformFuncDefToFunctionDefinition(funcdefContext, null).get();

        assertEquals("bar", functionDefinition.name.str);
        assertTrue(functionDefinition.isAsync);
        assertEquals(1, functionDefinition.start.line);
        assertEquals(1, functionDefinition.start.col);

        assertEquals("one", functionDefinition.parameters.values[0].name.str);
        assertNull(functionDefinition.parameters.values[0].type);
        assertNull(functionDefinition.parameters.values[0].defaultValue);

    }
}
