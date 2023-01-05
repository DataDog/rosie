package io.codiga.analyzer.ast.languages.javascript;

import datadog.trace.api.Trace;
import io.codiga.model.ast.common.*;
import io.codiga.model.ast.javascript.JavaScriptHtmlData;
import io.codiga.model.ast.javascript.JavaScriptHtmlElement;
import io.codiga.model.ast.javascript.JavaScriptImport;
import io.codiga.model.ast.javascript.JavaScriptTryCatchStatement;
import io.codiga.model.context.JavaScriptNodeContext;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import io.codiga.parser.javascript.gen.JavaScriptParserBaseVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptClassDeclarationToClass.transformClassDeclaration;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptForStatement.transformForStatementToForStatement;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptFunctionCallTransformation.transformArgumentsExpressionToFunctionCall;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptFunctionDeclarationToFunctionDefinition.transformFunctionDeclarationToFunctionDefinition;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptHtmlCharDataTransformation.transformJavaScriptHtmlCharData;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptHtmlElementTransformation.transformJavaScriptHtmlElement;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptIfStatementToIfStatement.transformIfStatementToIfStatement;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptImportStatementToImport.transformImportStatementToImport;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptSingleExpressionTransformation.transformJavaScriptAssignmentExpressionToAssignment;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptTernaryExpressionToIfStatement.transformTernaryExpressionToIfStatement;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptTryStatement.transformTryStatementToTryCatchStatement;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptVariableDeclarationToAssignment.transformVariableDeclarationToAssignment;


/**
 * Main visitor. Visits the build tree, and build nodes for the AST.
 */
public class CodigaVisitor extends JavaScriptParserBaseVisitor<Object> {

