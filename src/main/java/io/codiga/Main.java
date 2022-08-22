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

        System.out.println("reading file: " + filename);


        if (!Files.exists(Path.of(filename))){
            System.out.println("file does not exists");
            System.exit(1);
        }

        File file = new File(filename);
        try {
            ScriptEngine graalEngine = new ScriptEngineManager().getEngineByName("graal.js");
            long startTime = System.currentTimeMillis();
            PythonLexer lexer = new PythonLexer(new ANTLRFileStream(filename));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            PythonParser parser = new PythonParser(tokens);
            parser.setBuildParseTree(true);
            FileInputStream fileInputStream = new FileInputStream(file);

            CodigaVisitor codigaVisitor = new CodigaVisitor(null);
            codigaVisitor.visit(parser.root());

            fileInputStream.close();
            long endTime = System.currentTimeMillis();
            long timeSpent = endTime - startTime;
            System.out.println("time spent: " + timeSpent);
            System.out.println("error reported: " + codigaVisitor.errorReporting.getErrors().size());
            for(AnalysisError analysisError: codigaVisitor.errorReporting.getErrors()) {
                String error = String.format("line %s: %s", analysisError.line(), analysisError.message());
                System.out.println(error);
            }
        } catch (IOException notFound) {
            System.out.println("exception");
        }


    }
}