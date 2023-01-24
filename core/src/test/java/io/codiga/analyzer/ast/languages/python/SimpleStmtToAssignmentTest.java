package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.VariableIndex;
import io.codiga.model.ast.python.PythonFunctionCall;
import io.codiga.model.ast.python.PythonList;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.python.transformations.ExprStmtTransformation.isAssignment;
import static io.codiga.analyzer.ast.languages.python.transformations.ExprStmtTransformation.transformExprStmtToAssignment;
import static io.codiga.model.ast.common.AstElement.AST_ELEMENT_TYPE_FUNCTION_CALL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleStmtToAssignmentTest extends PythonTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Correctly transform a assignment")
    public void testTransformAssignment() {
        String code = "v = 10";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Expr_stmtContext.class);
        assertEquals(1, exprNodes.size());
        PythonParser.Expr_stmtContext node = (PythonParser.Expr_stmtContext) exprNodes.get(0);


        assertTrue(isAssignment(node));
        Optional<Assignment> assignmentOptional = transformExprStmtToAssignment(node, null);
        assertTrue(assignmentOptional.isPresent());
        Assignment assignment = assignmentOptional.get();
        assertEquals("v", assignment.left.getText());
        assertEquals("10", assignment.right.getText());
    }

    @Test
    @DisplayName("Correctly transform a assignment with a list on both sides")
    public void testTransformAssignmentWithList() {
        String code = "v, w = 42, 51";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Expr_stmtContext.class);
        assertEquals(1, exprNodes.size());
        PythonParser.Expr_stmtContext node = (PythonParser.Expr_stmtContext) exprNodes.get(0);

        assertTrue(isAssignment(node));
        Optional<Assignment> assignmentOptional = transformExprStmtToAssignment(node, null);
        assertTrue(assignmentOptional.isPresent());
        Assignment assignment = assignmentOptional.get();
        assertEquals("v", ((AstString) (((PythonList) assignment.left).elements[0])).str);
        assertEquals("w", ((AstString) (((PythonList) assignment.left).elements[1])).str);
        assertEquals("42", ((AstString) (((PythonList) assignment.right).elements[0])).str);
        assertEquals("51", ((AstString) (((PythonList) assignment.right).elements[1])).str);
    }


    @Test
    @DisplayName("Test with f-string assignment")
    public void testTransformFstring() {
        String code = "v = f\"SELECT foo FROM bar WHERE plop={bli}\"";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Expr_stmtContext.class);
        assertEquals(1, exprNodes.size());
        PythonParser.Expr_stmtContext node = (PythonParser.Expr_stmtContext) exprNodes.get(0);
        assertTrue(isAssignment(node));
        Optional<Assignment> assignmentOptional = transformExprStmtToAssignment(node, null);
        assertTrue(assignmentOptional.isPresent());
        Assignment assignment = assignmentOptional.get();
        assertEquals("v", ((AstString) assignment.left).str);
        assertEquals("f\"SELECT foo FROM bar WHERE plop={bli}\"", ((AstString) assignment.right).str);

    }


    @Test
    @DisplayName("Test with format string")
    public void testTransformFormatString() {
        String code = "v = \"SELECT foo FROM bar WHERE plop={0}\".format(bli)";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Expr_stmtContext.class);
        assertEquals(1, exprNodes.size());
        PythonParser.Expr_stmtContext node = (PythonParser.Expr_stmtContext) exprNodes.get(0);
        assertTrue(isAssignment(node));
        Optional<Assignment> assignmentOptional = transformExprStmtToAssignment(node, null);
        assertTrue(assignmentOptional.isPresent());
        Assignment assignment = assignmentOptional.get();
        assertEquals("v", ((AstString) assignment.left).str);
        assertEquals(AST_ELEMENT_TYPE_FUNCTION_CALL, assignment.right.astType);
        PythonFunctionCall functionCall = (PythonFunctionCall) assignment.right;
        assertEquals("format", ((AstString) functionCall.functionName).value);
        assertEquals("\"SELECT foo FROM bar WHERE plop={0}\"", functionCall.moduleOrObject.str);


    }

    @Test
    @DisplayName("Assignment to key")
    public void testAssignmentToKey() {
        String code = "response.headers['Strict-Transport-Security'] = 'max-age=31536000; includeSubDomains'";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Expr_stmtContext.class);
        assertEquals(1, exprNodes.size());
        PythonParser.Expr_stmtContext node = (PythonParser.Expr_stmtContext) exprNodes.get(0);

        assertTrue(isAssignment(node));
        Optional<Assignment> assignmentOptional = transformExprStmtToAssignment(node, null);
        assertTrue(assignmentOptional.isPresent());
