package io.codiga.analyzer.ast.languages.typescript;

import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstArray;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.javascript.AstStringWithSpreadOperator;
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
import static org.junit.jupiter.api.Assertions.*;

public class AssignmentTest extends TypeScriptTestUtils {

    private Logger log = Logger.getLogger("Test");

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
}
