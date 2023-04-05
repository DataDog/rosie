package io.codiga.analyzer.ast.languages.typescript;

import io.codiga.model.ast.common.*;
import io.codiga.model.ast.javascript.JavaScriptHtmlElement;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptHtmlElementTransformation.transformTypeScriptHtmlElement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsxWithContentTest extends TypeScriptTestUtils {

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

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.HtmlElementContext.class);
        assertEquals(2, nodes.size());
        Optional<JavaScriptHtmlElement> elementOptional = transformTypeScriptHtmlElement(((TypeScriptParser.HtmlElementContext) nodes.get(0)), null);
        assertTrue(elementOptional.isPresent());
        JavaScriptHtmlElement element = elementOptional.get();
        assertEquals(1, element.content.length);
        assertEquals(AstElementTypes.EXPRESSION.label, element.content[0].astType);
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

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.HtmlElementContext.class);
        assertEquals(2, nodes.size());
        Optional<JavaScriptHtmlElement> elementOptional = transformTypeScriptHtmlElement(((TypeScriptParser.HtmlElementContext) nodes.get(0)), null);
        assertTrue(elementOptional.isPresent());
        JavaScriptHtmlElement element = elementOptional.get();
        assertEquals(1, element.content.length);
        assertEquals(AstElementTypes.EXPRESSION.label, element.content[0].astType);
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

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.HtmlElementContext.class);
        assertEquals(3, nodes.size());
        Optional<JavaScriptHtmlElement> elementOptional = transformTypeScriptHtmlElement(((TypeScriptParser.HtmlElementContext) nodes.get(0)), null);
        assertTrue(elementOptional.isPresent());
        JavaScriptHtmlElement element = elementOptional.get();
        assertEquals(1, element.content.length);
        assertEquals(AstElementTypes.IF_STATEMENT.label, element.content[0].astType);
        IfStatement ifStatement = (IfStatement) element.content[0];
        assertEquals("elements", ((AstString) ifStatement.condition).value);
        assertEquals("sequence", ifStatement.statements.astType);
        assertEquals(AstElementTypes.HTML_ELEMENT.label, ((Sequence) ifStatement.statements).elements[0].astType);
        assertEquals("sequence", ifStatement.elseStatements.astType);
        assertEquals(AstElementTypes.HTML_ELEMENT.label, ((Sequence) ifStatement.elseStatements).elements[0].astType);
    }
}
