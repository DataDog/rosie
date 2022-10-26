package io.codiga.analyzer.ast.languages.python;

import io.codiga.analyzer.ast.common.AnalyzerContext;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.python.gen.PythonLexer;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class PythonAnalyzerContext extends AnalyzerContext {


    Map<EntityChecked, List<AstElement>> entityCheckedToAstElements;
    private Logger logger = LoggerFactory.getLogger(PythonAnalyzerContext.class);

    public PythonAnalyzerContext(Language language, String filename, String code, List<AnalyzerRule> rules, boolean logOutput) {
        super(language, filename, code, rules, logOutput);
        PythonLexer lexer = new PythonLexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PythonParser parser = new PythonParser(tokens);
        parser.setBuildParseTree(true);

        CodigaVisitor codigaVisitor = new CodigaVisitor();
        codigaVisitor.visit(parser.root());


        entityCheckedToAstElements = new HashMap<>();

        Arrays.stream(EntityChecked.values()).toList().forEach(entityChecked -> {
            entityCheckedToAstElements.put(entityChecked, new ArrayList<>());
        });


        entityCheckedToAstElements.get(EntityChecked.ASSIGNMENT).addAll(codigaVisitor.assignments);
        entityCheckedToAstElements.get(EntityChecked.FOR_LOOP).addAll(codigaVisitor.forStatements);
        entityCheckedToAstElements.get(EntityChecked.FUNCTION_CALL).addAll(codigaVisitor.functionCalls);
        entityCheckedToAstElements.get(EntityChecked.FUNCTION_DEFINITION).addAll(codigaVisitor.functionDefinitions);
        entityCheckedToAstElements.get(EntityChecked.IF_STATEMENT).addAll(codigaVisitor.ifStatements);
        entityCheckedToAstElements.get(EntityChecked.IMPORT_STATEMENT).addAll(codigaVisitor.fromStatements);
        entityCheckedToAstElements.get(EntityChecked.IMPORT_STATEMENT).addAll(codigaVisitor.importStatements);
        entityCheckedToAstElements.get(EntityChecked.TRY_BLOCK).addAll(codigaVisitor.tryStatements);

    }
}
