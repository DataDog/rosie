package io.codiga.analyzer.ast.languages.python;

import io.codiga.analyzer.ast.common.AnalyzerCommon;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.error.RuleResult;
import io.codiga.parser.python.gen.PythonLexer;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PythonAnalyzer extends AnalyzerCommon {


    private Logger logger = LoggerFactory.getLogger(PythonAnalyzer.class);

    @Override
    public RuleResult execute(String filename, String code, AnalyzerRule rule) {
        logger.info("analysis start");

        PythonLexer lexer = new PythonLexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PythonParser parser = new PythonParser(tokens);
        parser.setBuildParseTree(true);

        CodigaVisitor codigaVisitor = new CodigaVisitor(rule, code);
        codigaVisitor.visit(parser.root());
        logger.info("error reported: " + codigaVisitor.errorReporting.getErrors().size());
        logger.info("analysis done");
        return new RuleResult(rule.name(), codigaVisitor.errorReporting.getErrors(), List.of(), null);
    }


}
