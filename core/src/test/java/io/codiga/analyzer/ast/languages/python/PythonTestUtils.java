package io.codiga.analyzer.ast.languages.python;

import io.codiga.analyzer.ast.TestUtils;
import io.codiga.parser.python.gen.PythonLexer;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class PythonTestUtils extends TestUtils {
    public ParseTree parseCode(String code) {
        CharStream charStream = CharStreams.fromString(code);

        PythonLexer lexer = new PythonLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PythonParser parser = new PythonParser(tokens);
        parser.setBuildParseTree(true);
        return parser.root();
    }
}
