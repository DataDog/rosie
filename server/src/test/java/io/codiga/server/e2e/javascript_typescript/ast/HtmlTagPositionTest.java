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

public class HtmlTagPositionTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(HtmlTagPositionTest.class);


    String code1WithError = """
        function App(){
        	return (
            <div>
            	<Header children="App Title" />
            </div>
          )
        }""";

    String code2WithError = """
        function App(){
        	return (
            <div>
            	<Header> children="App Title" </Header>
            </div>
          )
        }""";

    String code1Fixed1 = """
        function App(){
        	return (
            <div>
            	plop children="App Title" />
            </div>
          )
        }""";

    String code1Fixed2 = """
        function App(){
        	return (
            <div>
            	<Header children="App Title" plop
            </div>
          )
        }""";


    String code2Fixed1 = """
        function App(){
        	return (
            <div>
            	plop children="App Title" </Header>
            </div>
          )
        }""";

    String code2Fixed2 = """
        function App(){
        	return (
            <div>
            	<Header> children="App Title" plop
            </div>
          )
        }""";


    String ruleCode = """
        /**
         * handles all the logic when Codiga hits an assignment in file's AST
         */
        function visit(node, filename, code) {
            if(node.tag && node.tag.value === "Header") {
                const openingTag = node.openingTag;
                const closingTag = node.closingTag;

                const edit1 = buildEditUpdate(openingTag.start.line, openingTag.start.col, openingTag.end.line, openingTag.end.col, "plop");

                const edit2 = buildEditUpdate(closingTag.start.line, closingTag.start.col, closingTag.end.line, closingTag.end.col, "plop");

                const fix1 = buildFix("use plop", [edit1]);
                const fix2 = buildFix("use plop", [edit2]);
                const error = buildError(node.tag.start.line, node.tag.start.col, node.tag.end.line, node.tag.end.col, "use plop for your tag", "CRITICAL", "SAFETY");
                addError(error.addFix(fix1).addFix(fix2));
                
            }
        }""";

    @Test
    @DisplayName("htmlTagPosition")
    public void testHtmlTagPosition() throws Exception {
    JAVASCRIPT_TYPESCRIPT.forEach(
        l -> {
          logger.info("Running test with language: " + l);
          Response response = executeTest("bla.js", code1WithError, l, ruleCode, "htmltagposition", RuleType.AST_CHECK, EntityChecked.HTML_ELEMENT, null, true);
          logger.info(response.toString());
          assertEquals(1, response.ruleResponses.size());
          assertEquals(1, response.ruleResponses.get(0).violations.size());
          assertEquals(code1Fixed1, applyFix(code1WithError, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
          assertEquals(code1Fixed2, applyFix(code1WithError, response.ruleResponses.get(0).violations.get(0).fixes.get(1)));

          response = executeTest("bla.js", code2WithError, l, ruleCode, "htmltagposition", RuleType.AST_CHECK, EntityChecked.HTML_ELEMENT, null, true);
          assertEquals(code2Fixed1, applyFix(code2WithError, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
          assertEquals(code2Fixed2, applyFix(code2WithError, response.ruleResponses.get(0).violations.get(0).fixes.get(1)));
        });
    }

}
