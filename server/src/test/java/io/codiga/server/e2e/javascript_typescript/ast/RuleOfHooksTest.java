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


    String codeWithError1 = """
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

    String codeWithError2 = """
        const useMyCustomHook = (isReady) => {
          if (!isReady){
        		return;
        	}
         
          // invalid
          const [state, setState] = useState();
         
          // invalid
          const myCallback = useCallback(() => {
            // invalid
            useEffect(function myEffect() {
              return;
            });
          }, []);
         
          // invalid
          const value = useMemo(() => ({ state, myCallback}), []);
         
          return value;
        }
        """;


    String codeWithError3 = """
        const MyComp = () => {
          const [payload, setPayload] = useState();
          const [result, setResult] = useState();
         
          const handleClick = () => {
            // invalid
            setPayload(useContext(MyContext));
          };
         
          useEffect(() => {
            fetch(payload).then((res) => {
              setResult(res);
            });
          }, [payload]);

          return <button onClick={handleClick}>my button</button>;
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

          const flagHook = (element, message) => {
            const error = buildError(element.start.line, element.start.col,
              element.end.line, element.end.col,
              message, "WARNING", "BEST_PRACTICE");
            addError(error);
          }


          // is there a hook usage from this node?
          const containsHook = (element) => {
            if (!element) {
              return false;
            }
            if (element.astType === "functioncall") {
              const name = element.functionName.value;
              return (REACT_HOOKS_NAMES.includes(name));
            }
            if (element.astType === "sequence") {
              return element.elements.map((e) => containsHook(e)).includes(true);
            }
            if (element.astType === "variabledeclaration") {
              return containsHook(element.value);
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


          const checkHookUsageInsideFunctionDefinition = (element) => {

            // check if a function content ever calls a hook
            const checkFunctionContent = (element) => {
              if (element.astType === "functionexpression") {
                checkFunctionContent(element.content);
              }
              if (element.astType === "sequence") {
                element.elements.forEach((e) => checkFunctionContent(e));
              }

              if (element.astType === "functioncall") {
                if (element.arguments && element.arguments.values) {
                  element.arguments.values.forEach((v) => {
                    checkFunctionContent(v.value);
                  });
                }
              }

              if (containsHook(element)) {
                flagHook(element, "hook should not be called inside inner functions");
              }

            };


            if (!element) {
              return;
            }
            if (element.astType === "variabledeclaration") {
              checkHookUsageInsideFunctionDefinition(element.value);
            }

            if (element.astType === "functionexpression") {
              checkFunctionContent(element);
            }
          };



          // main entry point
          if (node.right.astType === "functionexpression") {

            const functionContent = node.right.content;
            var hasIfWithReturn = false;
            if (functionContent.astType === "sequence") {
              /**
               * Check for potential use of a hook inside a function there
               */
              functionContent.elements.forEach(e => {
                checkHookUsageInsideFunctionDefinition(e);
              })
              /**
               * Check for each element if we found a if with a return inside.
               * If there is a if with a return, any subsequent hook will have an error
               */
              functionContent.elements.forEach(e => {

                const isHook = containsHook(e);
                if (!hasIfWithReturn) {
                  const t = containsIfWithReturn(e);
                  if (t) {
                    hasIfWithReturn = true;
                  }
                }
                if (isHook && hasIfWithReturn) {
                  flagHook(e, "hook should not be placed after a if that returns");
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
            Response responseWithError1 = executeTest("bla.js", codeWithError1, l, ruleCode, "rule-of-hooks", RULE_TYPE_AST, ENTITY_CHECKED_ASSIGNMENT, null, true);
            logger.info(responseWithError1.toString());
            assertEquals(1, responseWithError1.ruleResponses.size());
            assertEquals(1, responseWithError1.ruleResponses.get(0).violations.size());


            Response responseWithError2 = executeTest("bla.js", codeWithError2, l, ruleCode, "rule-of-hooks", RULE_TYPE_AST, ENTITY_CHECKED_ASSIGNMENT, null, true);
            logger.info(responseWithError2.toString());
            assertEquals(1, responseWithError2.ruleResponses.size());
            assertEquals(3, responseWithError2.ruleResponses.get(0).violations.size());

            Response responseWithError3 = executeTest("bla.js", codeWithError3, l, ruleCode, "rule-of-hooks", RULE_TYPE_AST, ENTITY_CHECKED_ASSIGNMENT, null, true);
            logger.info(responseWithError3.toString());
            assertEquals(1, responseWithError3.ruleResponses.size());
            assertEquals(1, responseWithError3.ruleResponses.get(0).violations.size());

            Response responseWithoutError = executeTest("bla.js", codeWithoutError, l, ruleCode, "rule-of-hooks", RULE_TYPE_AST, ENTITY_CHECKED_ASSIGNMENT, null, true);
            logger.info(responseWithError1.toString());
            assertEquals(1, responseWithoutError.ruleResponses.size());
            assertEquals(0, responseWithoutError.ruleResponses.get(0).violations.size());
        });

    }

}
