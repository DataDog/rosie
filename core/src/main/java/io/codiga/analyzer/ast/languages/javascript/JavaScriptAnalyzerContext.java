package io.codiga.analyzer.ast.languages.javascript;

import io.codiga.analyzer.ast.common.AnalyzerContext;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.javascript.gen.JavaScriptLexer;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class JavaScriptAnalyzerContext extends AnalyzerContext {


    Map<EntityChecked, List<AstElement>> entityCheckedToAstElements;
    private Logger logger = LoggerFactory.getLogger(JavaScriptAnalyzerContext.class);

    public JavaScriptAnalyzerContext(Language language, String filename, String code, List<AnalyzerRule> rules, boolean logOutput) {
        super(language, filename, code, rules, logOutput);
        JavaScriptLexer lexer = new JavaScriptLexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaScriptParser parser = new JavaScriptParser(tokens);


        CodigaVisitor codigaVisitor = new CodigaVisitor(code);
        codigaVisitor.visit(parser.program());


        entityCheckedToAstElements = new HashMap<>();

        Arrays.stream(EntityChecked.values()).toList().forEach(entityChecked -> {
            entityCheckedToAstElements.put(entityChecked, new ArrayList<>());
        });

        entityCheckedToAstElements.get(EntityChecked.FUNCTION_CALL).addAll(codigaVisitor.functionCalls);
        entityCheckedToAstElements.get(EntityChecked.IMPORT_STATEMENT).addAll(codigaVisitor.importStatements);
        entityCheckedToAstElements.get(EntityChecked.ASSIGNMENT).addAll(codigaVisitor.assignments);
    }
}
