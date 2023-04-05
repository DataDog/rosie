package io.codiga.analyzer.ast.languages.python.treesitter;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.Sequence;
import io.codiga.model.ast.common.WhileStatement;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.WhileStatementTransformation.transformWhileStatement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WhileStatementTest extends PythonTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Correctly map while statement")
    public void mapWhileStatement() {
        String code = """
            while True:
                pass
            """;

        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.WHILE_STATEMENT.label);
        assertEquals(1, nodes.size());
        Optional<WhileStatement> whileStatementOptional = transformWhileStatement(nodes.get(0), parsingContext);

        assertTrue(whileStatementOptional.isPresent());
        WhileStatement whileStatement = whileStatementOptional.get();

        assertEquals("True", ((AstString) whileStatement.condition).value);
        assertEquals(AstElementTypes.PASS.label, ((Sequence) whileStatement.statements).elements[0].astType);
    }


}
