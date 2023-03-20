package io.codiga.analyzer.ast.languages.typescript;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.ClassDeclarationOneParent;
import io.codiga.model.ast.typescript.TypeScriptType;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptClassDeclarationToClass.transformClassDeclaration;
import static org.junit.jupiter.api.Assertions.*;

public class ClassDeclarationTest extends TypeScriptTestUtils {

    private Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }


    @Test
    @DisplayName("Parse a class with a parent")
    public void testParseClassWithParent() {
        String code = """
            class Rabbit extends Animal {
              // generated for extending classes without own constructors
              constructor(...args) {
                super(...args);
              }
            }""";

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.ClassDeclarationContext.class);

        for (ParseTree node : nodes) {
            Optional<ClassDeclarationOneParent> classDeclarationOptional = transformClassDeclaration((TypeScriptParser.ClassDeclarationContext) node, null);
            assertTrue(classDeclarationOptional.isPresent());
            ClassDeclarationOneParent classDeclaration = classDeclarationOptional.get();
            assertEquals(classDeclaration.name.value, "Rabbit");
            assertEquals(((AstString) ((TypeScriptType) classDeclaration.parentClass).name).value, "Animal");
        }
    }

    @Test
    @DisplayName("Parse a class without a parent")
    public void testParseClassWithoutParent() {
        String code = """
            class Rabbit {
              // generated for extending classes without own constructors
              constructor(...args) {
                super(...args);
              }
            }""";

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.ClassDeclarationContext.class);

        for (ParseTree node : nodes) {
            Optional<ClassDeclarationOneParent> classDeclarationOptional = transformClassDeclaration((TypeScriptParser.ClassDeclarationContext) node, null);
            assertTrue(classDeclarationOptional.isPresent());
            ClassDeclarationOneParent classDeclaration = classDeclarationOptional.get();
            assertEquals(classDeclaration.name.value, "Rabbit");
            assertNull(classDeclaration.parentClass);
        }
    }

}
