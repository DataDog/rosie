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

public class HtmlElementTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(HtmlElementTest.class);


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
              <ul id="js-outcome-summary-container-{$objectType}-{$objectId}"
                  className="js-outcome-summary-container j-outcome-summary-container js-ed-{$objectType}-{$objectId}"
                  data-object-id="{$objectId}"
                  role="group">
              </ul>
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
              <ul id="js-outcome-summary-container-{$objectType}-{$objectId}"
                  className="js-outcome-summary-container j-outcome-summary-container js-ed-{$objectType}-{$objectId}"
                  data-object-id="{$objectId}"
                  aria-label="{i18nText('outcomes.summaryContainer.ariaLabel')}"
                  role="group">
              </ul>
            );
          }
        }""";


    String ruleCode = """
        function visit(node) {            
            if(node.attributes && node.attributes.length > 0){
                
                if(node.attributes && node.attributes.filter(a => a.name && a.name.value && a.name.value === "aria-label").length === 0) {
                    const error = buildError(node.tag.start.line, node.tag.start.col, node.tag.end.line, node.tag.end.col, "should have aria-label", "CRITICAL", "SAFETY");
                    addError(error);
                }
                
            }
        }
        """;

    @Test
    @DisplayName("check aria-label, report error")
    public void testHtmlElementNoError() throws Exception {
        Response response = executeTest("bla.tsx", codeWithError, Language.TYPESCRIPT, ruleCode, "check-aria-element", RuleType.AST_CHECK, EntityChecked.HTML_ELEMENT, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(12, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(8, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(12, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(10, response.ruleResponses.get(0).violations.get(0).end.col);
    }

    @Test
    @DisplayName("check aria-label, no error")
    public void testHtmlElementError() throws Exception {
        Response response = executeTest("bla.tsx", codeWithoutError, Language.TYPESCRIPT, ruleCode, "check-aria-element", RuleType.AST_CHECK, EntityChecked.HTML_ELEMENT, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
    }
}
