package io.codiga.analyzer.ast.languages.python.treesitter;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.Sequence;
import io.codiga.model.ast.python.PythonFunctionDefinition;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.DecoratedDefinitionTransformation.transformDecoratedDefinition;
import static io.codiga.parser.treesitter.python.transformation.FunctionDefinitionTransformation.transformFunctionDefinition;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionDefinitionTest extends PythonTestUtils {

    private final Logger log = Logger.getLogger("Test");

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

        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.FUNCTION_DEFINITION.label);
        assertEquals(1, nodes.size());
        Optional<PythonFunctionDefinition> functionDefinitionOptional = transformFunctionDefinition(nodes.get(0), parsingContext);
        assertTrue(functionDefinitionOptional.isPresent());
        var pythonFunctionDefinition = functionDefinitionOptional.get();
        assertEquals("bla", pythonFunctionDefinition.name.str);
        assertFalse(pythonFunctionDefinition.isAsync);
        assertEquals("i", pythonFunctionDefinition.parameters.values[0].name.str);
        assertEquals("int", ((AstString) pythonFunctionDefinition.parameters.values[0].type).str);
        assertNull(pythonFunctionDefinition.parameters.values[0].defaultValue);

        assertEquals("a", pythonFunctionDefinition.parameters.values[1].name.str);
        assertEquals("typing.List[str]", ((AstString) pythonFunctionDefinition.parameters.values[1].type).str);
        assertEquals("[]", pythonFunctionDefinition.parameters.values[1].defaultValue.value);

        assertEquals("b", pythonFunctionDefinition.parameters.values[2].name.value);
        assertNull(pythonFunctionDefinition.parameters.values[2].type);
        assertNull(pythonFunctionDefinition.parameters.values[2].defaultValue);
    }

    @Test
    @DisplayName("Correctly map an async function definition with different parameters")
    public void testTransformAsyncFunctionDefinition() {
        String code = "async def bar(one): pass";
        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.FUNCTION_DEFINITION.label);
        assertEquals(1, nodes.size());
        Optional<PythonFunctionDefinition> functionDefinitionOptional = transformFunctionDefinition(nodes.get(0), parsingContext);
        assertTrue(functionDefinitionOptional.isPresent());
        var pythonFunctionDefinition = functionDefinitionOptional.get();

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

        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.DECORATED_DEFINITION.label);
        assertEquals(1, nodes.size());
        Optional<AstElement> decoratedDefinitionOptional = transformDecoratedDefinition(nodes.get(0), parsingContext);
        assertTrue(decoratedDefinitionOptional.isPresent());
        AstElement astElement = decoratedDefinitionOptional.get();
        assertTrue(astElement instanceof PythonFunctionDefinition);
        PythonFunctionDefinition pythonFunctionDefinition = (PythonFunctionDefinition) astElement;


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

        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.DECORATED_DEFINITION.label);
        assertEquals(1, nodes.size());
        Optional<AstElement> decoratedDefinitionOptional = transformDecoratedDefinition(nodes.get(0), parsingContext);
        assertTrue(decoratedDefinitionOptional.isPresent());
        AstElement astElement = decoratedDefinitionOptional.get();
        assertTrue(astElement instanceof PythonFunctionDefinition);
        PythonFunctionDefinition pythonFunctionDefinition = (PythonFunctionDefinition) astElement;

        assertEquals(1, pythonFunctionDefinition.decorators.length);
        assertEquals(1, pythonFunctionDefinition.decorators[0].arguments.length);
        assertEquals("foo", pythonFunctionDefinition.decorators[0].arguments[0].name.str);
        assertEquals("bar", pythonFunctionDefinition.decorators[0].arguments[0].value.str);
        // TODO FIXME
//        assertEquals("plop.bla.plip", pythonFunctionDefinition.decorators[0].name.str);
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

        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.DECORATED_DEFINITION.label);
        assertEquals(1, nodes.size());
        Optional<AstElement> decoratedDefinitionOptional = transformDecoratedDefinition(nodes.get(0), parsingContext);
        assertTrue(decoratedDefinitionOptional.isPresent());
        AstElement astElement = decoratedDefinitionOptional.get();
        assertTrue(astElement instanceof PythonFunctionDefinition);
        PythonFunctionDefinition pythonFunctionDefinition = (PythonFunctionDefinition) astElement;


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

    @Test
    @DisplayName("Correctly map a function definition with a decorator with arguments")
    public void testTransformContentOfFunction() {
        String code = """
            from flask import Flask
            import json

            app = Flask(__name__)


            @app.route('/')
            def hello():
                json.dumps(bla)
                foo = 42
                return 'Hello, World!'
                        """;

        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.DECORATED_DEFINITION.label);
        assertEquals(1, nodes.size());
        Optional<AstElement> decoratedDefinitionOptional = transformDecoratedDefinition(nodes.get(0), parsingContext);
        assertTrue(decoratedDefinitionOptional.isPresent());
        AstElement astElement = decoratedDefinitionOptional.get();
        assertTrue(astElement instanceof PythonFunctionDefinition);
        PythonFunctionDefinition functionDefinition = (PythonFunctionDefinition) astElement;

        assertEquals("hello", functionDefinition.name.str);
        assertEquals(AstElementTypes.SEQUENCE.label, functionDefinition.content.astType);
        assertEquals(3, ((Sequence) functionDefinition.content).elements.length);
        assertEquals(AstElementTypes.FUNCTION_CALL.label, ((Sequence) functionDefinition.content).elements[0].astType);
        assertEquals(AstElementTypes.ASSIGNMENT.label, ((Sequence) functionDefinition.content).elements[1].astType);
        assertEquals(AstElementTypes.RETURN.label, ((Sequence) functionDefinition.content).elements[2].astType);

    }
}
