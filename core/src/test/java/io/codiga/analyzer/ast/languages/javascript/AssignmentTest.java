package io.codiga.analyzer.ast.languages.javascript;

import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptSingleExpressionTransformation.transformJavaScriptAssignmentExpressionToAssignment;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptVariableDeclarationToAssignment.transformVariableDeclartionToAssignment;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssignmentTest extends JavaScriptTestUtils {

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

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.VariableDeclarationContext.class);

        for (ParseTree node : nodes) {
            Optional<Assignment> assignmentOptional = transformVariableDeclartionToAssignment((JavaScriptParser.VariableDeclarationContext) node, null);
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

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.AssignmentExpressionContext.class);

        for (ParseTree node : nodes) {
            Optional<Assignment> assignmentOptional = transformJavaScriptAssignmentExpressionToAssignment((JavaScriptParser.AssignmentExpressionContext) node, null);
            assertTrue(assignmentOptional.isPresent());
            assertEquals("foo", ((AstString) assignmentOptional.get().left).value);
            assertEquals("3", ((AstString) assignmentOptional.get().right).value);
        }
    }


}
