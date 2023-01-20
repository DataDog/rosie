package io.codiga.analyzer.ast.languages.javascript;

import io.codiga.model.ast.javascript.JavaScriptFunctionExpression;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptAnonymousFunction.transformAnonymousFunction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionExpressionTest extends JavaScriptTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Parse function expression with async")
    public void testFunctionExpression() {
        String code = """
            describe('myFunction()', async () => {
              // comment
            });
                        """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.ArrowFunctionContext.class);
        assertEquals(1, nodes.size());
        JavaScriptParser.AnoymousFunctionContext anoymousFunctionContext = (JavaScriptParser.ArrowFunctionContext) nodes.get(0);

        var opt = transformAnonymousFunction(anoymousFunctionContext, null);
        log.info(opt.get().getText());
        assertTrue(opt.isPresent());
        JavaScriptFunctionExpression fe = (JavaScriptFunctionExpression) opt.get();
        assertTrue(fe.isAsync);

    }


}
