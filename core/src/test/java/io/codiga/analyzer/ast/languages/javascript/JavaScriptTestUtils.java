package io.codiga.analyzer.ast.languages.javascript;

import io.codiga.analyzer.ast.TestUtils;
import io.codiga.parser.javascript.gen.JavaScriptLexer;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class JavaScriptTestUtils extends TestUtils {
    public ParseTree parseCode(String code) {
        CharStream charStream = CharStreams.fromString(code);

        JavaScriptLexer lexer = new JavaScriptLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaScriptParser parser = new JavaScriptParser(tokens);
        parser.setBuildParseTree(true);
        return parser.program();
    }
}
