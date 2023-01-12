package io.codiga.analyzer.ast.languages.javascript;

import io.codiga.model.ast.common.Sequence;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptIfStatementToIfStatement.transformIfStatementToIfStatement;
import static io.codiga.model.ast.common.AstElement.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IfStatementTest extends JavaScriptTestUtils {

    private Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
    }

    @AfterAll
    public static void done() {

    }

    @Test
    @DisplayName("Get the ifstatement")
    public void testIfStatement() {
        String code = """
            import { useState, useEffect } from "react";
            import ReactDOM from "react-dom/client";

            function Timer() {
              const [count, setCount] = useState(0);

            	if(count === 3) {
            		useEffect(() => {
            			setTimeout(() => {
            				setCount((count) => count + 1);
            			}, 1000);
            		});
            	} else {
            	    const foo = 42;
            	}

              return null;
            }

            const root = ReactDOM.createRoot(document.getElementById('root'));
            root.render(<Timer />);
            """;

        ParseTree root = parseCode(code);

        List<ParseTree> nodes = getNodesFromType(root, JavaScriptParser.IfStatementContext.class);

        assertEquals(1, nodes.size());
        JavaScriptParser.IfStatementContext ifStatementContext = (JavaScriptParser.IfStatementContext) nodes.get(0);
        var ifOptional = transformIfStatementToIfStatement(ifStatementContext, null);
        assertTrue(ifOptional.isPresent());
        var ifStatement = ifOptional.get();
        assertEquals(AST_ELEMENT_IF_STATEMENT, ifStatement.astType);
        assertEquals(AST_ELEMENT_TYPE_SEQUENCE, ifStatement.statements.astType);
        var seq = (Sequence) ifStatement.statements;
        assertEquals(1, seq.elements.length);
        assertEquals(AST_ELEMENT_TYPE_FUNCTION_CALL, seq.elements[0].astType);

        var elseSeq = (Sequence) ifStatement.elseStatements;
        assertEquals(1, elseSeq.elements.length);
        assertEquals(AST_ELEMENT_TYPE_VARIABLE_DECLARATION, elseSeq.elements[0].astType);
    }
}
