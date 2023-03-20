package io.codiga.analyzer.ast.languages.typescript;

import io.codiga.model.ast.common.FunctionDefinition;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptArrowFunctionDeclaration.transformArrowFunctionDeclarationContext;
import static org.junit.jupiter.api.Assertions.*;

public class AnonymousFunctionTest extends TypeScriptTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }


    @Test
    @DisplayName("Get the anonymous function -  arrow function")
    public void testAnonymousFunction() {
        String code = """
            const sum = (a, b) => {  // the curly brace opens a multiline function
              const result = a + b;
              return result; // if we use curly braces, then we need an explicit "return"
            };
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.ArrowFunctionDeclarationContext.class);
        assertEquals(1, nodes.size());
        for (ParseTree node : nodes) {
            Optional<FunctionDefinition> functionCallOptional = transformArrowFunctionDeclarationContext((TypeScriptParser.ArrowFunctionDeclarationContext) node, null);
            assertTrue(functionCallOptional.isPresent());
            FunctionDefinition functionDefinition = functionCallOptional.get();
            assertNull(functionDefinition.name);
            assertEquals(functionDefinition.parameters.values[0].name.value, "a");
            assertEquals(functionDefinition.parameters.values[1].name.value, "b");
        }
    }


}
