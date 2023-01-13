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


public class RuleOfHooksTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(RuleOfHooksTest.class);


    String codeWithError = """
        const myComponent = () => {
          const { data, loading } = useQuery();
         
          if (loading) {
            return (<div></div>);
          }
         
          // invalid
          useEffect(() => {
            // do something
          }, [data]);
         
          return (<div>data.example</div>);
        }
        """;


    String codeWithoutError = """
        const myComponent = () => {
          const { data, loading } = useQuery();
         
            // invalid
          useEffect(() => {
            // do something
          }, [data]);
         
          if (loading) {
            return (<div></div>);
          }
         

          return (<div>data.example</div>);
        }
        """;

    String ruleCode = """
        function visit(node, filename, code) {
          const REACT_HOOKS_NAMES = [
            "useState",
            "useEffect",
            "useContext",
            "useReducer",
            "useCallback",
            "useRef",
            "useMemo"
          ]

          const flagHook = (element) => {
            const error = buildError(element.start.line, element.start.col,
              element.end.line, element.end.col,
              "hook should not be placed after a if that returns", "WARNING", "BEST_PRACTICE");
            addError(error);
          }


          // is there a hook usage from this node?
          const containsHook = (element) => {
            if(!element) {
                return false
            }
            if (element.astType === "functioncall") {
              const name = element.functionName.value;
              return (REACT_HOOKS_NAMES.includes(name));
            }
            return false;
          }

          // indicate if we have a return statement from a given node
          const containsReturnStatement = (element) => {
            if (!element) {
              return false;
            }

            if (element.astType === "return") {
              return true;
            }
            if (element.astType === "sequence") {
              return element.elements.map((e) => {
                return containsReturnStatement(e);
              }).includes(true);
            }
            return false;
          };


          // do we have a if statement with a return inside
          const containsIfWithReturn = (element) => {
            if (!element || element === null) {
              return false;
            }

            if (element.astType === "sequence") {
              element.elements.forEach((e) => {
                if (containsIfWithReturn(e)) {
                  return true;
                }
              });
            }
            if (element.astType === "ifstatement") {
              return containsReturnStatement(element.statements) || containsReturnStatement(element.elseStatements);
            }
            return false;
          }

          // main entry point
          console.log(node.right.astType);
          console.log("here");
          if (node.right.astType === "functionexpression") {
            const functionContent = node.right.content;
            var hasIfWithReturn = false;
            console.log(functionContent.astType);
            if (functionContent.astType === "sequence") {
              console.log("INSIDE FUNCTION CONTENT");
              functionContent.elements.forEach(e => {
                console.log(e.astType);
                const isHook = containsHook(e);
                console.log(`isHook = ${isHook}`);
                console.log(`hasIfWithReturn = ${hasIfWithReturn}`);
                if (!hasIfWithReturn) {
                  const t = containsIfWithReturn(e);
                  if (t) {
                    hasIfWithReturn = true;
                  }
                }
                if (isHook && hasIfWithReturn) {
                  flagHook(e);
                }
              });
            }
          }
        }
        """;

    @Test
    @DisplayName("rules of hooks")
    public void testHookRule() throws Exception {
        JAVASCRIPT_TYPESCRIPT.forEach(l -> {
            logger.info("Running test with language: " + l);
            Response responseWithError = executeTest("bla.js", codeWithError, l, ruleCode, "rule-of-hooks", RULE_TYPE_AST, ENTITY_CHECKED_ASSIGNMENT, null, true);
            logger.info(responseWithError.toString());
            assertEquals(1, responseWithError.ruleResponses.size());
            assertEquals(1, responseWithError.ruleResponses.get(0).violations.size());


            Response responseWithoutError = executeTest("bla.js", codeWithoutError, l, ruleCode, "rule-of-hooks", RULE_TYPE_AST, ENTITY_CHECKED_ASSIGNMENT, null, true);
            logger.info(responseWithError.toString());
            assertEquals(1, responseWithoutError.ruleResponses.size());
            assertEquals(0, responseWithoutError.ruleResponses.get(0).violations.size());
        });

    }

}
