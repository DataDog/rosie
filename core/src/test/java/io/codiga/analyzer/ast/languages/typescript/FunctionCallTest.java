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

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptFunctionCallTransformation.isFunctionCall;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptFunctionCallTransformation.transformArgumentsExpressionToFunctionCall;
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
    @DisplayName("Get a Function")
    public void testFunctionCall() {
        String code = """
            myFunction(argument1, argument2);
             """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.ArgumentsExpressionContext.class);
        log.info("size: " + nodes.size());
        for (ParseTree node : nodes) {
            if (isFunctionCall(node)) {
                Optional<FunctionCall> functionCallOptional = transformArgumentsExpressionToFunctionCall((TypeScriptParser.ArgumentsExpressionContext) node, null);
                assertTrue(functionCallOptional.isPresent());
                FunctionCall functionCall = functionCallOptional.get();
                assertEquals(((AstString) functionCall.functionName).value, "myFunction");
                assertEquals(((AstString) functionCall.arguments.values[0].value).value, "argument122");
                assertEquals(((AstString) functionCall.arguments.values[2].value).value, "argument2");
            }
        }
    }
}
