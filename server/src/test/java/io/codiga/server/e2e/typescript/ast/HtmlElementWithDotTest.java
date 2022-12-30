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


public class HtmlElementWithDotTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(HtmlElementWithDotTest.class);


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
              <Foo.Bar />
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
              <FooBar />
            );
          }
        }""";


    String ruleCode = """
        function visit(node) {            
            console.log(node.tag.value);
            if(node.tag && node.tag.value && node.tag.value === "Foo.Bar") {
                const editComponentName = buildEditUpdate(node.tag.start.line, node.tag.start.col, node.tag.end.line, node.tag.end.col, "FooBar");
                const fix = buildFix("use FooBar instead of Foo.Bar", [editComponentName]);

                const error = buildError(node.tag.start.line, node.tag.start.col, node.tag.end.line, node.tag.end.col, "do not use Foo.Bar", "CRITICAL", "SAFETY");
                addError(error.addFix(fix));
            }
        }
        """;

    @Test
    @DisplayName("do not use Foo.Bar")
    public void testHtmlElementNoError() throws Exception {
        Response response = executeTest("bla.js", codeWithError, Language.TYPESCRIPT, ruleCode, "no-foo-dor-bar", RULE_TYPE_AST, ENTITY_CHECKED_HTML_ELEMENT, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(12, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(8, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(12, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(15, response.ruleResponses.get(0).violations.get(0).end.col);

        assertEquals(codeWithoutError, applyFix(codeWithError, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }


}
