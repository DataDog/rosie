package io.codiga.server.e2e.javascript_typescript.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.EntityChecked;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunctionCallTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(FunctionCallTest.class);


    String codeWithError1 = """
        foo = my_function(foo, bar);

        """;


    String codeWithError2 = """
        my_function(foo, bar);

        """;


    String ruleCode = """
        function visit(node, filename, code) {
          const arguments = node.arguments.values;

          /*
           * Check each argument. If one argument has the variable "foo" used,
           * we report it.
           */
          arguments.forEach((a) => {
            if (a.value.astType === "string") {
              console.log(a.value.value);
              if (a.value.value === "foo") {
                const error = buildError(a.value.start.line, a.value.start.col,
                  a.value.end.line, a.value.end.col,
                  "Do not use foo as value", "WARNING", "BEST_PRACTICE");

                addError(error);
              }
            }
          });

          /*
           * Check the function name. We cannot use my_function, we recommend to use myFunction
           */
          if (node.functionName && node.functionName.astType === "string") {
            if (node.functionName.value === "my_function") {
              const error = buildError(node.functionName.start.line, node.functionName.start.col,
                node.functionName.end.line, node.functionName.end.col,
                "Do not use function_name", "WARNING", "BEST_PRACTICE");
              const edit = buildEditUpdate(node.functionName.start.line, node.functionName.start.col,
                node.functionName.end.line, node.functionName.end.col,
                "myFunction");
              const fix = buildFix("use myFunction", [edit]);
              addError(error.addFix(fix));
            }
          }
        }
                        """;

    @Test
    @DisplayName("ensure the error variable is defined in GraphQL")
    public void testFunctionCallWithinVariableAssignment() throws Exception {
        JAVASCRIPT_TYPESCRIPT.forEach(l -> {
            logger.info("Running test with language: " + l);
            Response response = executeTest("bla.js", codeWithError1, l, ruleCode, "errorUseQuery", RuleType.AST_CHECK, EntityChecked.FUNCTION_CALL, null, true);
            logger.info(response.toString());
            assertEquals(1, response.ruleResponses.size());
            assertEquals(2, response.ruleResponses.get(0).violations.size());
        });
    }


    @Test
    @DisplayName("ensure the error variable is defined in GraphQL")
    public void testFunctionCallAlone() throws Exception {
    JAVASCRIPT_TYPESCRIPT.forEach(
        l -> {
          logger.info("Running test with language: " + l);
          Response response = executeTest("bla.js", codeWithError2, l, ruleCode, "errorUseQuery", RuleType.AST_CHECK, EntityChecked.FUNCTION_CALL, null, true);
          logger.info(response.toString());
          assertEquals(1, response.ruleResponses.size());
          assertEquals(2, response.ruleResponses.get(0).violations.size());
        });
    }
}
