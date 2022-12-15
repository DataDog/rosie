package io.codiga.analyzer.ast.languages.typescript;

import io.codiga.analyzer.ast.common.AnalyzerContext;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.typescript.gen.TypeScriptLexer;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TypeScriptAnalyzerContext extends AnalyzerContext {


    private final Logger logger = LoggerFactory.getLogger(TypeScriptAnalyzerContext.class);
    Map<EntityChecked, List<AstElement>> entityCheckedToAstElements;

    public TypeScriptAnalyzerContext(Language language, String filename, String code, List<AnalyzerRule> rules, boolean logOutput) {
        super(language, filename, code, rules, logOutput);
        TypeScriptLexer lexer = new TypeScriptLexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TypeScriptParser parser = new TypeScriptParser(tokens);


        io.codiga.analyzer.ast.languages.typescript.CodigaVisitor codigaVisitor = new CodigaVisitor(code);
        codigaVisitor.visit(parser.program());


        entityCheckedToAstElements = new HashMap<>();

        Arrays.stream(EntityChecked.values()).toList().forEach(entityChecked -> {
            entityCheckedToAstElements.put(entityChecked, new ArrayList<>());
        });

        entityCheckedToAstElements.get(EntityChecked.CLASS_DEFINITION).addAll(codigaVisitor.classDefinitions);
        entityCheckedToAstElements.get(EntityChecked.FUNCTION_DEFINITION).addAll(codigaVisitor.functionDefinitions);
        entityCheckedToAstElements.get(EntityChecked.IF_STATEMENT).addAll(codigaVisitor.ifStatements);
        entityCheckedToAstElements.get(EntityChecked.FOR_LOOP).addAll(codigaVisitor.forStatements);
        entityCheckedToAstElements.get(EntityChecked.FUNCTION_CALL).addAll(codigaVisitor.functionCalls);
        entityCheckedToAstElements.get(EntityChecked.IMPORT_STATEMENT).addAll(codigaVisitor.importStatements);
        entityCheckedToAstElements.get(EntityChecked.ASSIGNMENT).addAll(codigaVisitor.assignments);
        entityCheckedToAstElements.get(EntityChecked.TRY_BLOCK).addAll(codigaVisitor.tryStatements);
        entityCheckedToAstElements.get(EntityChecked.HTML_ELEMENT).addAll(codigaVisitor.htmlElements);
    }
}
