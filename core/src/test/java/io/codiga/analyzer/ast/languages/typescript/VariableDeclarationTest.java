package io.codiga.analyzer.ast.languages.typescript;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.VariableDeclaration;
import io.codiga.model.ast.typescript.TypeScriptTypeOperation;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptVariableStatement.transformVariableStatementToVariableDeclaration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VariableDeclarationTest extends TypeScriptTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }


    @Test
    @DisplayName("Variable Declaration With Type")
    public void testVariableDeclarationWithType() {
        String code = """
            const bla: int = 1;
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.VariableStatementContext.class);
        assertEquals(1, nodes.size());
        List<VariableDeclaration> variableDeclarationList = transformVariableStatementToVariableDeclaration((TypeScriptParser.VariableStatementContext) nodes.get(0), null);
        assertEquals(1, variableDeclarationList.size());
        VariableDeclaration variableDeclaration = variableDeclarationList.get(0);
        assertEquals("const", variableDeclaration.modifier.value);
        assertEquals("bla", ((AstString) variableDeclaration.name).value);
        assertEquals("int", ((AstString) variableDeclaration.type).value);
        assertEquals("1", ((AstString) variableDeclaration.value).value);

    }


    @Test
    @DisplayName("Variable Declaration With Type and Union")
    public void testVariableDeclarationWithTypeUnion() {
        String code = """
            const bla: int | string = 1;
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.VariableStatementContext.class);
        assertEquals(1, nodes.size());
        List<VariableDeclaration> variableDeclarationList = transformVariableStatementToVariableDeclaration((TypeScriptParser.VariableStatementContext) nodes.get(0), null);
        assertEquals(1, variableDeclarationList.size());
        VariableDeclaration variableDeclaration = variableDeclarationList.get(0);
        assertEquals("const", variableDeclaration.modifier.value);
        assertEquals("bla", ((AstString) variableDeclaration.name).value);
        assertEquals("typeoperation", variableDeclaration.type.astType);

        TypeScriptTypeOperation t = (TypeScriptTypeOperation) variableDeclaration.type;
        assertEquals("|", t.operand.value);
        assertEquals("int", ((AstString) t.left).value);
        assertEquals("string", ((AstString) t.right).value);
        assertEquals("1", ((AstString) variableDeclaration.value).value);

    }


    @Test
    @DisplayName("Variable Declaration With Type and Intersection")
    public void testVariableDeclarationWithTypeIntersection() {
        String code = """
            const bla: int & string = 1;
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.VariableStatementContext.class);
        assertEquals(1, nodes.size());
        List<VariableDeclaration> variableDeclarationList = transformVariableStatementToVariableDeclaration((TypeScriptParser.VariableStatementContext) nodes.get(0), null);
        assertEquals(1, variableDeclarationList.size());
        VariableDeclaration variableDeclaration = variableDeclarationList.get(0);
        assertEquals("const", variableDeclaration.modifier.value);
        assertEquals("bla", ((AstString) variableDeclaration.name).value);
        assertEquals("typeoperation", variableDeclaration.type.astType);

        TypeScriptTypeOperation t = (TypeScriptTypeOperation) variableDeclaration.type;
        assertEquals("&", t.operand.value);
        assertEquals("int", ((AstString) t.left).value);
        assertEquals("string", ((AstString) t.right).value);
        assertEquals("1", ((AstString) variableDeclaration.value).value);

    }


}
