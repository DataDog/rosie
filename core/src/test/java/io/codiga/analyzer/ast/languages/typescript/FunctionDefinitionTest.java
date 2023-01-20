package io.codiga.analyzer.ast.languages.typescript;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinition;
import io.codiga.model.ast.javascript.JavaScriptFunctionDefinition;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptFunctionDeclarationToFunctionDefinition.transformFunctionDeclarationToFunctionDefinition;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionDefinitionTest extends TypeScriptTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }


    @Test
    @DisplayName("Parse a function definition")
    public void testParseFunctionDefinition() {
        String code = """
            function foo(bla, bli) {
                console.log("foobar");
            }""";

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.FunctionDeclarationContext.class);

        assertEquals(1, nodes.size());

        Optional<FunctionDefinition> functionDefinitionOptional = transformFunctionDeclarationToFunctionDefinition((TypeScriptParser.FunctionDeclarationContext) nodes.get(0), null);
        assertTrue(functionDefinitionOptional.isPresent());
        FunctionDefinition functionDefinition = functionDefinitionOptional.get();
        assertEquals(functionDefinition.name.value, "foo");
        assertEquals(2, functionDefinition.parameters.values.length);
        assertEquals("bla", functionDefinition.parameters.values[0].name.value);
        assertNull(functionDefinition.parameters.values[0].type);
        assertEquals("bli", functionDefinition.parameters.values[1].name.value);
        assertNull(functionDefinition.parameters.values[1].type);
    }

    @Test
    @DisplayName("Parse a function definition")
    public void testParseFunctionDefinitionWithTypes() {
        String code = """
            function foo(bla: int, bli: any) {
                console.log("foobar");
            }""";

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.FunctionDeclarationContext.class);

        assertEquals(1, nodes.size());

        Optional<FunctionDefinition> functionDefinitionOptional = transformFunctionDeclarationToFunctionDefinition((TypeScriptParser.FunctionDeclarationContext) nodes.get(0), null);
        assertTrue(functionDefinitionOptional.isPresent());
        FunctionDefinition functionDefinition = functionDefinitionOptional.get();
        assertEquals(functionDefinition.name.value, "foo");
        assertEquals(2, functionDefinition.parameters.values.length);
        assertEquals("bla", functionDefinition.parameters.values[0].name.value);
        assertEquals("int", ((AstString) functionDefinition.parameters.values[0].type).value);
        assertEquals("bli", functionDefinition.parameters.values[1].name.value);
        assertEquals("any", ((AstString) functionDefinition.parameters.values[1].type).value);
    }

    @Test
    @DisplayName("Have an async function")
    public void testAsync() {
        String code = """
            async function foo(bar, baz) {
              var bli = 1;
            }
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.FunctionDeclarationContext.class);
        TypeScriptParser.FunctionDeclarationContext node = (TypeScriptParser.FunctionDeclarationContext) nodes.get(0);
        Optional<FunctionDefinition> functionCallOptional = transformFunctionDeclarationToFunctionDefinition(node, null);
        assertTrue(functionCallOptional.isPresent());
        JavaScriptFunctionDefinition functionDefinition = (JavaScriptFunctionDefinition) functionCallOptional.get();
        assertEquals(functionDefinition.name.value, "foo");
        assertEquals(functionDefinition.name.value, "foo");
        assertEquals(functionDefinition.parameters.values[0].name.value, "bar");
        assertEquals(functionDefinition.parameters.values[1].name.value, "baz");
        assertTrue(functionDefinition.isAsync);
    }
}
