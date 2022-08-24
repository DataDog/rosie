package io.codiga.analyzer.languages.python;

import io.codiga.analyzer.AnalyzerFuturePool;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.error.AnalysisError;
import io.codiga.parser.python.CodigaVisitor;
import io.codiga.parser.python.gen.PythonLexer;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static io.codiga.utils.CompletableFutureUtils.sequence;

public class PythonAnalyzer {

    private AnalyzerFuturePool pool = AnalyzerFuturePool.getInstance();

    private Logger logger = LoggerFactory.getLogger(PythonAnalyzer.class);

    public CompletableFuture<List<AnalysisError>> analyze(String filename, String code, List<AnalyzerRule> rules) {
        logger.info("submitting new task");
        List<CompletableFuture<List<AnalysisError>>> futures = rules.stream().map(rule -> {
            CompletableFuture<List<AnalysisError>> future =  CompletableFuture.supplyAsync(() -> {
                logger.info("analysis start");
                logger.info(code);

                PythonLexer lexer = new PythonLexer(CharStreams.fromString(code));
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                PythonParser parser = new PythonParser(tokens);
                parser.setBuildParseTree(true);


                CodigaVisitor codigaVisitor = new CodigaVisitor(rule);
                codigaVisitor.visit(parser.root());
                logger.info("error reported: " + codigaVisitor.errorReporting.getErrors().size());
                logger.info("analysis done");
                return codigaVisitor.errorReporting.getErrors();
            }, pool.service)
            .whenComplete((r, e) -> {
                if (e != null) {
                    logger.error("exception when analyzing python code", e);
                    e.printStackTrace();
                }
            });
            return future;
        }).toList();


        return sequence(futures).thenApply(listOfList -> {
            List<AnalysisError> finalList = new ArrayList<>();
            listOfList.forEach(l -> finalList.addAll(l));
            return finalList;
        });
    }
}
