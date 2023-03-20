package io.codiga.analyzer.ast.languages.javascript;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.Sequence;
import io.codiga.model.ast.common.Switch;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.model.ast.common.AstElement.*;
import static io.codiga.parser.antlr.javascript.transformations.JavaScriptSwitchStatement.transformSwitchStatement;
import static org.junit.jupiter.api.Assertions.*;

public class SwitchTest extends JavaScriptTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }


    @Test
    @DisplayName("First switch handling")
    public void testSwitch1() {
        String code = """
            const expr = 'Papayas';
            switch (expr) {
              case 'Oranges':
                console.log('Oranges are $0.59 a pound.');
                break;
              case 'Mangoes':
              case 'Papayas':
                console.log('Mangoes and papayas are $2.79 a pound.');
                // Expected output: "Mangoes and papayas are $2.79 a pound."
                break;
              case 'Pineapple':
                return "Yum";
              case 'Apples': {
                const howmanyleft = fetch();
                console.log("bla")
                break;
              }
              default:
                console.log(`Sorry, we are out of ${expr}.`);
            }
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.SwitchStatementContext.class);
        assertEquals(1, nodes.size());
        Optional<Switch> switchOptional = transformSwitchStatement((JavaScriptParser.SwitchStatementContext) nodes.get(0), null);
        assertTrue(switchOptional.isPresent());
        Switch switchStatement = switchOptional.get();
        assertEquals(5, switchStatement.cases.length);
        assertNotNull(switchStatement.defaultCase);
        assertEquals(AST_ELEMENT_TYPE_STRING, switchStatement.expression.astType);
        assertEquals("expr", ((AstString) switchStatement.expression).value);
        assertEquals(AST_ELEMENT_TYPE_SEQUENCE, switchStatement.defaultCase.content.astType);
        assertEquals(1, ((Sequence) switchStatement.defaultCase.content).elements.length);
        assertEquals(AST_ELEMENT_TYPE_FUNCTION_CALL, ((Sequence) switchStatement.defaultCase.content).elements[0].astType);

        assertEquals(AST_ELEMENT_TYPE_SEQUENCE, switchStatement.cases[4].content.astType);
        assertEquals(3, ((Sequence) switchStatement.cases[4].content).elements.length);
        assertEquals(AST_ELEMENT_TYPE_SEQUENCE, switchStatement.cases[4].content.astType);
        assertEquals(AST_ELEMENT_TYPE_BREAK, ((Sequence) switchStatement.cases[4].content).elements[2].astType);
    }


}
