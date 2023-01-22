package io.codiga.analyzer.ast.languages.typescript;

import io.codiga.analyzer.ast.common.AnalyzerContext;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.parser.typescript.gen.TypeScriptLexer;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TypeScriptAnalyzerContext extends AnalyzerContext {


    private final Logger logger = LoggerFactory.getLogger(TypeScriptAnalyzerContext.class);


    public TypeScriptAnalyzerContext(Language language, String filename, String code, List<AnalyzerRule> rules, boolean logOutput) {
        super(language, filename, code, rules, logOutput);
        TypeScriptLexer lexer = new TypeScriptLexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TypeScriptParser parser = new TypeScriptParser(tokens);


        io.codiga.analyzer.ast.languages.typescript.CodigaVisitor codigaVisitor = new CodigaVisitor(code);
        codigaVisitor.visit(parser.program());


        entityCheckedToAstElements.get(EntityChecked.CLASS_DEFINITION).addAll(codigaVisitor.classDefinitions);
        entityCheckedToAstElements.get(EntityChecked.FUNCTION_DEFINITION).addAll(codigaVisitor.functionDefinitions);
        entityCheckedToAstElements.get(EntityChecked.IF_STATEMENT).addAll(codigaVisitor.ifStatements);
        entityCheckedToAstElements.get(EntityChecked.FOR_LOOP).addAll(codigaVisitor.forStatements);
        entityCheckedToAstElements.get(EntityChecked.FUNCTION_CALL).addAll(codigaVisitor.functionCalls);
        entityCheckedToAstElements.get(EntityChecked.IMPORT_STATEMENT).addAll(codigaVisitor.importStatements);
        entityCheckedToAstElements.get(EntityChecked.ASSIGNMENT).addAll(codigaVisitor.assignments);
        entityCheckedToAstElements.get(EntityChecked.TRY_BLOCK).addAll(codigaVisitor.tryStatements);
        entityCheckedToAstElements.get(EntityChecked.HTML_ELEMENT).addAll(codigaVisitor.htmlElements);
        entityCheckedToAstElements.get(EntityChecked.VARIABLE_DECLARATION).addAll(codigaVisitor.variableDeclarations);
        entityCheckedToAstElements.get(EntityChecked.INTERFACE).addAll(codigaVisitor.typeScriptInterfaces);


        // Add all entities to the ANY type
        addAllEntityToAny();
    }
}
