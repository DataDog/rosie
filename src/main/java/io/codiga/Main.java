package io.codiga;

import io.codiga.model.error.AnalysisError;
import io.codiga.parser.python.CodigaVisitor;
import io.codiga.parser.python.gen.PythonLexer;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void applicationMain(String[] args) {

        String filename = "/Users/julien/git/antlr-python-experiments/src/test/data/request.py";

        if (!Files.exists(Path.of(filename))){
            System.exit(1);
        }

        File file = new File(filename);
        try {
            ScriptEngine graalEngine = new ScriptEngineManager().getEngineByName("graal.js");
            PythonLexer lexer = new PythonLexer(new ANTLRFileStream(filename));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            PythonParser parser = new PythonParser(tokens);
            parser.setBuildParseTree(true);
            FileInputStream fileInputStream = new FileInputStream(file);

            CodigaVisitor codigaVisitor = new CodigaVisitor(null);
            codigaVisitor.visit(parser.root());

            fileInputStream.close();
            for(AnalysisError analysisError: codigaVisitor.errorReporting.getErrors()) {
                String error = String.format("line %s: %s", analysisError.line(), analysisError.message());
                System.out.println(error);
            }
        } catch (IOException notFound) {
            System.out.println("exception");
        }


    }
}