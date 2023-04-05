package io.codiga.analyzer.ast.languages.python.treesitter;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.Sequence;
import io.codiga.model.ast.python.PythonComparison;
import io.codiga.model.ast.python.PythonIfStatement;
import io.codiga.model.ast.python.PythonNot;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.IfStatementTransformation.transformIfStatement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IfStatementTest extends PythonTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Correctly map if statement - first test")
    public void testMapIfStatement1() {
        String code = "if x == 5: pass";

        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.IF_STATEMENT.label);
        assertEquals(1, nodes.size());
        Optional<PythonIfStatement> ifStatementOptional = transformIfStatement(nodes.get(0), parsingContext);


        assertTrue(ifStatementOptional.isPresent());
        PythonIfStatement ifStatement = ifStatementOptional.get();

        assertEquals(((PythonComparison) ifStatement.condition).operator, "==");
        assertEquals(AstElementTypes.SEQUENCE.label, ifStatement.statements.astType);
        assertEquals(AstElementTypes.PASS.label, ((Sequence) ifStatement.statements).elements[0].astType);
        assertEquals("x", ((AstString) ((PythonComparison) ifStatement.condition).leftSide).value);
        assertEquals("5", ((AstString) ((PythonComparison) ifStatement.condition).rightSide).value);

    }


    @Test
    @DisplayName("regular if statement with else")
    public void testMapIfStatement2() {
        String code = """
            if x == 5:
               print("foo")
               foo = bar
            else:
               pass
            """;


        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.IF_STATEMENT.label);
        assertEquals(1, nodes.size());
        Optional<PythonIfStatement> ifStatementOptional = transformIfStatement(nodes.get(0), parsingContext);


        assertTrue(ifStatementOptional.isPresent());
        PythonIfStatement ifStatement = ifStatementOptional.get();

        assertEquals(((PythonComparison) ifStatement.condition).operator, "==");
        assertEquals(AstElementTypes.SEQUENCE.label, ifStatement.statements.astType);
        Sequence seq = (Sequence) ifStatement.statements;
        assertEquals(2, seq.elements.length);
        assertEquals(AstElementTypes.FUNCTION_CALL.label, seq.elements[0].astType);
        assertEquals(AstElementTypes.ASSIGNMENT.label, seq.elements[1].astType);
        assertEquals(AstElementTypes.SEQUENCE.label, ifStatement.elseStatements.statements.astType);
        assertEquals(AstElementTypes.PASS.label, ((Sequence) ifStatement.elseStatements.statements).elements[0].astType);

    }


    @Test
    @DisplayName("regular if statement with == True in the condition")
    public void testIfStatementWithTrueInCondition() {
        String code = """
            bla = 1
            if bla == True:
                print("hello")
                """;


        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.IF_STATEMENT.label);
        assertEquals(1, nodes.size());
        Optional<PythonIfStatement> ifStatementOptional = transformIfStatement(nodes.get(0), parsingContext);


        assertTrue(ifStatementOptional.isPresent());
        PythonIfStatement ifStatement = ifStatementOptional.get();

        assertEquals(((PythonComparison) ifStatement.condition).operator, "==");
        assertEquals("string", ((PythonComparison) ifStatement.condition).leftSide.astType);
        assertEquals("string", ((PythonComparison) ifStatement.condition).rightSide.astType);
        assertEquals("bla", ((AstString) ((PythonComparison) ifStatement.condition).leftSide).value);
        assertEquals("True", ((AstString) ((PythonComparison) ifStatement.condition).rightSide).value);
        assertEquals("==", ((PythonComparison) ifStatement.condition).operator);
        assertEquals(AstElementTypes.SEQUENCE.label, ifStatement.statements.astType);
        Sequence seq = (Sequence) ifStatement.statements;
        assertEquals(1, seq.elements.length);
        assertEquals(AstElementTypes.FUNCTION_CALL.label, seq.elements[0].astType);

    }


    @Test
    @DisplayName("condition with not")
    public void testIfWithNot() {
        String code = """
            bla = 1
            if not bla:
                print("hello")
                """;


        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.IF_STATEMENT.label);
        assertEquals(1, nodes.size());
        Optional<PythonIfStatement> ifStatementOptional = transformIfStatement(nodes.get(0), parsingContext);


        assertTrue(ifStatementOptional.isPresent());
        PythonIfStatement ifStatement = ifStatementOptional.get();

        assertEquals("not", (ifStatement.condition).astType);
        assertEquals("string", ((PythonNot) (ifStatement.condition)).value.astType);
        assertEquals("bla", ((AstString) ((PythonNot) (ifStatement.condition)).value).value);
    }


    @Test
    @DisplayName("elif being parsed")
    public void testElif() {
        String code = """
            bla = 1
            if not bla:
                pass
            elif foo:
                print("hello")
            elif baz:
                print("hello")
            else:
                pass
                """;


        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.IF_STATEMENT.label);
        assertEquals(1, nodes.size());
        Optional<PythonIfStatement> ifStatementOptional = transformIfStatement(nodes.get(0), parsingContext);


        assertTrue(ifStatementOptional.isPresent());
        PythonIfStatement ifStatement = ifStatementOptional.get();
        assertEquals(2, ifStatement.elifStatements.length);
        assertEquals("foo", ((AstString) ifStatement.elifStatements[0].condition).value);
        assertEquals("functioncall", ((Sequence) ifStatement.elifStatements[0].statements).elements[0].astType);
        assertEquals("baz", ((AstString) ifStatement.elifStatements[1].condition).value);
        assertEquals("functioncall", ((Sequence) ifStatement.elifStatements[1].statements).elements[0].astType);

    }
}
