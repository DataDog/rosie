package io.codiga.server.e2e.javascript.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.constants.Languages.ENTITY_CHECKED_ASSIGNMENT;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class HookRuleTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(HookRuleTest.class);


    String codeWithError = """
        const [state, setter] = useState("");
        const [state, updateState] = useState("");
        const [state, deleteState] = useState("");
        const [state, removeState] = useState("");""";


    String ruleCode = """
        /**
         * is the function name useState
         */
        function checkIfUseState(value) {
          if (value === "useState") {
            return true;
          } else {
            return false;
          }
        }

        /**
         * used to check if the right side of an assignment is either:
         * React.useState
         * useState
         */
        function checkIfReactUseStateHook(right) {
          if (!right.functionName) return false;
          if (right.functionName.parent?.value === "React") {
            if (checkIfUseState(right.functionName.name?.value)) {
              return true;
            }
          }
          if (checkIfUseState(right.functionName.value)) {
            return true;
          }
          return false;
        }

        /**
         *
         */
        function checkIfValidSetter(value) {
          if (!value || value.length < 5) return false;
          const isPascalCase = value[3].toUpperCase() === value[3];
          if (value.startsWith("set") && isPascalCase) {
            return true;
          } else {
            return false;
          }
        }

        /**
         * handles all the logic when Codiga hits an assignment in file's AST
         */
        function visit(node, filename, code) {
          if (!node || !node.left || !node.right) return;

          const isReactUseStateHook = checkIfReactUseStateHook(node.right);
          if (!isReactUseStateHook) return;

          /**
           * the left side of the assignment isn't destructured
           */
          if (!node.left.elements) {
            const notDestructuredError = buildError(
              node.left.start.line,
              node.left.start.col,
              node.left.end.line,
              node.left.end.col,
              "useState call is not destructured into value + setter pair",
              "WARNING",
              "BEST_PRACTICES"
            );

            const notDestructuredEdit = [
              buildEditUpdate(
                node.left.start.line,
                node.left.start.col,
                node.left.end.line,
                node.left.end.col,
                `[state, setState]`
              ),
            ];

            const notDestructuredFix = buildFix(`destructure`, notDestructuredEdit);

            addError(notDestructuredError.addFix(notDestructuredFix));
            return;
          }

          /**
           * less than the standard 2 (getter + setter) values were destructured
           */
          if (node.left.elements.length < 2) {
            const badDestructing = buildError(
              node.left.start.line,
              node.left.start.col,
              node.left.end.line,
              node.left.end.col,
              `Destructure both value + setter from useState`,
              "WARNING",
              "BEST_PRACTICES"
            );

            addError(badDestructing);
            return;
          }

          /**
           * the setter naming doesn't follow convention
           */
          const setterNode = node.left.elements[1];
          if (!setterNode?.value) return;
          const isValidSetter = checkIfValidSetter(setterNode.value);
          if (isValidSetter) return;

          const setterNamingError = buildError(
            setterNode.start.line,
            setterNode.start.col,
            setterNode.end.line,
            setterNode.end.col,
            `Your setter does not follow the standard (setThing) naming convention`,
            "WARNING",
            "BEST_PRACTICES"
          );

          addError(setterNamingError);
        }
                """;

    @Test
    @DisplayName("check hook rule")
    public void testHookRule() throws Exception {
        Response response = executeTest("bla.js", codeWithError, Language.JAVASCRIPT, ruleCode, "hook-use-state", RULE_TYPE_AST, ENTITY_CHECKED_ASSIGNMENT, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(4, response.ruleResponses.get(0).violations.size());

    }

}
