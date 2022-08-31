package io.codiga.analyzer.languages.python;

import io.codiga.analyzer.AnalyzerFuturePool;
import io.codiga.analyzer.languages.AnalyzerCommon;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.ast.python.CodigaVisitor;
import io.codiga.model.error.AnalysisResult;
import io.codiga.model.error.RuleResult;
import io.codiga.parser.python.gen.PythonLexer;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.graalvm.polyglot.PolyglotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static io.codiga.ast.vm.VmUtils.formatVmErrorMessage;
import static io.codiga.model.ErrorCode.*;
import static io.codiga.utils.CompletableFutureUtils.sequence;

public class PythonAnalyzer extends AnalyzerCommon {

    private AnalyzerFuturePool pool = AnalyzerFuturePool.getInstance();

    private Logger logger = LoggerFactory.getLogger(PythonAnalyzer.class);

    public CompletableFuture<AnalysisResult> analyze(String filename, String code, List<AnalyzerRule> rules) {
        logger.info("submitting new task");
        List<CompletableFuture<RuleResult>> futures = rules.stream().map(rule -> {
            CompletableFuture<RuleResult> future = CompletableFuture.supplyAsync(() -> {
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
                    return new RuleResult(rule.name(), codigaVisitor.errorReporting.getErrors(), List.of(), null);
                }, pool.service)
                .orTimeout(getTimeout(), TimeUnit.MILLISECONDS)
                .exceptionally(exception -> {
                    if (exception instanceof TimeoutException) {
                        logger.error(String.format("reporting rule %s as timeout", rule.name()));
                        return new RuleResult(rule.name(), List.of(), List.of(ERROR_RULE_TIMEOUT), null);
                    }

                    if (exception.getCause() != null && exception.getCause() instanceof PolyglotException) {

                        String executionMessage = formatVmErrorMessage(exception.getMessage());
                        logger.error(String.format("reporting rule %s as execution error", rule.name()));
                        exception.printStackTrace();
                        return new RuleResult(rule.name(), List.of(), List.of(ERROR_RULE_EXECUTION), executionMessage);
                    }

                    logger.error(String.format("unhandled exception for rule %s: %s - %s", rule.name(), exception, exception.getMessage()));

                    return new RuleResult(rule.name(), List.of(), List.of(ERROR_RULE_UNKNOWN), null);
                });
            return future;
        }).toList();


        return sequence(futures).thenApply(listOfList -> {
            List<RuleResult> finalList = new ArrayList<>();
            listOfList.forEach(l -> finalList.add(l));
            return new AnalysisResult(finalList);
        });
    }
}
