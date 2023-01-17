package io.codiga.analyzer.ast.languages.typescript;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.typescript.TypeScriptInterface;
import io.codiga.model.ast.typescript.TypeScriptInterfaceIndexSignature;
import io.codiga.model.ast.typescript.TypeScriptInterfaceProperty;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptInterfaceTransformation.transformInterfaceDeclaration;
import static io.codiga.model.ast.common.AstElement.AST_ELEMENT_TYPE_INTERFACE_INDEX_SIGNATURE;
import static io.codiga.model.ast.common.AstElement.AST_ELEMENT_TYPE_INTERFACE_PROPERTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InterfaceTest extends TypeScriptTestUtils {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }


    @Test
    @DisplayName("simple interface")
    public void testSimpleInterface() {
        String code = """
            interface LabeledValue {
              label: string;
            }
                        """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.InterfaceDeclarationContext.class);

        assertEquals(1, nodes.size());

        Optional<TypeScriptInterface> interfaceOptional = transformInterfaceDeclaration((TypeScriptParser.InterfaceDeclarationContext) nodes.get(0), null);
        assertTrue(interfaceOptional.isPresent());
        assertEquals("LabeledValue", ((AstString) interfaceOptional.get().name).value);
        assertEquals(1, interfaceOptional.get().members.length);
        assertEquals(AST_ELEMENT_TYPE_INTERFACE_PROPERTY, interfaceOptional.get().members[0].astType);
        TypeScriptInterfaceProperty typeScriptInterfaceProperty = (TypeScriptInterfaceProperty) interfaceOptional.get().members[0];

        assertEquals("label", ((AstString) typeScriptInterfaceProperty.name).value);
        assertEquals("string", ((AstString) typeScriptInterfaceProperty.value).value);

    }


    @Test
    @DisplayName("interface with index signature")
    public void testIndexSignature() {
        String code = """
            interface StringByString {
              [key: string]: string;
            }
                                    """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, TypeScriptParser.InterfaceDeclarationContext.class);

        assertEquals(1, nodes.size());

        Optional<TypeScriptInterface> interfaceOptional = transformInterfaceDeclaration((TypeScriptParser.InterfaceDeclarationContext) nodes.get(0), null);
        assertTrue(interfaceOptional.isPresent());
        assertEquals("StringByString", ((AstString) interfaceOptional.get().name).value);
        assertEquals(1, interfaceOptional.get().members.length);
        assertEquals(AST_ELEMENT_TYPE_INTERFACE_INDEX_SIGNATURE, interfaceOptional.get().members[0].astType);
        TypeScriptInterfaceIndexSignature typeScriptInterfaceIndexSignature = (TypeScriptInterfaceIndexSignature) interfaceOptional.get().members[0];

        assertEquals("key", ((AstString) typeScriptInterfaceIndexSignature.keyName).value);
        assertEquals("string", ((AstString) typeScriptInterfaceIndexSignature.keyType).value);
        assertEquals("string", ((AstString) typeScriptInterfaceIndexSignature.type).value);

    }

}
