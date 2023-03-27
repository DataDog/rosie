package io.codiga.analyzer.ast.languages.python.treesitter;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Sequence;
import io.codiga.model.ast.python.PythonClassDefinition;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.model.ast.common.AstElement.*;
import static io.codiga.parser.treesitter.python.transformation.ClassDeclarationTransformation.transformClassDefinition;
import static io.codiga.parser.treesitter.python.transformation.DecoratedDefinitionTransformation.transformDecoratedDefinition;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassDefToClassDefinitionTest extends PythonTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Map a simple class with no decorator or inheritance")
    public void testTransformClassDefinitionNoDecoratorNoInheritance() {
        String code = """
            class bar(): pass""";

        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.CLASS_DEFINITION.label);
        assertEquals(1, nodes.size());
        Optional<PythonClassDefinition> classDefinitionOptional = transformClassDefinition(nodes.get(0), parsingContext);
        assertTrue(classDefinitionOptional.isPresent());
        PythonClassDefinition classDefinition = classDefinitionOptional.get();

        assertEquals("bar", classDefinition.name.str);
        assertEquals(0, classDefinition.parentClasses.length);
        assertEquals(0, classDefinition.decorators.length);
    }

    @Test
    @DisplayName("Map a simple class with no decorator and a single inheritance")
    public void testTransformClassDefinitionNoDecoratorWithSingleInheritance() {
        String code = """
            class baz(foo):
                pass""";


        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.CLASS_DEFINITION.label);
        assertEquals(1, nodes.size());
        Optional<PythonClassDefinition> classDefinitionOptional = transformClassDefinition(nodes.get(0), parsingContext);
        assertTrue(classDefinitionOptional.isPresent());
        PythonClassDefinition classDefinition = classDefinitionOptional.get();
        assertEquals("baz", classDefinition.name.str);
        assertEquals(1, classDefinition.parentClasses.length);
        assertEquals("foo", classDefinition.parentClasses[0].value);
        assertEquals(0, classDefinition.decorators.length);
    }

    @Test
    @DisplayName("Map a simple class with no decorator and multiple inheritances")
    public void testTransformClassDefinitionNoDecoratorWithMultipleInheritances() {
        String code = """
            class baz(Class1, Class2):
                pass""";


        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.CLASS_DEFINITION.label);
        assertEquals(1, nodes.size());
        Optional<PythonClassDefinition> classDefinitionOptional = transformClassDefinition(nodes.get(0), parsingContext);
        assertTrue(classDefinitionOptional.isPresent());
        PythonClassDefinition classDefinition = classDefinitionOptional.get();

        assertEquals("baz", classDefinition.name.str);
        assertEquals(2, classDefinition.parentClasses.length);
        assertEquals("Class1", classDefinition.parentClasses[0].value);
        assertEquals("Class2", classDefinition.parentClasses[1].value);
        assertEquals(0, classDefinition.decorators.length);
    }

    @Test
    @DisplayName("Map a simple class with multiple decorators")
    public void testTransformClassDefinitionMultipleDecorators() {
        String code = """
            @decorator1
            @decorator2(bli = blo)
            class baz:
                pass""";

        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.DECORATED_DEFINITION.label);
        assertEquals(1, nodes.size());
        Optional<AstElement> classDefinitionOptional = transformDecoratedDefinition(nodes.get(0), parsingContext);
        assertTrue(classDefinitionOptional.isPresent());

        PythonClassDefinition pythonClassDefinition = (PythonClassDefinition) classDefinitionOptional.get();


        assertEquals("baz", pythonClassDefinition.name.str);
        assertEquals(0, pythonClassDefinition.parentClasses.length);
        assertEquals(2, pythonClassDefinition.decorators.length);

        assertEquals("decorator1", pythonClassDefinition.decorators[0].name.str);
        assertEquals(0, pythonClassDefinition.decorators[0].arguments.length);

        // TO FIX: decorator name
//        assertEquals("decorator2", pythonClassDefinition.decorators[1].name.str);
//        assertEquals(1, pythonClassDefinition.decorators[1].arguments.length);
//        assertEquals("blo", pythonClassDefinition.decorators[1].arguments[0].value.str);
//        assertEquals("bli", pythonClassDefinition.decorators[1].arguments[0].name.str);
    }

    @Test
    @DisplayName("Class with methods")
    public void testClassWithMethods() {
        String code = """
            class FooBar:
                def __init__(self):
                    pass


                def my_method(self, argument1):
                    pass""";

        Node rootNode = parseCode(code);
        io.codiga.analyzer.ast.utils.TreeSitterUtils.printTree(rootNode);

        TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
        List<Node> nodes = io.codiga.analyzer.ast.utils.TreeSitterUtils.getNodesFromType(rootNode, TreeSitterPythonTypes.CLASS_DEFINITION.label);
        assertEquals(1, nodes.size());
        Optional<PythonClassDefinition> classDefinitionOptional = transformClassDefinition(nodes.get(0), parsingContext);
        assertTrue(classDefinitionOptional.isPresent());
        PythonClassDefinition classDefinition = classDefinitionOptional.get();

        assertEquals(AST_ELEMENT_TYPE_CLASS_DEFINITION, classDefinition.astType);
        assertEquals(AST_ELEMENT_TYPE_SEQUENCE, classDefinition.content.astType);
        Sequence seq = (Sequence) classDefinition.content;
        assertEquals(2, seq.elements.length);
        assertEquals(AST_ELEMENT_TYPE_FUNCTION_DEFINITION, seq.elements[0].astType);
        assertEquals(AST_ELEMENT_TYPE_FUNCTION_DEFINITION, seq.elements[1].astType);
    }
}
