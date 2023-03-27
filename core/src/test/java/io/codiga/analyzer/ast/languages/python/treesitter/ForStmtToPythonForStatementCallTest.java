package io.codiga.analyzer.ast.languages.python.treesitter;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.Sequence;
import io.codiga.model.ast.python.PythonForStatement;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.model.ast.common.AstElement.*;
import static io.codiga.parser.treesitter.python.transformation.ForStatementTransformation.transformForStatement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForStmtToPythonForStatementCallTest extends PythonTestUtils {

    private final Logger log = Logger.getLogger("Test");

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

        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.FOR_STATEMENT.label);
        assertEquals(1, nodes.size());
        Optional<PythonForStatement> forStatementOptional = transformForStatement(nodes.get(0), parsingContext);

        assertTrue(forStatementOptional.isPresent());
        PythonForStatement forStatement = forStatementOptional.get();

        assertEquals("x", ((AstString) forStatement.left).value);
        assertEquals(AST_ELEMENT_TYPE_FUNCTION_CALL, forStatement.right.astType);
        assertEquals(AST_ELEMENT_TYPE_SEQUENCE, forStatement.statements.astType);
        assertEquals(AST_ELEMENT_TYPE_PASS, ((Sequence) forStatement.statements).elements[0].astType);
    }


}
