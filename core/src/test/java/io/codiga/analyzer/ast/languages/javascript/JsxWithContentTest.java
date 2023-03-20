package io.codiga.analyzer.ast.languages.javascript;

import io.codiga.model.ast.common.AstExpression;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.IfStatement;
import io.codiga.model.ast.common.Sequence;
import io.codiga.model.ast.javascript.JavaScriptHtmlElement;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.model.ast.common.AstElement.*;
import static io.codiga.parser.antlr.javascript.transformations.JavaScriptHtmlElementTransformation.transformJavaScriptHtmlElement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsxWithContentTest extends JavaScriptTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }


    @Test
    @DisplayName("Get Content with Expression - variance1")
    public void testContentJsxWithExpression1() {
        String code = """
            const ComponentOne = ({ elements }) => {
              return (
                            <div>
                                    {elements || <List elements={elements} />}
                            </div>
                    )
             }
             """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.HtmlElementContext.class);
        assertEquals(2, nodes.size());
        Optional<JavaScriptHtmlElement> elementOptional = transformJavaScriptHtmlElement(((JavaScriptParser.HtmlElementContext) nodes.get(0)), null);
        assertTrue(elementOptional.isPresent());
        JavaScriptHtmlElement element = elementOptional.get();
        assertEquals(1, element.content.length);
        assertEquals(AST_ELEMENT_TYPE_EXPRESSION, element.content[0].astType);
        AstExpression astExpression = (AstExpression) element.content[0];
        assertEquals("||", ((AstString) astExpression.operator).value);
        assertEquals("elements", ((AstString) astExpression.left).value);
        JavaScriptHtmlElement rightSide = (JavaScriptHtmlElement) ((Sequence) astExpression.right).elements[0];
        assertEquals("List", ((AstString) rightSide.tag).value);
    }

    @Test
    @DisplayName("Get Content with Expression - variance2")
    public void testContentJsxWithExpression2() {
        String code = """
            const ComponentOne = ({ elements }) => {
              return (
                            <div>
                                    {elements && <List elements={elements} />}
                            </div>
                    )
             }
             """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.HtmlElementContext.class);
        assertEquals(2, nodes.size());
        Optional<JavaScriptHtmlElement> elementOptional = transformJavaScriptHtmlElement(((JavaScriptParser.HtmlElementContext) nodes.get(0)), null);
        assertTrue(elementOptional.isPresent());
        JavaScriptHtmlElement element = elementOptional.get();
        assertEquals(1, element.content.length);
        assertEquals(AST_ELEMENT_TYPE_EXPRESSION, element.content[0].astType);
        AstExpression astExpression = (AstExpression) element.content[0];
        assertEquals("&&", ((AstString) astExpression.operator).value);
        assertEquals("elements", ((AstString) astExpression.left).value);
        JavaScriptHtmlElement rightSide = (JavaScriptHtmlElement) ((Sequence) astExpression.right).elements[0];
        assertEquals("List", ((AstString) rightSide.tag).value);
    }

    @Test
    @DisplayName("Get Content with Expression - variance3")
    public void testContentJsxWithExpression3() {
        String code = """
            const ComponentThree = ({ elements }) => {
              return (
                            <div>
                                    {elements
                                            ? <List elements={elements} />
                                            : <EmptyList />}
                            </div>
                    )
            }
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.HtmlElementContext.class);
        assertEquals(3, nodes.size());
        Optional<JavaScriptHtmlElement> elementOptional = transformJavaScriptHtmlElement(((JavaScriptParser.HtmlElementContext) nodes.get(0)), null);
        assertTrue(elementOptional.isPresent());
        JavaScriptHtmlElement element = elementOptional.get();
        assertEquals(1, element.content.length);
        assertEquals(AST_ELEMENT_IF_STATEMENT, element.content[0].astType);
        IfStatement ifStatement = (IfStatement) element.content[0];
        assertEquals("elements", ((AstString) ifStatement.condition).value);
        assertEquals("sequence", ifStatement.statements.astType);
        assertEquals(AST_ELEMENT_TYPE_HTML_ELEMENT, ((Sequence) ifStatement.statements).elements[0].astType);
        assertEquals("sequence", ifStatement.elseStatements.astType);
        assertEquals(AST_ELEMENT_TYPE_HTML_ELEMENT, ((Sequence) ifStatement.elseStatements).elements[0].astType);
    }

    @Test
    @DisplayName("Get content with a return with parenthesis")
    public void testReturnWithParenthesis() {
        String code = """
            const Index = () => {
              return (
                <>
                  <b>World</b>
                  {true && (
                    <div>
                      <Box w="full" as={CodeMirror} value={decodeBase64(rule.content)} />
                    </div>
                  )}
                </>
              );
            };
                        """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.HtmlElementContext.class);
        assertEquals(4, nodes.size());
        Optional<JavaScriptHtmlElement> elementOptional = transformJavaScriptHtmlElement(((JavaScriptParser.HtmlElementContext) nodes.get(0)), null);
        assertTrue(elementOptional.isPresent());
        JavaScriptHtmlElement element = elementOptional.get();
        assertEquals(2, element.content.length);
        assertEquals(AST_ELEMENT_TYPE_EXPRESSION, element.content[1].astType);
        AstExpression expression = (AstExpression) element.content[1];
        assertEquals("true", ((AstString) expression.left).value);
        assertEquals(AST_ELEMENT_TYPE_SEQUENCE, expression.right.astType);
        assertEquals(1, ((Sequence) expression.right).elements.length);
    }
}
