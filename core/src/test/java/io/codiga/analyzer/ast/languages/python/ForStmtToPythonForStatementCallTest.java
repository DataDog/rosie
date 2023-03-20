package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.python.PythonForStatement;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.antlr.python.transformations.ForStmtToForStatement.transformForStatement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForStmtToPythonForStatementCallTest extends PythonTestUtils {

    private Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Correctly map for statement - first test")
    public void testMapForStatement1() {
        String code = """
            for x in range(1):
                pass
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.For_stmtContext.class);

        for (ParseTree node : exprNodes) {
            Optional<PythonForStatement> forStatementOptional = transformForStatement((PythonParser.For_stmtContext) node, null);
            assertTrue(forStatementOptional.isPresent());
            PythonForStatement forStatement = forStatementOptional.get();

            assertEquals("x", forStatement.variables[0].atom.str);
            assertEquals("range(1)", forStatement.list.str);
        }
    }


}
