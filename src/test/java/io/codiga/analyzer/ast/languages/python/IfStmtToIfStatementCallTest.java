package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.python.PythonIfStatement;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.python.IfStmtToIfStatement.transformIfStatement;
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
            assertEquals("pass<EOF>", ifStatement.statements.str);
            assertEquals("x", ifStatement.condition.leftSide.expression.atom.value);
            assertEquals("5", ifStatement.condition.rightSide.expression.atom.str);
        }
    }


}
