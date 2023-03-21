package io.codiga.analyzer.ast.languages.python;

import io.codiga.analyzer.ast.common.AnalyzerContext;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.parser.antlr.python.gen.PythonLexer;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PythonAnalyzerContext extends AnalyzerContext {


    private Logger logger = LoggerFactory.getLogger(PythonAnalyzerContext.class);

    public PythonAnalyzerContext(Language language, String filename, String code, List<AnalyzerRule> rules, boolean logOutput) {
        super(language, filename, code, rules, logOutput);
        PythonLexer lexer = new PythonLexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PythonParser parser = new PythonParser(tokens);

        parser.setBuildParseTree(true);

        //ANTLR
//        CodigaVisitor codigaVisitor = new CodigaVisitor(code);
//        codigaVisitor.visit(parser.root());

        // TreeSitter
        io.codiga.parser.treesitter.python.CodigaVisitor codigaVisitor = new io.codiga.parser.treesitter.python.CodigaVisitor(code);
        codigaVisitor.parse();
        

        entityCheckedToAstElements.get(EntityChecked.ASSIGNMENT).addAll(codigaVisitor.assignments);
        entityCheckedToAstElements.get(EntityChecked.FOR_LOOP).addAll(codigaVisitor.forStatements);
        entityCheckedToAstElements.get(EntityChecked.FUNCTION_CALL).addAll(codigaVisitor.functionCalls);
        entityCheckedToAstElements.get(EntityChecked.FUNCTION_DEFINITION).addAll(codigaVisitor.functionDefinitions);
        entityCheckedToAstElements.get(EntityChecked.IF_STATEMENT).addAll(codigaVisitor.ifStatements);
        entityCheckedToAstElements.get(EntityChecked.IMPORT_STATEMENT).addAll(codigaVisitor.fromStatements);
        entityCheckedToAstElements.get(EntityChecked.IMPORT_STATEMENT).addAll(codigaVisitor.importStatements);
        entityCheckedToAstElements.get(EntityChecked.TRY_BLOCK).addAll(codigaVisitor.tryStatements);
        entityCheckedToAstElements.get(EntityChecked.CLASS_DEFINITION).addAll(codigaVisitor.classDefinitions);

        // Add all entities to the ANY type
        addAllEntityToAny();
    }
}


