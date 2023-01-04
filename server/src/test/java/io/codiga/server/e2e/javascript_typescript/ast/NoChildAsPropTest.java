package io.codiga.server.e2e.javascript_typescript.ast;

import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.constants.Languages.ENTITY_CHECKED_HTML_ELEMENT;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NoChildAsPropTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(NoChildAsPropTest.class);


    String codeWithError = """
        function App(){
        	return (
            <div>
            	<Header children="App Title" />
        			<Content children={"Some app content"} />
        			{/* this won't work */}
        			<Content children={<p>Some app content</p>} />
            	<Footer color="purple"/>
            </div>
          )
        }""";


    String ruleCode = """
        /**
         * handles all the logic when Codiga hits an assignment in file's AST
         */
        function visit(node, filename, code) {
            if (!node.tag) return;
            if (!node.attributes) return;
            if (!node.attributes.length === 0) return;
            // if the tag does't start with a capital, skip it
            if (node.tag.value[0].toUpperCase() !== node.tag.value[0]) return;

            node.attributes.forEach((attribute) => {
                if (attribute?.name?.value === "children") {
                    const error = buildError(
                        attribute.name.start.line,
                        attribute.name.start.col,
                        attribute.name.end.line,
                        attribute.name.end.col,
                        "Children should always be actual children, not passed in as a prop.",
                        "WARNING",
                        "BEST_PRACTICES"
                    )
                    addError(error)
                }
            });
        }""";

    @Test
    @DisplayName("noChildAsProps")
    public void testHookRule() throws Exception {
        JAVASCRIPT_TYPESCRIPT.forEach(l -> {
            logger.info("Running test with language: " + l);
            Response response = executeTest("bla.js", codeWithError, l, ruleCode, "no-child-as-prop", RULE_TYPE_AST, ENTITY_CHECKED_HTML_ELEMENT, null, true);
            logger.info(response.toString());
            assertEquals(1, response.ruleResponses.size());
            assertEquals(3, response.ruleResponses.get(0).violations.size());
        });

    }

}
