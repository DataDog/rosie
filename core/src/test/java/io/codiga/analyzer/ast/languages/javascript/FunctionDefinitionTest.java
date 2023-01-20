package io.codiga.analyzer.ast.languages.javascript;

import io.codiga.model.ast.common.FunctionDefinition;
import io.codiga.model.ast.javascript.JavaScriptFunctionDefinition;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptFunctionDeclarationToFunctionDefinition.transformFunctionDeclarationToFunctionDefinition;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionDefinitionTest extends JavaScriptTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Get the function definition with the function keyword")
    public void testFunctionDefinition() {
        String code = """
            function foo(bar, baz) {
              var bli = 1;
            }
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.FunctionDeclarationContext.class);

        for (ParseTree node : nodes) {
            Optional<FunctionDefinition> functionCallOptional = transformFunctionDeclarationToFunctionDefinition((JavaScriptParser.FunctionDeclarationContext) node, null);
            assertTrue(functionCallOptional.isPresent());
            FunctionDefinition functionDefinition = functionCallOptional.get();
            assertEquals(functionDefinition.name.value, "foo");
            assertEquals(functionDefinition.parameters.values[0].name.value, "bar");
            assertEquals(functionDefinition.parameters.values[1].name.value, "baz");
        }
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

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.FunctionDeclarationContext.class);
        JavaScriptParser.FunctionDeclarationContext node = (JavaScriptParser.FunctionDeclarationContext) nodes.get(0);
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
