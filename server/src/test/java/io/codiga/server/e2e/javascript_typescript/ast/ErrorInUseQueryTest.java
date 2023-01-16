package io.codiga.server.e2e.javascript_typescript.ast;

import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.constants.Languages.ENTITY_CHECKED_ASSIGNMENT;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ErrorInUseQueryTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(ErrorInUseQueryTest.class);


    String codeWithEror = """
        const {
          data: cookbookData,
          loading: cookbookLoading
        } = useQuery(GET_PUBLIC_COOKBOOK_BY_ID, {
          skip: tmpCookbookId == undefined || mode === RecipesMode.PRIVATE_MODE,
          variables,
        });""";


    String ruleCode = """
                
        function visit(node, filename, code) {
          if (!node.right.astType === "functioncall" || !node.right.functionName || !node.right.functionName.value) {
            return;
          }

          if ((node.right.functionName.value !== "useQuery") && (node.right.functionName.value !== "useMutation")) {
            return;
          }
          if (!node.left.astType === "object" || !node.left.elements) {
            return;
          }

          const els = node.left.elements;
          els.forEach((e) => {
            console.log(e);
          });

          const hasErrorElement = els.filter(e => (e.value && e.value.astType === "string" && e.value.value && e.value.value === "error") || (e.name && e.name.astType === "string" && e.name.value === "error")).length > 0;
          console.log(hasErrorElement);

          if (!hasErrorElement) {
            const error = buildError(node.left.start.line, node.left.start.col, node.left.end.line, node.left.end.col,
              "Must catch potential error when using GraphQL Apollo", "WARNING", "BEST_PRACTICE");
            const edit = buildEditAdd(node.left.end.line, node.left.end.col - 1, ", error");
            const fix = buildFix("add error variable", [edit]);
            addError(error.addFix(fix));
          }
        }
                """;

    @Test
    @DisplayName("ensure the error variable is defined in GraphQL")
    public void testUseQueryHasError() throws Exception {
        JAVASCRIPT_TYPESCRIPT.forEach(l -> {
            logger.info("Running test with language: " + l);
            Response response = executeTest("bla.js", codeWithEror, l, ruleCode, "errorUseQuery", RULE_TYPE_AST, ENTITY_CHECKED_ASSIGNMENT, null, true);
            logger.info(response.toString());
            assertEquals(1, response.ruleResponses.size());
            assertEquals(1, response.ruleResponses.get(0).violations.size());
        });

    }

}
