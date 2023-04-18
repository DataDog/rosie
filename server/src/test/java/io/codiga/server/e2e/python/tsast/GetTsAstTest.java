package io.codiga.server.e2e.python.tsast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.GetTreeSitterAstResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.server.response.ResponseErrors.ERROR_LANGUAGE_NOT_SUPPORTED;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GetTsAstTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(GetTsAstTest.class);

    String emptyCode = "";

    String validCode = """
            arr = ["foo", "bar"];
                        
            def passes():
            	pass;""";

    String brokenCode = """
            def
                        
            foo = "bar";""";

    @Test
    @DisplayName("Test getting a TreeSitter AST for a blank file")
    public void testGettingTreeSitterAstForEmptyFile() throws Exception {
        GetTreeSitterAstResponse response = executeTreesitterAstTest(emptyCode, Language.PYTHON, "utf-8");

        assertEquals(response.errors.size(), 0);
        assertEquals(response.result.astType, "module");
        assertEquals(response.result.start.line, 0);
        assertEquals(response.result.start.col, 0);
        assertEquals(response.result.end.line, 0);
        assertEquals(response.result.end.col, 0);
        assertEquals(response.result.children.size(), 0);
    }

    @Test
    @DisplayName("Test getting a TreeSitter AST for a valid file")
    public void testGettingTreeSitterAstForValidFile() throws Exception {
        GetTreeSitterAstResponse response = executeTreesitterAstTest(validCode, Language.PYTHON, "utf-8");

        assertEquals(response.errors.size(), 0);
        assertEquals(response.result.astType, "module");
        assertEquals(response.result.children.size(), 2);
        assertEquals(response.result.children.get(0).astType, "expression_statement");
        assertEquals(response.result.children.get(0).children.size(), 1);
        assertEquals(response.result.children.get(0).children.get(0).astType, "assignment");
        assertEquals(response.result.children.get(0).children.get(0).start.line, 0);
        assertEquals(response.result.children.get(0).children.get(0).start.col, 0);
        assertEquals(response.result.children.get(0).children.get(0).end.line, 0);
        assertEquals(response.result.children.get(0).children.get(0).end.col, 20);
        assertEquals(response.result.children.get(0).children.get(0).children.size(), 2);
        assertEquals(response.result.children.get(0).children.get(0).children.get(0).astType, "identifier");
        assertEquals(response.result.children.get(0).children.get(0).children.get(0).fieldName, "left");
        assertEquals(response.result.children.get(0).children.get(0).children.get(1).astType, "list");
        assertEquals(response.result.children.get(0).children.get(0).children.get(1).fieldName, "right");
        assertEquals(response.result.children.get(1).astType, "function_definition");
        assertEquals(response.result.children.get(1).children.size(), 3);
        assertEquals(response.result.children.get(1).children.get(0).astType, "identifier");
        assertEquals(response.result.children.get(1).children.get(0).children.size(), 0);
        assertEquals(response.result.children.get(1).children.get(1).astType, "parameters");
        assertEquals(response.result.children.get(1).children.get(1).children.size(), 0);
        assertEquals(response.result.children.get(1).children.get(2).astType, "block");
        assertEquals(response.result.children.get(1).children.get(2).fieldName, "body");
        assertEquals(response.result.children.get(1).children.get(2).start.line, 3);
        assertEquals(response.result.children.get(1).children.get(2).start.col, 1);
        assertEquals(response.result.children.get(1).children.get(2).end.line, 3);
        assertEquals(response.result.children.get(1).children.get(2).end.col, 6);
        assertEquals(response.result.children.get(1).children.get(2).children.size(), 1);
        assertEquals(response.result.children.get(1).children.get(2).children.get(0).astType, "pass_statement");
        assertEquals(response.result.children.get(1).children.get(2).children.get(0).children.size(), 0);
    }

    @Test
    @DisplayName("Test getting a TreeSitter AST for an invalid file")
    public void testGettingTreeSitterAstForInvalidFile() throws Exception {
        GetTreeSitterAstResponse response = executeTreesitterAstTest(brokenCode, Language.PYTHON, "utf-8");

        assertEquals(response.errors.size(), 0);
        assertEquals(response.result.astType, "module");
        assertEquals(response.result.children.size(), 2);
        assertEquals(response.result.children.get(0).astType, "ERROR");
        assertEquals(response.result.children.get(0).start.line, 0);
        assertEquals(response.result.children.get(0).start.col, 0);
        assertEquals(response.result.children.get(0).end.line, 2);
        assertEquals(response.result.children.get(0).end.col, 5);
        assertEquals(response.result.children.get(0).children.size(), 1);
        assertEquals(response.result.children.get(0).children.get(0).astType, "identifier");
        assertEquals(response.result.children.get(0).children.get(0).children.size(), 0);
        assertEquals(response.result.children.get(1).astType, "expression_statement");
        assertEquals(response.result.children.get(1).children.size(), 1);
        assertEquals(response.result.children.get(1).children.get(0).astType, "string");
        assertEquals(response.result.children.get(1).children.get(0).start.line, 2);
        assertEquals(response.result.children.get(1).children.get(0).start.col, 6);
        assertEquals(response.result.children.get(1).children.get(0).end.line, 2);
        assertEquals(response.result.children.get(1).children.get(0).end.col, 11);
        assertEquals(response.result.children.get(1).children.get(0).children.size(), 1);
        assertEquals(response.result.children.get(1).children.get(0).children.get(0).astType, "string_content");
    }
}