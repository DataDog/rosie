package io.codiga.analyzer.ast.languages.python.antlr;

import io.codiga.analyzer.ast.utils.AntlrUtils;
import io.codiga.parser.antlr.python.gen.PythonLexer;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class PythonTestUtils extends AntlrUtils {


    public ParseTree parseCode(String code) {
        CharStream charStream = CharStreams.fromString(code);

        PythonLexer lexer = new PythonLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PythonParser parser = new PythonParser(tokens);
        parser.setBuildParseTree(true);
        return parser.root();
    }
}
