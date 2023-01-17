package io.codiga.analyzer.ast.languages.typescript;

import datadog.trace.api.Trace;
import io.codiga.model.ast.common.*;
import io.codiga.model.ast.javascript.JavaScriptHtmlData;
import io.codiga.model.ast.javascript.JavaScriptHtmlElement;
import io.codiga.model.ast.javascript.JavaScriptImport;
import io.codiga.model.ast.javascript.JavaScriptTryCatchStatement;
import io.codiga.model.ast.typescript.TypeScriptInterface;
import io.codiga.model.context.JavaScriptNodeContext;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import io.codiga.parser.typescript.gen.TypeScriptParserBaseVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptAssignmentExpression.transformAssignmentExpressionToAssignment;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptClassDeclarationToClass.transformClassDeclaration;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptForStatement.transformForStatementToForStatement;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptFunctionCallTransformation.transformArgumentsExpressionToFunctionCall;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptFunctionDeclarationToFunctionDefinition.transformFunctionDeclarationToFunctionDefinition;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptHtmlCharDataTransformation.transformTypescriptHtmlCharData;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptHtmlElementTransformation.transformTypeScriptHtmlElement;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIdentifierExpressionTransformation.transformIdentifierExpressionToFunctionCall;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIfStatementToIfStatement.transformIfStatementToIfStatement;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptImportStatementToImport.transformImportStatementToImport;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptInterfaceTransformation.transformInterfaceDeclaration;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptTernaryExpressionToIfStatement.transformTernaryExpressionToIfStatement;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptTryStatement.transformTryStatementToTryCatchStatement;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptVariableDeclaration.transformVariableDeclarationToAssignment;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptVariableDeclaration.transformVariableDeclarationToFunctionCall;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptVariableStatement.transformVariableStatementToVariableDeclaration;


/**
 * Main visitor. Visits the build tree, and build nodes for the AST.
 */
public class CodigaVisitor extends TypeScriptParserBaseVisitor<Object> {

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
    List<VariableDeclaration> variableDeclarations;
    List<IfStatement> ifStatements;
    List<JavaScriptTryCatchStatement> tryStatements;
    List<TypeScriptInterface> typeScriptInterfaces;
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
        variableDeclarations = new ArrayList<>();
        typeScriptInterfaces = new ArrayList<>();

        // Initialize the visited elements
        visitedImportStatements = new ArrayList<>();
        visitedFunctionDefinitions = new Stack<>();
        visitedClassDefinitions = new Stack<>();
        visitedIfStatements = new Stack<>();
        visitedForStatements = new Stack<>();
        visitedHtmlElements = new Stack<>();
        visitedFunctionCalls = new Stack<>();
    }

    @Override
    public Object visitProgram(TypeScriptParser.ProgramContext ctx) {
        this.root = ctx;
        return visitChildren(ctx);
    }

    @Override
    public Object visitVariableStatement(TypeScriptParser.VariableStatementContext ctx) {
        List<VariableDeclaration> variableDeclarationList = transformVariableStatementToVariableDeclaration(ctx, root);
        if (variableDeclarationList.size() > 0) {
            this.variableDeclarations.addAll(variableDeclarationList);
        }
        return visitChildren(ctx);
    }


    @Override
    public Object visitVariableDeclaration(TypeScriptParser.VariableDeclarationContext ctx) {
        Optional<Assignment> assignmentOptional = transformVariableDeclarationToAssignment(ctx, root);

        // Assignment
        if (assignmentOptional.isPresent() && ctx.Assign() != null) {
            Assignment assignment = assignmentOptional.get();
            assignment.setContext(buildContext());
            this.assignments.add(assignment);
        }

        Optional<FunctionCall> functionCallOptional = transformVariableDeclarationToFunctionCall(ctx, root);
        if (functionCallOptional.isPresent()) {
            FunctionCall functionCall = functionCallOptional.get();
            functionCall.setContext(buildContext());
            this.functionCalls.add(functionCall);
        }

        return visitChildren(ctx);
    }


    @Override
    public Object visitFunctionDeclaration(TypeScriptParser.FunctionDeclarationContext ctx) {
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
    public Object visitAssignmentExpression(TypeScriptParser.AssignmentExpressionContext ctx) {
        Optional<Assignment> assignmentOptional = transformAssignmentExpressionToAssignment(ctx, root);
        if (assignmentOptional.isPresent()) {
            Assignment assignment = assignmentOptional.get();
            assignment.setContext(buildContext());
            this.assignments.add(assignment);
        }
        return visitChildren(ctx);
    }


    @Override
    public Object visitForStatement(TypeScriptParser.ForStatementContext ctx) {
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
    public Object visitTernaryExpression(TypeScriptParser.TernaryExpressionContext ctx) {
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
    public Object visitHtmlElement(TypeScriptParser.HtmlElementContext ctx) {
        Optional<JavaScriptHtmlElement> htmlElementOptional = transformTypeScriptHtmlElement(ctx, root);

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
    public Object visitImportStatement(TypeScriptParser.ImportStatementContext ctx) {
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
    public Object visitHtmlChardata(TypeScriptParser.HtmlChardataContext ctx) {

        Optional<JavaScriptHtmlData> htmlDataOptional = transformTypescriptHtmlCharData(ctx, root);

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

    @Override
    public Object visitArgumentsExpression(TypeScriptParser.ArgumentsExpressionContext ctx) {

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
    public Object visitClassDeclaration(TypeScriptParser.ClassDeclarationContext ctx) {
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
    public Object visitIdentifierExpression(TypeScriptParser.IdentifierExpressionContext ctx) {
        Optional<FunctionCall> functionCallOptional = transformIdentifierExpressionToFunctionCall(ctx, root);
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
    public Object visitIfStatement(TypeScriptParser.IfStatementContext ctx) {
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
    public Object visitTryStatement(TypeScriptParser.TryStatementContext ctx) {
        Optional<JavaScriptTryCatchStatement> tryCatchStatementOptional = transformTryStatementToTryCatchStatement(ctx, root);
        if (tryCatchStatementOptional.isPresent()) {
            JavaScriptTryCatchStatement javaScriptTryCatchStatement = tryCatchStatementOptional.get();
            javaScriptTryCatchStatement.setContext(buildContext());
            this.tryStatements.add(javaScriptTryCatchStatement);
        }
        return visitChildren(ctx);
    }


    @Override
    public Object visitInterfaceDeclaration(TypeScriptParser.InterfaceDeclarationContext ctx) {
        Optional<TypeScriptInterface> typeScriptInterfaceOptional = transformInterfaceDeclaration(ctx, root);
        if (typeScriptInterfaceOptional.isPresent()) {
            TypeScriptInterface typeScriptInterface = typeScriptInterfaceOptional.get();
            typeScriptInterface.setContext(buildContext());
            this.typeScriptInterfaces.add(typeScriptInterface);
        }
        return visitChildren(ctx);
    }


    @Trace(operationName = "CodigaTypeScriptVisitor.buildContext")
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
