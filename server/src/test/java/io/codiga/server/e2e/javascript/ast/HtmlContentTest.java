package io.codiga.server.e2e.javascript.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlContentTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(HtmlContentTest.class);


    String codeWithError = """
        class SummaryContainer extends React.Component {
          constructor(props) {
            super(props);

            this.state = {
              subject: 'Summary Container!!'
            };
          }

          render() {
            return (
              <div>
                hello
              </div>
            );
          }
        }""";


    String codeWithoutError = """
        class SummaryContainer extends React.Component {
          constructor(props) {
            super(props);

            this.state = {
              subject: 'Summary Container!!'
            };
          }

          render() {
            return (
              <div>
                world
              </div>
            );
          }
        }""";


    String ruleCode = """
        function visit(node) {            
            if (node.htmlChildren && node.htmlChildren.length > 0 && 
                node.htmlChildren[0].astType === "htmldata" &&
                node.htmlChildren[0].value.value === "hello") {
                const n = node.htmlChildren[0].value;
                const editChangeContent = buildEditUpdate(n.start.line, n.start.col, n.end.line, n.end.col, "world");
                const fix = buildFix("use world", [editChangeContent]);
                
                const error = buildError(n.start.line, n.start.col, 
                n.end.line, n.end.col, "update hello to world", "CRITICAL", "SAFETY");
                addError(error.addFix(fix)); 
            }       
        }
        """;

    @Test
    @DisplayName("check aria-label, report error")
    public void testHtmlElementNoError() throws Exception {
        Response response = executeTest("bla.js", codeWithError, Language.JAVASCRIPT, ruleCode, "check-aria-element", RuleType.AST_CHECK, EntityChecked.HTML_ELEMENT, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(13, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(9, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(13, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(14, response.ruleResponses.get(0).violations.get(0).end.col);
    }

    @Test
    @DisplayName("check aria-label, no error")
    public void testHtmlElementError() throws Exception {
        Response response = executeTest("bla.js", codeWithoutError, Language.JAVASCRIPT, ruleCode, "check-aria-element", RuleType.AST_CHECK, EntityChecked.HTML_ELEMENT, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
    }
}
