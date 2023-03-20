package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.common.Sequence;
import io.codiga.model.ast.python.PythonClassDefinition;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static io.codiga.model.ast.common.AstElement.*;
import static io.codiga.parser.antlr.python.transformations.ClassOrFuncDefTransformation.isClassDefinition;
import static io.codiga.parser.antlr.python.transformations.ClassOrFuncDefTransformation.transformClassOrFuncDefToClassDefinition;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassDefToClassDefinitionTest extends PythonTestUtils {

    private Logger log = Logger.getLogger("Test");

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

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Class_or_func_def_stmtContext.class);
        PythonParser.Class_or_func_def_stmtContext funcdefContext = (PythonParser.Class_or_func_def_stmtContext) exprNodes.get(0);
        assertTrue(isClassDefinition(funcdefContext));
        PythonClassDefinition pythonClassDefinition = transformClassOrFuncDefToClassDefinition(funcdefContext, null).get();
        assertEquals("bar", pythonClassDefinition.name.str);
        assertEquals(0, pythonClassDefinition.parentClasses.length);
        assertEquals(0, pythonClassDefinition.decorators.length);
    }

    @Test
    @DisplayName("Map a simple class with no decorator and a single inheritance")
    public void testTransformClassDefinitionNoDecoratorWithSingleInheritance() {
        String code = """
            class baz(foo):
                pass""";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Class_or_func_def_stmtContext.class);
        PythonParser.Class_or_func_def_stmtContext funcdefContext = (PythonParser.Class_or_func_def_stmtContext) exprNodes.get(0);
        assertTrue(isClassDefinition(funcdefContext));
        PythonClassDefinition pythonClassDefinition = transformClassOrFuncDefToClassDefinition(funcdefContext, null).get();
        assertEquals("baz", pythonClassDefinition.name.str);
        assertEquals(1, pythonClassDefinition.parentClasses.length);
        assertEquals("foo", pythonClassDefinition.parentClasses[0].value);
        assertEquals(0, pythonClassDefinition.decorators.length);
    }

    @Test
    @DisplayName("Map a simple class with no decorator and multiple inheritances")
    public void testTransformClassDefinitionNoDecoratorWithMultipleInheritances() {
        String code = """
            class baz(Class1, Class2):
                pass""";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Class_or_func_def_stmtContext.class);

        PythonParser.Class_or_func_def_stmtContext funcdefContext = (PythonParser.Class_or_func_def_stmtContext) exprNodes.get(0);
        assertTrue(isClassDefinition(funcdefContext));
        PythonClassDefinition pythonClassDefinition = transformClassOrFuncDefToClassDefinition(funcdefContext, null).get();
        assertEquals("baz", pythonClassDefinition.name.str);
        assertEquals(2, pythonClassDefinition.parentClasses.length);
        assertEquals("Class1", pythonClassDefinition.parentClasses[0].value);
        assertEquals("Class2", pythonClassDefinition.parentClasses[1].value);
        assertEquals(0, pythonClassDefinition.decorators.length);
    }

    @Test
    @DisplayName("Map a simple class with multiple decorators")
    public void testTransformClassDefinitionMultipleDecorators() {
        String code = """
            @decorator1
            @decorator2(bli = blo)
            class baz:
                pass""";

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Class_or_func_def_stmtContext.class);

        PythonParser.Class_or_func_def_stmtContext funcdefContext = (PythonParser.Class_or_func_def_stmtContext) exprNodes.get(0);
        assertTrue(isClassDefinition(funcdefContext));
        PythonClassDefinition pythonClassDefinition = transformClassOrFuncDefToClassDefinition(funcdefContext, null).get();
        assertEquals("baz", pythonClassDefinition.name.str);
        assertEquals(0, pythonClassDefinition.parentClasses.length);
        assertEquals(2, pythonClassDefinition.decorators.length);

        assertEquals("decorator1", pythonClassDefinition.decorators[0].name.str);
        assertEquals(0, pythonClassDefinition.decorators[0].arguments.length);

        assertEquals("decorator2", pythonClassDefinition.decorators[1].name.str);
        assertEquals(1, pythonClassDefinition.decorators[1].arguments.length);
        assertEquals("blo", pythonClassDefinition.decorators[1].arguments[0].value.str);
        assertEquals("bli", pythonClassDefinition.decorators[1].arguments[0].name.str);
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

        ParseTree root = parseCode(code);

        List<ParseTree> exprNodes = getNodesFromType(root, PythonParser.Class_or_func_def_stmtContext.class);

        PythonParser.Class_or_func_def_stmtContext funcdefContext = (PythonParser.Class_or_func_def_stmtContext) exprNodes.get(0);
        assertTrue(isClassDefinition(funcdefContext));
        var optionalClass = transformClassOrFuncDefToClassDefinition(funcdefContext, null);
        assertTrue(optionalClass.isPresent());
        var classDefinition = optionalClass.get();
        assertEquals(AST_ELEMENT_TYPE_CLASS_DEFINITION, classDefinition.astType);
        assertEquals(AST_ELEMENT_TYPE_SEQUENCE, classDefinition.content.astType);
        Sequence seq = (Sequence) classDefinition.content;
        assertEquals(2, seq.elements.length);
        assertEquals(AST_ELEMENT_TYPE_FUNCTION_DEFINITION, seq.elements[0].astType);
        assertEquals(AST_ELEMENT_TYPE_FUNCTION_DEFINITION, seq.elements[1].astType);
    }

}
