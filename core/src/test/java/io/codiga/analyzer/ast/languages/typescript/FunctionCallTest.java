package io.codiga.analyzer.ast.languages.typescript;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionCall;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIdentifierExpressionTransformation.isIdentifierExpressionFunctionCall;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIdentifierExpressionTransformation.transformIdentifierExpressionToFunctionCall;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionCallTest extends TypeScriptTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }


    @Test
    @DisplayName("Get a function call via expression")
    public void testFunctionCallExpression() {
        String code = """
            var bla = 1;
            var foo = baz(baz);
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.IdentifierExpressionContext.class);
        assertEquals(2, nodes.size());
        for (ParseTree node : nodes) {
            if (isIdentifierExpressionFunctionCall((TypeScriptParser.IdentifierExpressionContext) node)) {
                Optional<FunctionCall> functionCallOptional = transformIdentifierExpressionToFunctionCall((TypeScriptParser.IdentifierExpressionContext) node, null);
                assertTrue(functionCallOptional.isPresent());
                FunctionCall functionCall = functionCallOptional.get();
                assertEquals(((AstString) functionCall.functionName).value, "baz");
                assertEquals(1, functionCall.arguments.values.length);
                assertEquals(((AstString) functionCall.arguments.values[0].value).value, "baz");

            }
        }
    }
}
