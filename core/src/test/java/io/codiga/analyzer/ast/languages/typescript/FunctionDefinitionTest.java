package io.codiga.analyzer.ast.languages.typescript;

import io.codiga.model.ast.common.FunctionDefinition;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionDefinitionTest extends TypeScriptTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }


    @Test
    @DisplayName("Parse a function call")
    public void testParseClassWithParent() {
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
        assertEquals("bli", functionDefinition.parameters.values[1].name.value);
    }


}
