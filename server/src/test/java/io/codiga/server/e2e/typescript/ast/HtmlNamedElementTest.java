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


public class HtmlNamedElementTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(HtmlNamedElementTest.class);

    String code = """
        // Layout.jsx

        export { Header, Content, Footer }

        // App.jsx
        import * as Layout from "./Layout";

        return (
         <div>

           <Layout.Header user='Daniel' />
           <Layout.Content color='purple' />
           <Layout.Footer />
         </div>
        )

        // look at the 5th and 6th console results
        """;


    String ruleCode = """
        function visit(node) {
            if(node && node.tag && node.tag.value){
                   console.log(node.tag.value);

                    const error = buildError(node.tag.start.line, node.tag.start.col, node.tag.end.line, node.tag.end.col, "foo", "CRITICAL", "SAFETY");
                    addError(error);
                
            }
        }
        """;

    @Test
    @DisplayName("use component with different tags")
    public void testHtmlElementNoError() throws Exception {
        Response response = executeTest("bla.tsx", code, Language.TYPESCRIPT, ruleCode, "multi-element", RULE_TYPE_AST, ENTITY_CHECKED_HTML_ELEMENT, null, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(4, response.ruleResponses.get(0).violations.size());
    }


}
