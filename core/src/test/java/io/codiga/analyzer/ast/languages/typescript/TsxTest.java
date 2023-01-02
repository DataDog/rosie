package io.codiga.analyzer.ast.languages.typescript;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.javascript.JavaScriptHtmlElement;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptHtmlElementTransformation.transformTypeScriptHtmlElement;
import static org.junit.jupiter.api.Assertions.*;

public class TsxTest extends TypeScriptTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }


    @Test
    @DisplayName("Get the HTML elements")
    public void testHtmlElement() {
        String code = """
            import React from 'react';

            /**
             * jive.outcomes.summary.summaryContainer
             */
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
                      data-object-type="{$objectType}"
                      data-object-id="{$objectId}"
                      aria-label="{i18nText('outcomes.summaryContainer.ariaLabel')}"
                      role="group">
                  </ul>
                );
              }
            }

            export default SummaryContainer;
                        """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.HtmlElementContext.class);
        for (ParseTree node : nodes) {
            Optional<JavaScriptHtmlElement> elementOptional = transformTypeScriptHtmlElement(((TypeScriptParser.HtmlElementContext) node), null);
            assertTrue(elementOptional.isPresent());
            JavaScriptHtmlElement element = elementOptional.get();
            assertEquals("ul", ((AstString) element.tag).value);
//            assertEquals(6, element.attributes.length);
//            assertEquals("id", element.attributes[0].name.value);
//            assertEquals("\"js-outcome-summary-container-{$objectType}-{$objectId}\"", ((AstString) element.attributes[0].value).value);
//            assertEquals("className", element.attributes[1].name.value);
//            assertEquals("\"js-outcome-summary-container j-outcome-summary-container js-ed-{$objectType}-{$objectId}\"", ((AstString) element.attributes[1].value).value);
//            assertEquals("data-object-type", element.attributes[2].name.value);
//            assertEquals("\"{$objectType}\"", ((AstString) element.attributes[2].value).value);
//            assertEquals("data-object-id", element.attributes[3].name.value);
//            assertEquals("\"{$objectId}\"", ((AstString) element.attributes[3].value).value);
//            assertEquals("aria-label", element.attributes[4].name.value);
//            assertEquals("\"{i18nText('outcomes.summaryContainer.ariaLabel')}\"", ((AstString) element.attributes[4].value).value);
//            assertEquals("role", element.attributes[5].name.value);
//            assertEquals("\"group\"", ((AstString) element.attributes[5].value).value);
        }
    }


    @Test
    @DisplayName("Get emtpy tags")
    public void testEmptyTag() {
        String code = """
            import React from 'react';

            /**
             * jive.outcomes.summary.summaryContainer
             */
            class SummaryContainer extends React.Component {
              constructor(props) {
                super(props);

                this.state = {
                  subject: 'Summary Container!!'
                };
              }

              render() {
                return (
                  <>
                    <div>
                    </div>
                    <div />
                  </>
                );
              }
            }

            export default SummaryContainer;
                        """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.HtmlElementContext.class);
        Optional<JavaScriptHtmlElement> elementOptional = transformTypeScriptHtmlElement(((TypeScriptParser.HtmlElementContext) nodes.get(0)), null);
        assertTrue(elementOptional.isPresent());
        JavaScriptHtmlElement element = elementOptional.get();
        assertNull(element.tag);
    }

    @Test
    @DisplayName("TSX with comments")
    public void testTsxWithComments() {
        String code = """
                // Layout.jsx

                export { Header, Content, Footer }

                // App.jsx
                import * as Layout from "./Layout";

                return (
                 <div>
                    {/*
                      These components don't return any .[naming] info
                      and they are missing the props
                    */}
                   <Layout.Header user='Daniel' />
                   <Layout.Content color='purple' />
                   <Layout.Footer />
                 </div>
                )

                // look at the 5th and 6th console results
            """;

        ParseTree root = parseCode(code);
        printTree(root);
        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.HtmlElementContext.class);
        nodes.forEach(c -> log.info(c.getText()));
        assertEquals(4, nodes.size());
    }
}
