package io.codiga.server.e2e.javascript_typescript.ast;

import io.codiga.model.EntityChecked;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunctionCallAwaitTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(FunctionCallAwaitTest.class);


    String codeWithError = """
                function delay(t, v) {
                  return new Promise(resolve => setTimeout(resolve, t, v));
                }
                        
                test('should not pass', async () => {
        function delay(t, v) {
          return new Promise(resolve => setTimeout(resolve, t, v));
        }

        test('should not pass', async () => {

          await delay(10000, 'Delayed for 10 seconds.')

          expect(true).toBeDefined();
        });

        """;


    String ruleCode = """
        const isExpect = (element) => {
            if (element.astType === "functioncall" &&
                element.functionName &&
                element.functionName.astType === "string" &&
                element.functionName.value === "expect") {
                return true;
            }

            if (element.astType === "functioncall" && element.functionName.astType === "member") {
                return isExpect(element.functionName);
            }

            if (element.astType === "member") {
                return isExpect(element.parent);
            }
            return false;
        }

        function visit(node, filename, code) {
            console.log(JSON.stringify(node))
            if (!filename.includes(".spec.") && !filename.includes(".test.")) {
                return
            };

            if (node.functionName.astType !== "string") {
                return;
            }

            if (node.functionName.value !== "test" && node.functionName.value !== "it") {
                return;
            }

            if (!node.arguments || !node.arguments.values || node.arguments.values.length !== 2) {
                return;
            }

            const callback = node.arguments.values.length && node.arguments.values[1];

            if (
                callback &&
                callback.value.astType === "functionexpression" &&
                callback.value.content &&
                callback.value.content.elements
            ) {
                const elements = callback.value.content.elements;
                const nbExpect = elements.filter(e => isExpect(e)).length;
                console.log(nbExpect);

                if (nbExpect > 5) {
                    const error = buildError(node.functionName.start.line, node.functionName.start.col,
                        node.functionName.end.line, node.functionName.end.col,
                        "Too many expect()", "WARNING", "BEST_PRACTICE");
                    addError(error);
                }
            }
        }
                        """;

  @Test
  @DisplayName("test with code that has await")
  public void testWithAwait() throws Exception {
    JAVASCRIPT_TYPESCRIPT.forEach(
        l -> {
          logger.info("Running test with language: " + l);
          Response response =
              executeTest(
                  "bla.js",
                  codeWithError,
                  l,
                  ruleCode,
                  "errorUseQuery",
                  RuleType.AST_CHECK,
                  EntityChecked.FUNCTION_CALL,
                  null,
                  true);
          logger.info(response.toString());
        });
  }
}
