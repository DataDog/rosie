package io.codiga.server.e2e.typescript.ast;

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

public class NestedHtmlElementTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(NestedHtmlElementTest.class);


    String codeWithError = """
        class SummaryContainer extends React.Component {
          render() {
            return (
              <div>
                  <ul>
                    <li>item</li>
                  </ul>
              </div>
            );
          }
        }""";

    String codeWithoutError = """
        class SummaryContainer extends React.Component {
          render() {
            return (
              <div>
                  <ul>
                    <p>item</p>
                  </ul>
              </div>
            );
          }
        }""";

    String ruleCode = """
        function visit(node) {            
            if(node.htmlChildren && node.htmlChildren.length > 0 &&
               node.htmlChildren[0].htmlChildren && node.htmlChildren[0].htmlChildren.length > 0){
               const n = node.htmlChildren[0].htmlChildren[0];
                
                if(n.tag && n.tag.value && n.tag.value === "li") {
                    const error = buildError(n.tag.start.line, n.tag.start.col, n.tag.end.line, n.tag.end.col, "li tag", "CRITICAL", "SAFETY");
                    addError(error);
                }
                
            }
        }
        """;

    @Test
    @DisplayName("error for li element")
    public void testHtmlElementNoError() throws Exception {
        Response response = executeTest("bla.js", codeWithError, Language.TYPESCRIPT, ruleCode, "check-aria-element", RuleType.AST_CHECK, EntityChecked.HTML_ELEMENT, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());

        response = executeTest("bla.js", codeWithoutError, Language.TYPESCRIPT, ruleCode, "check-aria-element", RuleType.AST_CHECK, EntityChecked.HTML_ELEMENT, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
    }

}
