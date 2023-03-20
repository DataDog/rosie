package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.common.Sequence;
import io.codiga.model.ast.python.PythonIfStatement;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.model.ast.common.AstElement.*;
import static io.codiga.parser.antlr.python.transformations.IfStmtToIfStatement.transformIfStatement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IfStmtToIfStatementCallTest extends PythonTestUtils {

    private Logger log = Logger.getLogger("Test");

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

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.If_stmtContext.class);

        for (ParseTree node : exprNodes) {
            Optional<PythonIfStatement> ifStatementOptional = transformIfStatement((PythonParser.If_stmtContext) node, null);
            assertTrue(ifStatementOptional.isPresent());
            PythonIfStatement ifStatement = ifStatementOptional.get();

            assertEquals(ifStatement.condition.operator, "==");
            assertEquals(AST_ELEMENT_TYPE_PASS, ifStatement.statements.astType);
            assertEquals("x", ifStatement.condition.leftSide.expression.atom.value);
            assertEquals("5", ifStatement.condition.rightSide.expression.atom.str);
        }
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

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.If_stmtContext.class);

        for (ParseTree node : exprNodes) {
            Optional<PythonIfStatement> ifStatementOptional = transformIfStatement((PythonParser.If_stmtContext) node, null);
            assertTrue(ifStatementOptional.isPresent());
            PythonIfStatement ifStatement = ifStatementOptional.get();

            assertEquals(ifStatement.condition.operator, "==");
            assertEquals(AST_ELEMENT_TYPE_SEQUENCE, ifStatement.statements.astType);
            Sequence seq = (Sequence) ifStatement.statements;
            assertEquals(2, seq.elements.length);
            assertEquals(AST_ELEMENT_TYPE_FUNCTION_CALL, seq.elements[0].astType);
            assertEquals(AST_ELEMENT_TYPE_ASSIGNMENT, seq.elements[1].astType);
            assertEquals(AST_ELEMENT_TYPE_SEQUENCE, ifStatement.elseStatements.statements.astType);
            assertEquals(AST_ELEMENT_TYPE_PASS, ((Sequence) ifStatement.elseStatements.statements).elements[0].astType);

        }
    }

}
