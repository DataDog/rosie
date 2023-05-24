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

public class IgnoreRuleTest extends E2EBase {


    private static final Logger logger = LoggerFactory.getLogger(IgnoreRuleTest.class);

    String codeWithOneError = """
        // codiga-disable
        const valid = false;
        const invalid = true;
                            """;

    String codeWithTwoErrors = """
        const valid = false;
        const invalid = true;""";

    String ruleCode = """
        /**
         * what's the minimum length of variable names that this should run on
         */
        const MINIMUM_LENGTH = 4;
                
        /**
         * what a boolean assignment should start with
         */
        const BOOLEAN_ASSIGNMENT_STARTERS = ["is", "has", "can"];
                
        /**
         * check whether the left-side of the assignment
         * has a boolean type naming
         */
        function checkLeftSideBooleanNaming(value) {
          let isBooleanNaming = false;
          for (const booleanAssignmentStarter of BOOLEAN_ASSIGNMENT_STARTERS) {
            if (value.startsWith(booleanAssignmentStarter)) {
              isBooleanNaming = true;
              break;
            }
          }
          return isBooleanNaming;
        }
                
        /**
         * check whether the right-side of the assignment is a boolean
         */
        function checkRightSideForBoolean(value) {
          if (value === "true" || value === "false") {
            return true;
          } else {
            return false;
          }
        }
                
        /**
         * check whether the right-side of the assignment is a functioncall
         */
        function checkRightSideForFunction(astType) {
          if (astType === "functioncall") {
            return true;
          } else {
            return false;
          }
        }
                
        /**
         * concatenates the starter and value (pascalCase)
         */
        function editValue(value, starter) {
          return `${starter}${value.charAt(0).toUpperCase() + value.substring(1)}`;
        }
                
        /**
         * handles all the logic when Codiga hits an assignment in file's AST
         */
        function visit(node, filename, code) {
          if (!node || !node.left || !node.right) return;
                
          const isLeftSideBooleanNaming = checkLeftSideBooleanNaming(node.left.value);
          const isRightSideABoolean = checkRightSideForBoolean(node.right.value);
          const isRightSideAFunction = checkRightSideForFunction(node.right.astType);
                
          if (node.left.value.length < MINIMUM_LENGTH) return;
                
          /**
           * if the assignment has boolean type naming and the value
           * isn't a boolean show a warning that it's not standard
           */
          if (
            isLeftSideBooleanNaming &&
            !isRightSideABoolean &&
            !isRightSideAFunction
          ) {
            const error = buildError(
              node.left.start.line,
              node.left.start.col,
              node.left.end.line,
              node.left.end.col,
              "Your naming indicates this variable is a boolean.",
              "WARNING",
              "BEST_PRACTICES"
            );
            addError(error);
          }
                
          /**
           * if the assignment doesn't have boolean type naming, but the
           * value is a boolean, suggest fixes for the assignment.
           */
          if (!isLeftSideBooleanNaming && isRightSideABoolean) {
            const error = buildError(
              node.left.start.line,
              node.left.start.col,
              node.left.end.line,
              node.left.end.col,
              "Your variable naming should indicate it's a boolean.",
              "WARNING",
              "BEST_PRACTICES"
            );
                
            const oldValue = node.left.value;
                
            const edits = BOOLEAN_ASSIGNMENT_STARTERS.map((starter) => {
              const newValue = editValue(oldValue, starter);
              return [
                buildEditUpdate(
                  node.left.start.line,
                  node.left.start.col,
                  node.left.end.line,
                  node.left.end.col,
                  editValue(oldValue, starter)
                ),
              ];
            });
                
            const isFix = buildFix(`naming with: is`, edits[0]);
            const hasFix = buildFix(`naming with: has`, edits[1]);
            const canFix = buildFix(`naming with: can`, edits[2]);
                
            addError(error.addFix(isFix).addFix(hasFix).addFix(canFix));
          }
        }
                """;

    @Test
    @DisplayName("ignore rules when comment is here")
    public void testIgnoreRules() throws Exception {
    JAVASCRIPT_TYPESCRIPT.forEach(
        l -> {
          logger.info("Running test with language: " + l);
          Response responseOneError = executeTest("bla.js", codeWithOneError, l, ruleCode, "no-child-as-prop", RuleType.AST_CHECK, EntityChecked.ASSIGNMENT, null, true);
          logger.info(responseOneError.toString());
          assertEquals(1, responseOneError.ruleResponses.size());
          assertEquals(1, responseOneError.ruleResponses.get(0).violations.size());

          Response responseTwoErrors = executeTest("bla.js", codeWithTwoErrors, l, ruleCode, "no-child-as-prop", RuleType.AST_CHECK, EntityChecked.ASSIGNMENT, null, true);
          logger.info(responseTwoErrors.toString());
          assertEquals(1, responseTwoErrors.ruleResponses.size());
          assertEquals(2, responseTwoErrors.ruleResponses.get(0).violations.size());
        });
    }
}
