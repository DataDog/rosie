package io.codiga.analyzer.ast.languages.python.treesitter;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.python.PythonAssertStatement;
import io.codiga.model.ast.python.PythonTuple;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.AssertTransformation.transformAssert;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertTest extends PythonTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Transform assert()")
    public void testTransformAssertTuple() {
        String code = "assert (1, None)";

        Node rootNode = parseCode(code);
        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.ASSERT_STATEMENT.label);
        assertEquals(1, nodes.size());

        Node node = nodes.get(0);
        Optional<PythonAssertStatement> assertStatementOptional = transformAssert(node, parsingContext);
        assertTrue(assertStatementOptional.isPresent());
        assertEquals(AstElementTypes.TUPLE.label, assertStatementOptional.get().value.astType);
        assertEquals(AstElementTypes.STRING.label, ((PythonTuple) assertStatementOptional.get().value).elements[0].astType);
        assertEquals(AstElementTypes.NONE.label, ((PythonTuple) assertStatementOptional.get().value).elements[1].astType);

    }

    @Test
    @DisplayName("Transform assert()")
    public void testTransformAssertSingleValue() {
        String code = "assert x";

        Node rootNode = parseCode(code);
        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);

        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.ASSERT_STATEMENT.label);
        assertEquals(1, nodes.size());

        Node node = nodes.get(0);
        Optional<PythonAssertStatement> assertStatementOptional = transformAssert(node, parsingContext);
        assertTrue(assertStatementOptional.isPresent());
        assertEquals("string", assertStatementOptional.get().value.astType);
        assertEquals("x", ((AstString) assertStatementOptional.get().value).value);
    }

}