//        Assignment assignment = assignmentOptional.get();
//        assertEquals("v", ((AstString) assignment.left).str);
//        assertEquals(AST_ELEMENT_TYPE_FUNCTION_CALL, assignment.right.astType);
//        PythonFunctionCall functionCall = (PythonFunctionCall) assignment.right;
//        assertEquals("format", ((AstString) functionCall.functionName).value);
//        assertEquals("\"SELECT foo FROM bar WHERE plop={0}\"", functionCall.moduleOrObject.str);

    }


    @Test
    @DisplayName("Assignment with index")
    public void testTransformAssignmentWithIndex() {
        String code = """
            response["Set-Cookie"] = value""";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Expr_stmtContext.class);
        assertEquals(1, exprNodes.size());
        PythonParser.Expr_stmtContext node = (PythonParser.Expr_stmtContext) exprNodes.get(0);


        assertTrue(isAssignment(node));
        Optional<Assignment> assignmentOptional = transformExprStmtToAssignment(node, null);
        assertTrue(assignmentOptional.isPresent());
        Assignment assignment = assignmentOptional.get();
        assertEquals("variableindex", assignment.left.astType);
        VariableIndex variableIndex = (VariableIndex) assignment.left;
        assertEquals("\"Set-Cookie\"", ((AstString) variableIndex.index).value);
        assertEquals("response", ((AstString) variableIndex.variable).value);
    }

    @Test
    @DisplayName("Assignment with index and a member")
    public void testTransformIndexAndMember() {
        String code = """
            url = request.args["next_page"]""";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Expr_stmtContext.class);
        assertEquals(1, exprNodes.size());
        PythonParser.Expr_stmtContext node = (PythonParser.Expr_stmtContext) exprNodes.get(0);


        assertTrue(isAssignment(node));
        Optional<Assignment> assignmentOptional = transformExprStmtToAssignment(node, null);
        assertTrue(assignmentOptional.isPresent());
        Assignment assignment = assignmentOptional.get();
        VariableIndex variableIndex = (VariableIndex) assignment.right;
        assertEquals("request.args", ((AstString) variableIndex.variable).value);
    }

    @Test
    @DisplayName("Assignment with index and two members")
    public void testTransformIndexAndTwoMembers() {
        String code = """
            url = request.foo.args["next_page"]""";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Expr_stmtContext.class);
        assertEquals(1, exprNodes.size());
        PythonParser.Expr_stmtContext node = (PythonParser.Expr_stmtContext) exprNodes.get(0);


        assertTrue(isAssignment(node));
        Optional<Assignment> assignmentOptional = transformExprStmtToAssignment(node, null);
        assertTrue(assignmentOptional.isPresent());
        Assignment assignment = assignmentOptional.get();
        VariableIndex variableIndex = (VariableIndex) assignment.right;
        assertEquals("request.foo.args", ((AstString) variableIndex.variable).value);
    }

    @Test
    @DisplayName("Assignment with index and no member")
    public void testTransformIndexAndNoMembers() {
        String code = """
            url = request["next_page"]""";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Expr_stmtContext.class);
        assertEquals(1, exprNodes.size());
        PythonParser.Expr_stmtContext node = (PythonParser.Expr_stmtContext) exprNodes.get(0);


        assertTrue(isAssignment(node));
        Optional<Assignment> assignmentOptional = transformExprStmtToAssignment(node, null);
        assertTrue(assignmentOptional.isPresent());
        Assignment assignment = assignmentOptional.get();
        VariableIndex variableIndex = (VariableIndex) assignment.right;
        assertEquals("request", ((AstString) variableIndex.variable).value);
    }
}