    private final String code;
    private final Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);
    // To build the context
    Stack<FunctionDefinition> visitedFunctionDefinitions;
    Stack<AstElement> visitedFunctionCalls;
    Stack<AstElement> visitedClassDefinitions;
    Stack<IfStatement> visitedIfStatements;
    Stack<ForStatement> visitedForStatements;
    List<AstElement> visitedImportStatements;

    Stack<JavaScriptHtmlElement> visitedHtmlElements;


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

    private JavaScriptParser.ProgramContext root;

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
        visitedFunctionCalls = new Stack<>();
        visitedImportStatements = new ArrayList<>();
        visitedFunctionDefinitions = new Stack<>();
        visitedClassDefinitions = new Stack<>();
        visitedIfStatements = new Stack<>();
        visitedForStatements = new Stack<>();
        visitedHtmlElements = new Stack<>();
    }

    @Override
    public Object visitProgram(JavaScriptParser.ProgramContext ctx) {
        this.root = ctx;
        return visitChildren(ctx);
    }

    @Override
    public Object visitArgumentsExpression(JavaScriptParser.ArgumentsExpressionContext ctx) {

        Optional<FunctionCall> functionCallOptional = transformArgumentsExpressionToFunctionCall(ctx, root);
        if (functionCallOptional.isPresent()) {
            FunctionCall functionCall = functionCallOptional.get();
            functionCall.setContext(buildContext());
            this.functionCalls.add(functionCall);
            visitedFunctionCalls.push(functionCall);
            Object obj = visitChildren(ctx);
            visitedFunctionCalls.pop();
            return obj;
        } else {
            return visitChildren(ctx);
        }
    }

    @Override
    public Object visitImportStatement(JavaScriptParser.ImportStatementContext ctx) {
        Optional<JavaScriptImport> javaScriptImport = transformImportStatementToImport(ctx, root);
        if (javaScriptImport.isPresent()) {
            JavaScriptImport jsImport = javaScriptImport.get();
            jsImport.setContext(buildContext());
            this.visitedImportStatements.add(jsImport);
            this.importStatements.add(jsImport);
        }
        return visitChildren(ctx);
    }

    @Override
    public Object visitVariableDeclaration(JavaScriptParser.VariableDeclarationContext ctx) {
        Optional<Assignment> assignmentOptional = transformVariableDeclarationToAssignment(ctx, root);
        if (assignmentOptional.isPresent()) {
            Assignment assignment = assignmentOptional.get();
            assignment.setContext(buildContext());
            this.assignments.add(assignment);
        }
        return visitChildren(ctx);
    }


    @Override
    public Object visitAssignmentExpression(JavaScriptParser.AssignmentExpressionContext ctx) {
        Optional<Assignment> assignmentOptional = transformJavaScriptAssignmentExpressionToAssignment(ctx, root);
        if (assignmentOptional.isPresent()) {
            Assignment assignment = assignmentOptional.get();
            assignment.setContext(buildContext());
            this.assignments.add(assignment);
        }
        return visitChildren(ctx);
    }


    @Override
    public Object visitFunctionDeclaration(JavaScriptParser.FunctionDeclarationContext ctx) {
        Optional<FunctionDefinition> functionDefinitionOptional = transformFunctionDeclarationToFunctionDefinition(ctx, root);
        if (functionDefinitionOptional.isPresent()) {
            FunctionDefinition functionDefinition = functionDefinitionOptional.get();
            functionDefinition.setContext(buildContext());
            this.functionDefinitions.add(functionDefinition);
            this.visitedFunctionDefinitions.push(functionDefinition);
            Object res = visitChildren(ctx);
            this.visitedFunctionDefinitions.pop();
            return res;
        } else {
            return visitChildren(ctx);
        }
    }

    @Override
    public Object visitClassDeclaration(JavaScriptParser.ClassDeclarationContext ctx) {
        Optional<ClassDeclarationOneParent> classDeclarationOptional = transformClassDeclaration(ctx, root);
        if (classDeclarationOptional.isPresent()) {
            this.classDefinitions.add(classDeclarationOptional.get());
            this.visitedClassDefinitions.push(classDeclarationOptional.get());
            Object res = visitChildren(ctx);
            this.visitedClassDefinitions.pop();
            return res;
        } else {
            return visitChildren(ctx);
        }
    }


    @Override
    public Object visitTryStatement(JavaScriptParser.TryStatementContext ctx) {
        Optional<JavaScriptTryCatchStatement> tryCatchStatementOptional = transformTryStatementToTryCatchStatement(ctx, root);
        if (tryCatchStatementOptional.isPresent()) {
            JavaScriptTryCatchStatement javaScriptTryCatchStatement = tryCatchStatementOptional.get();
            javaScriptTryCatchStatement.setContext(buildContext());
            this.tryStatements.add(javaScriptTryCatchStatement);
        }
        return visitChildren(ctx);
    }


    @Override
    public Object visitForStatement(JavaScriptParser.ForStatementContext ctx) {
        Optional<ForStatement> forStatementOptional = transformForStatementToForStatement(ctx, root);
        if (forStatementOptional.isPresent()) {
            ForStatement forStatement = forStatementOptional.get();
            forStatement.setContext(buildContext());
            this.forStatements.add((forStatement));
            this.visitedForStatements.push(forStatement);
            Object res = visitChildren(ctx);
            this.visitedForStatements.pop();
            return res;
        } else {
            return visitChildren(ctx);
        }
    }


    @Override
    public Object visitIfStatement(JavaScriptParser.IfStatementContext ctx) {
        Optional<IfStatement> ifStatementOptional = transformIfStatementToIfStatement(ctx, root);

        if (ifStatementOptional.isPresent()) {
            IfStatement ifStatement = ifStatementOptional.get();
            ifStatement.setContext(buildContext());
            this.ifStatements.add(ifStatement);
            this.visitedIfStatements.push(ifStatement);
            Object res = visitChildren(ctx);
            this.visitedIfStatements.pop();
            return res;
        } else {
            return visitChildren(ctx);
        }
    }

    @Override
    public Object visitTernaryExpression(JavaScriptParser.TernaryExpressionContext ctx) {
        Optional<IfStatement> ifStatementOptional = transformTernaryExpressionToIfStatement(ctx, root);

        if (ifStatementOptional.isPresent()) {
            IfStatement ifStatement = ifStatementOptional.get();
            ifStatement.setContext(buildContext());
            this.ifStatements.add(ifStatement);
            this.visitedIfStatements.push(ifStatement);
            Object res = visitChildren(ctx);
            this.visitedIfStatements.pop();
            return res;
        } else {
            return visitChildren(ctx);
        }
    }


    @Override
    public Object visitHtmlElement(JavaScriptParser.HtmlElementContext ctx) {
        Optional<JavaScriptHtmlElement> htmlElementOptional = transformJavaScriptHtmlElement(ctx, root);

        if (htmlElementOptional.isPresent()) {
            JavaScriptHtmlElement htmlElement = htmlElementOptional.get();
            htmlElement.setContext(buildContext());
            this.htmlElements.add(htmlElementOptional.get());

            // If there is one visited html elements, add it to the list
            if (visitedHtmlElements.size() > 0) {
                JavaScriptHtmlElement parent = visitedHtmlElements.peek();
                htmlElement.setParentHtmlElement(parent);
                parent.addChild(htmlElement);
            }
            visitedHtmlElements.push(htmlElement);
            Object res = visitChildren(ctx);
            visitedHtmlElements.pop();
            // We visited all the children, build the list of children as an array
            htmlElement.updateChildren();
            return res;
        } else {
            return visitChildren(ctx);
        }
    }

    @Override
    public Object visitHtmlChardata(JavaScriptParser.HtmlChardataContext ctx) {

        Optional<JavaScriptHtmlData> htmlDataOptional = transformJavaScriptHtmlCharData(ctx, root);

        if (htmlDataOptional.isPresent()) {
            JavaScriptHtmlData htmlData = htmlDataOptional.get();
            htmlData.setContext(buildContext());

            // If there is one visited html elements, add it to the list
            if (visitedHtmlElements.size() > 0) {
                JavaScriptHtmlElement parent = visitedHtmlElements.peek();
                parent.addChild(htmlData);
            }
            Object res = visitChildren(ctx);
            return res;
        } else {
            return visitChildren(ctx);
        }
    }


    @Trace(operationName = "CodigaJavaScriptVisitor.buildContext")
    private JavaScriptNodeContext buildContext() {
        JavaScriptNodeContext res = JavaScriptNodeContext.buildJavaScriptNodeContext()
            .currentFunction(visitedFunctionDefinitions.isEmpty() ? null : visitedFunctionDefinitions.lastElement())
            .currentFunctionCall(visitedFunctionCalls.isEmpty() ? null : visitedFunctionCalls.lastElement())
//            .currentTryBlock(visitedTryStatements.size() > 0 ? visitedTryStatements.lastElement() : null)
            .currentClass(visitedClassDefinitions.size() > 0 ? visitedClassDefinitions.lastElement() : null)
            .code(this.code)
            .importsList(visitedImportStatements)
            .assignmentsList(this.assignments)
            .build();
        return res;
    }


}
