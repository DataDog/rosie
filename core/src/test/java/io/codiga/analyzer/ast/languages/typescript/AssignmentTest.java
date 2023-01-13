package io.codiga.analyzer.ast.languages.typescript;

import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstArray;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.Sequence;
import io.codiga.model.ast.javascript.AstStringWithSpreadOperator;
import io.codiga.model.ast.javascript.JavaScriptFunctionExpression;
import io.codiga.model.ast.javascript.JavaScriptObject;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptAssignmentExpression.transformAssignmentExpressionToAssignment;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptVariableDeclarationToAssignment.transformVariableDeclarationToAssignment;
import static io.codiga.model.ast.common.AstElement.*;
import static org.junit.jupiter.api.Assertions.*;

public class AssignmentTest extends TypeScriptTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }


    @Test
    @DisplayName("Get assignment from variable declaration")
    public void testAssignementFromDeclaration() {
        String code = """
            const bla = 1;
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.VariableDeclarationContext.class);
        assertEquals(1, nodes.size());
        for (ParseTree node : nodes) {
            Optional<Assignment> assignmentOptional = transformVariableDeclarationToAssignment((TypeScriptParser.VariableDeclarationContext) node, null);
            assertTrue(assignmentOptional.isPresent());
            assertEquals("bla", ((AstString) assignmentOptional.get().left).value);
            assertEquals("1", ((AstString) assignmentOptional.get().right).value);
        }
    }


    @Test
    @DisplayName("Get assignment from regular assignment")
    public void testAssignmentFromAssignment() {
        String code = """
            var foo;
            foo = 3;
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.AssignmentExpressionContext.class);

        for (ParseTree node : nodes) {
            Optional<Assignment> assignmentOptional = transformAssignmentExpressionToAssignment((TypeScriptParser.AssignmentExpressionContext) node, null);
            assertTrue(assignmentOptional.isPresent());
            assertEquals("foo", ((AstString) assignmentOptional.get().left).value);
            assertEquals("3", ((AstString) assignmentOptional.get().right).value);
        }
    }

    @Test
    @DisplayName("Array values with spread element")
    public void testArrayAssignment() {
        String code = """
            numbers = [1, ...foo, 2];
            """;

        ParseTree root = parseCode(code);


        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.VariableDeclarationContext.class);
        assertEquals(1, nodes.size());
        for (ParseTree node : nodes) {
            Optional<Assignment> assignmentOptional = transformVariableDeclarationToAssignment((TypeScriptParser.VariableDeclarationContext) node, null);
            assertTrue(assignmentOptional.isPresent());
            Assignment assignment = assignmentOptional.get();
            assertEquals("numbers", ((AstString) assignment.left).value);
            AstArray arr = (AstArray) assignment.right;
            assertEquals(3, arr.elements.length);
            assertEquals("1", ((AstStringWithSpreadOperator) arr.elements[0]).value);
            assertFalse(((AstStringWithSpreadOperator) arr.elements[0]).isSpread);
            assertEquals("foo", ((AstStringWithSpreadOperator) arr.elements[1]).value);
            assertTrue(((AstStringWithSpreadOperator) arr.elements[1]).isSpread);

        }
    }

    @Test
    @DisplayName("Object values with spread")
    public void testObjectWithSpread() {
        String code = """
            foo = { ...obj, key: 'value' };
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.VariableDeclarationContext.class);
        assertEquals(1, nodes.size());

        for (ParseTree node : nodes) {
            Optional<Assignment> assignmentOptional = transformVariableDeclarationToAssignment((TypeScriptParser.VariableDeclarationContext) node, null);
            assertTrue(assignmentOptional.isPresent());
            Assignment assignment = assignmentOptional.get();
            assertEquals("foo", ((AstString) assignment.left).value);
            JavaScriptObject obj = (JavaScriptObject) assignment.right;
            assertEquals(2, obj.elements.length);
            assertNull(obj.elements[0].name);
            assertTrue(((AstStringWithSpreadOperator) obj.elements[0].value).isSpread);
            assertEquals("obj", ((AstStringWithSpreadOperator) obj.elements[0].value).value);
            assertEquals("key", ((AstString) obj.elements[1].name).value);
            assertEquals("'value'", ((AstString) obj.elements[1].value).value);
        }
    }


    @Test
    @DisplayName("Assignment Function")
    public void testAssignmentFunction() {
        String code = """
            c = (foo, bar) => {};
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.VariableDeclarationContext.class);
        assertEquals(1, nodes.size());
        Optional<Assignment> assignmentOptional = transformVariableDeclarationToAssignment((TypeScriptParser.VariableDeclarationContext) nodes.get(0), null);
        assertTrue(assignmentOptional.isPresent());
        Assignment assignment = assignmentOptional.get();
        assertEquals("c", ((AstString) assignment.left).value);
        assertEquals(AST_ELEMENT_TYPE_FUNCTION_EXPRESSION, assignment.right.astType);
    }


    @Test
    @DisplayName("Assign a function expression")
    public void testAssignmentFunctionExpression() {
        String code = """
            const MyComp = () => {
              const [payload, setPayload] = useState();
              const [result, setResult] = useState();
             
              const handleClick = () => {
                // invalid
                setPayload(useContext(MyContext));
              };
             
              useEffect(() => {
                fetch(payload).then((res) => {
                  setResult(res);
                });
              }, [payload]);

              return <button onClick={handleClick}>my button</button>;
            }
            """;

        ParseTree root = parseCode(code);
        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.VariableDeclarationContext.class);

        assertEquals(8, nodes.size());
        TypeScriptParser.VariableDeclarationContext firstVariableDeclaration = (TypeScriptParser.VariableDeclarationContext) nodes.get(0);
        Optional<Assignment> assignmentOptional = transformVariableDeclarationToAssignment(firstVariableDeclaration, null);
        assertTrue(assignmentOptional.isPresent());
        Assignment assignment = assignmentOptional.get();
        assertEquals(AST_ELEMENT_TYPE_FUNCTION_EXPRESSION, assignment.right.astType);
        JavaScriptFunctionExpression functionExpression = (JavaScriptFunctionExpression) assignment.right;
        assertEquals(AST_ELEMENT_TYPE_SEQUENCE, functionExpression.content.astType);
        Sequence seq = (Sequence) functionExpression.content;
        for (var el : seq.elements) {
            log.info(el.astType);
        }
        assertEquals(5, seq.elements.length);
    }


    @Test
    @DisplayName("Assign a function expression2")
    public void testAssignmentFunctionExpression2() {
        String code = """
            const myComponent = () => {
              const { data, loading } = useQuery();

              if (loading) {
                return "bla";
              }

              // invalid
              useEffect(() => {
                // do something
              }, [data]);

              return (<div>data.example</div>);
            }
            """;

        ParseTree root = parseCode(code);
        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.VariableDeclarationContext.class);
        assertEquals(3, nodes.size());
        TypeScriptParser.VariableDeclarationContext firstVariableDeclaration = (TypeScriptParser.VariableDeclarationContext) nodes.get(0);
        Optional<Assignment> assignmentOptional = transformVariableDeclarationToAssignment(firstVariableDeclaration, null);
        assertTrue(assignmentOptional.isPresent());
        Assignment assignment = assignmentOptional.get();
        assertEquals(AST_ELEMENT_TYPE_FUNCTION_EXPRESSION, assignment.right.astType);
        JavaScriptFunctionExpression functionExpression = (JavaScriptFunctionExpression) assignment.right;
        assertEquals(AST_ELEMENT_TYPE_SEQUENCE, functionExpression.content.astType);
        Sequence seq = (Sequence) functionExpression.content;
        assertEquals(4, seq.elements.length);
        assertEquals(AST_ELEMENT_TYPE_VARIABLE_DECLARATION, seq.elements[0].astType);
        assertEquals(AST_ELEMENT_IF_STATEMENT, seq.elements[1].astType);
        assertEquals(AST_ELEMENT_TYPE_FUNCTION_CALL, seq.elements[2].astType);
        assertEquals(AST_ELEMENT_TYPE_RETURN, seq.elements[3].astType);
    }
}
