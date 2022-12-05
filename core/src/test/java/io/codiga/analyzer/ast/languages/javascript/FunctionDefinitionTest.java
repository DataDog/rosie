package io.codiga.analyzer.ast.languages.javascript;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinition;
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

    private Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Get the function definition")
    public void testFunctionCallValue() {
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
            assertEquals(((AstString) functionDefinition.parameters.values[0].name).value, "bar");
            assertEquals(((AstString) functionDefinition.parameters.values[1].name).value, "baz");
        }
    }
}
