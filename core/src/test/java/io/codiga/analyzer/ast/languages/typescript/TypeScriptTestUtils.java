package io.codiga.analyzer.ast.languages.typescript;

import io.codiga.analyzer.ast.utils.AntlrUtils;
import io.codiga.parser.antlr.typescript.gen.TypeScriptLexer;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class TypeScriptTestUtils extends AntlrUtils {
    public ParseTree parseCode(String code) {
        CharStream charStream = CharStreams.fromString(code);

        TypeScriptLexer lexer = new TypeScriptLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TypeScriptParser parser = new TypeScriptParser(tokens);
        parser.setBuildParseTree(true);
        return parser.program();
    }
}
