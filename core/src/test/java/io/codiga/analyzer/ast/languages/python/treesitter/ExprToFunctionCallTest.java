package io.codiga.analyzer.ast.languages.python.treesitter;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.python.PythonDictionary;
import io.codiga.model.ast.python.PythonFunctionCall;
import io.codiga.model.ast.python.PythonList;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.model.ast.common.AstElement.AST_ELEMENT_TYPE_FUNCTION_CALL;
import static io.codiga.parser.treesitter.python.transformation.CallTransformation.transformCall;
import static org.junit.jupiter.api.Assertions.*;

public class ExprToFunctionCallTest extends io.codiga.analyzer.ast.languages.python.treesitter.PythonTestUtils {

    private static final Logger LOGGER = Logger.getLogger(ExprToFunctionCallTest.class.getName());

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

        Node rootNode = parseCode(code);
        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);


        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, "call");
        assertEquals(1, nodes.size());

        Node node = nodes.get(0);

        Optional<PythonFunctionCall> functionCallOptional = transformCall(node, parsingContext);
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

    @Test
    @DisplayName("Correctly transform a function for get()")
    public void testTransformWithoutDotNotation() {
        String code = "r = get(w, verify=False, timeout=10)";

        Node rootNode = parseCode(code);
//        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);
        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.CALL.label);
        assertEquals(1, nodes.size());

        Node node = nodes.get(0);

        Optional<PythonFunctionCall> functionCallOptional = transformCall(node, parsingContext);
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


    @Test
    @DisplayName("Correctly transform a function for request.get()")
    public void testTransformFunctionWithDotNotation() {
        String code = "r = requests.get(w, verify=False, timeout=10)";

        Node rootNode = parseCode(code);
//        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.CALL.label);
        assertEquals(1, nodes.size());

        Node node = nodes.get(0);
        Optional<PythonFunctionCall> functionCallOptional = transformCall(node, parsingContext);
        assertTrue(functionCallOptional.isPresent());
        PythonFunctionCall functionCall = functionCallOptional.get();
        assertEquals(((AstString) functionCall.functionName).value, "get");
        assertNotNull(functionCall.moduleOrObject);
        assertEquals(functionCall.moduleOrObject.value, "requests");
        assertEquals(((AstString) functionCall.arguments.values[0].value).value, "w");
        assertNull(functionCall.arguments.values[0].name);
        assertEquals(functionCall.arguments.values[1].name.value, "verify");
        assertEquals(((AstString) functionCall.arguments.values[1].value).value, "False");
        assertEquals(functionCall.arguments.values[2].name.value, "timeout");
        assertEquals(((AstString) functionCall.arguments.values[2].value).value, "10");
    }

    @Test
    @DisplayName("Function call with a string as argument")
    public void testTransformSimpleFunctionCall() {
        String code = "eval('[1, 2, 3]')";

        Node rootNode = parseCode(code);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.CALL.label);
        assertEquals(1, nodes.size());

        Node node = nodes.get(0);
        Optional<PythonFunctionCall> functionCallOptional = transformCall(node, parsingContext);
        assertTrue(functionCallOptional.isPresent());
        PythonFunctionCall functionCall = functionCallOptional.get();
        assertEquals(((AstString) functionCall.functionName).value, "eval");
        assertNull(functionCall.moduleOrObject);
        assertEquals(1, functionCall.arguments.values.length);
        assertEquals(((AstString) functionCall.arguments.values[0].value).value, "'[1, 2, 3]'");
    }


    @Test
    @DisplayName("Function call with a string as argument")
    public void testFunctionCallDictionary() {
        String code = """
            jwt.decode(encoded, options={"verify_signature": True, "foo": "bar"})""";

        Node rootNode = parseCode(code);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.CALL.label);
        assertEquals(1, nodes.size());

        Node node = nodes.get(0);
        Optional<PythonFunctionCall> functionCallOptional = transformCall(node, parsingContext);
        assertTrue(functionCallOptional.isPresent());
        PythonFunctionCall functionCall = functionCallOptional.get();
        assertEquals(functionCall.moduleOrObject.value, "jwt");
        assertEquals(((AstString) functionCall.functionName).value, "decode");
        assertEquals(2, functionCall.arguments.values.length);
        assertEquals(functionCall.arguments.values[1].name.value, "options");
        PythonDictionary dict = (PythonDictionary) functionCall.arguments.values[1].value;
        assertEquals(((AstString) dict.elements[0].key).value, "\"verify_signature\"");
        assertEquals(((AstString) dict.elements[0].value).value, "True");

        assertEquals(((AstString) dict.elements[1].key).value, "\"foo\"");
        assertEquals(((AstString) dict.elements[1].value).value, "\"bar\"");
    }

    @Test
    @DisplayName("Function with format string as argument")
    public void testFunctionWithFormatString() {
        String code = """
            cursor.execute("SELECT * FROM users WHERE username = '{0}'".format(username));
                                                                                 """;

        Node rootNode = parseCode(code);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.CALL.label);
        assertEquals(2, nodes.size());

        Node node = nodes.get(0);
        Optional<PythonFunctionCall> functionCallOptional = transformCall(node, parsingContext);
        assertTrue(functionCallOptional.isPresent());
        PythonFunctionCall functionCall = functionCallOptional.get();
        assertEquals(functionCall.moduleOrObject.value, "cursor");
        assertEquals(((AstString) functionCall.functionName).value, "execute");
        assertEquals(1, functionCall.arguments.values.length);
        assertEquals(AST_ELEMENT_TYPE_FUNCTION_CALL, functionCall.arguments.values[0].value.astType);
        assertEquals("format", ((AstString) ((PythonFunctionCall) functionCall.arguments.values[0].value).functionName).value);
    }

    @Test
    @DisplayName("Function with array as parameter value")
    public void testFunctionArgumentArray() {
        String code = """
            StringField('name', validators=[DataRequired()])
                                                                                 """;

        Node rootNode = parseCode(code);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.CALL.label);
        assertEquals(2, nodes.size());

        Node node = nodes.get(0);
        Optional<PythonFunctionCall> functionCallOptional = transformCall(node, parsingContext);
        assertTrue(functionCallOptional.isPresent());
        PythonFunctionCall functionCall = functionCallOptional.get();
        assertEquals(2, functionCall.arguments.values.length);
        assertEquals("validators", functionCall.arguments.values[1].name.value);
        assertEquals("list", functionCall.arguments.values[1].value.astType);
        assertEquals("functioncall", ((PythonList) functionCall.arguments.values[1].value).elements[0].astType);
    }


}
