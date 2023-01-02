package io.codiga.server.e2e.typescript.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.constants.Languages.ENTITY_CHECKED_HTML_ELEMENT;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsxArrayIndexTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(JsxArrayIndexTest.class);


    String codeWithError = """
        class SummaryContainer extends React.Component {
          constructor(props) {
            super(props);

            this.state = {
              subject: 'Summary Container!!'
            };
          }

          render() {
            things.map((thing, index) => (
              <Hello key={index} />
            ));
          }
        }""";


    String codeWithNoError = """
        class SummaryContainer extends React.Component {
          constructor(props) {
            super(props);

            this.state = {
              subject: 'Summary Container!!'
            };
          }

          render() {
            things.map((thing, index) => (
              <Hello key="my-super-key" />
            ));
          }
        }""";


    String codeWithNoError2 = """
        class SummaryContainer extends React.Component {
          constructor(props) {
            super(props);

            this.state = {
              subject: 'Summary Container!!'
            };
          }

          render() {
            things.map((thing, index) => (
              <Hello key={plop} />
            ));
          }
        }""";


    String ruleCode = """
        function visit(node) {
            // check if the node has attributes            
            if(node.attributes && node.attributes.length > 0){
                // check if the nodes has an attribute "key"
                if(node.attributes && node.attributes.filter(a => a.name && a.name.value && a.name.value === "key").length > 0) {
                    const keyAttribute = node.attributes.filter(a => a.name && a.name.value && a.name.value === "key")[0];
                    
                    // Check the value passed in the attribute
                    if(keyAttribute && keyAttribute.value && keyAttribute.value.elements && keyAttribute.value.elements.length > 0 && keyAttribute.value.elements[0].value) {
                        const keyValue = keyAttribute.value.elements[0].value;
                        
                        const currentFunctionCall = node.context.currentFunctionCall;

                        // check if we are in a function call
                        if (currentFunctionCall && currentFunctionCall.arguments.values.length === 1 && currentFunctionCall.functionName && currentFunctionCall.functionName.name &&  currentFunctionCall.functionName.name.value && currentFunctionCall.functionName.name.value === "map") {
                        
                            // get the function call argument that is an anonymous function
                            const arg = currentFunctionCall.arguments.values[0].value;
                            
                            // check this is a function definition
                            if(arg.astType === "functiondefinition") {
                                // check it has two argument
                                if (arg.parameters && arg.parameters.values && arg.parameters.values.length === 2) {
                                    
                                    // get the second argument name
                                    const secondArgumentName = arg.parameters.values[1].name.value;
                                    
                                    // if the second argument of map is the same value as the key property, raise an issue
                                    if(secondArgumentName === keyValue) {
                                        const error = buildError(keyAttribute.start.line, keyAttribute.start.col, keyAttribute.end.line, keyAttribute.end.col, "using index array as key", "CRITICAL", "SAFETY");
                                        addError(error);
                                    }
                                }
                            }
                        
                        
                        }
                        
                        

                    }
                }
                
            }
        }
        """;

    @Test
    @DisplayName("cannot use index as a key - raise error")
    public void testArrayKeyError() throws Exception {
        Response response = executeTest("bla.ts", codeWithError, Language.TYPESCRIPT, ruleCode, "index-as-key", RULE_TYPE_AST, ENTITY_CHECKED_HTML_ELEMENT, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(12, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(14, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(12, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(25, response.ruleResponses.get(0).violations.get(0).end.col);
    }

//    @Test
//    @DisplayName("cannot use index as a key - no error")
//    public void testArrayKeyNoError() throws Exception {
//        Response response = executeTest("bla.ts", codeWithNoError, Language.TYPESCRIPT, ruleCode, "index-as-key", RULE_TYPE_AST, ENTITY_CHECKED_HTML_ELEMENT, null, true);
//        logger.info(response.toString());
//        assertEquals(1, response.ruleResponses.size());
//        assertEquals(0, response.ruleResponses.get(0).violations.size());
//    }
//
//    @Test
//    @DisplayName("cannot use index as a key - no error2")
//    public void testArrayKeyNoError2() throws Exception {
//        Response response = executeTest("bla.ts", codeWithNoError2, Language.TYPESCRIPT, ruleCode, "index-as-key", RULE_TYPE_AST, ENTITY_CHECKED_HTML_ELEMENT, null, true);
//        logger.info(response.toString());
//        assertEquals(1, response.ruleResponses.size());
//        assertEquals(0, response.ruleResponses.get(0).violations.size());
//    }

}
