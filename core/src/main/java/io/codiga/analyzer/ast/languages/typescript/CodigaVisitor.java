package io.codiga.analyzer.ast.languages.typescript;

import datadog.trace.api.Trace;
import io.codiga.model.ast.common.*;
import io.codiga.model.ast.javascript.JavaScriptHtmlElement;
import io.codiga.model.ast.javascript.JavaScriptImport;
import io.codiga.model.ast.javascript.JavaScriptTryCatchStatement;
import io.codiga.model.context.JavaScriptNodeContext;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import io.codiga.parser.typescript.gen.TypeScriptParserBaseVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptHtmlElementTransformation.transformTypeScriptHtmlElement;


/**
 * Main visitor. Visits the build tree, and build nodes for the AST.
 */
public class CodigaVisitor extends TypeScriptParserBaseVisitor<Object> {

    private final String code;
    private final Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);
    // To build the context
    Stack<FunctionDefinition> visitedFunctionDefinitions;
    Stack<AstElement> visitedClassDefinitions;
    Stack<IfStatement> visitedIfStatements;
    Stack<ForStatement> visitedForStatements;
    List<AstElement> visitedImportStatements;


    // List of all AST elements
    List<Assignment> assignments;

    List<JavaScriptImport> importStatements;
    List<IfStatement> ifStatements;
    List<JavaScriptTryCatchStatement> tryStatements;
    List<ForStatement> forStatements;
    List<FunctionDefinition> functionDefinitions;
    List<FunctionCall> functionCalls;
    List<AstElement> classDefinitions;
    List<JavaScriptHtmlElement> htmlElements;

    private TypeScriptParser.ProgramContext root;

    public CodigaVisitor(String code) {
        this.code = code;

        // Initialize the list of all elements being visited
        functionCalls = new ArrayList<>();
        importStatements = new ArrayList<>();
        assignments = new ArrayList<>();
        functionDefinitions = new ArrayList<>();
        classDefinitions = new ArrayList<>();
        ifStatements = new ArrayList<>();
        forStatements = new ArrayList<>();
        tryStatements = new ArrayList<>();
        htmlElements = new ArrayList<>();

        // Initialize the visited elements
        visitedImportStatements = new ArrayList<>();
        visitedFunctionDefinitions = new Stack<>();
        visitedClassDefinitions = new Stack<>();
        visitedIfStatements = new Stack<>();
        visitedForStatements = new Stack<>();
    }

    @Override
    public Object visitProgram(TypeScriptParser.ProgramContext ctx) {
        this.root = ctx;
        return visitChildren(ctx);
    }

    @Override
    public Object visitHtmlElement(TypeScriptParser.HtmlElementContext ctx) {
        Optional<JavaScriptHtmlElement> htmlElementOptional = transformTypeScriptHtmlElement(ctx, root);

        htmlElementOptional.ifPresent(v -> {
            v.setContext(buildContext());
            this.htmlElements.add(htmlElementOptional.get());
        });

        return visitChildren(ctx);
    }


    @Trace(operationName = "CodigaTypeScriptVisitor.buildContext")
    private JavaScriptNodeContext buildContext() {
        JavaScriptNodeContext res = JavaScriptNodeContext.buildJavaScriptNodeContext()
            .currentFunction(visitedFunctionDefinitions.isEmpty() ? null : visitedFunctionDefinitions.lastElement())
//            .currentTryBlock(visitedTryStatements.size() > 0 ? visitedTryStatements.lastElement() : null)
            .currentClass(visitedClassDefinitions.size() > 0 ? visitedClassDefinitions.lastElement() : null)
            .code(this.code)
            .importsList(visitedImportStatements)
            .assignmentsList(this.assignments)
            .build();
        return res;
    }
}
