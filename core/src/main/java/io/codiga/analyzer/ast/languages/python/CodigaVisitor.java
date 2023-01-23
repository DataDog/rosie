package io.codiga.analyzer.ast.languages.python;

import datadog.trace.api.Trace;
import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.FunctionCall;
import io.codiga.model.ast.python.*;
import io.codiga.model.context.PythonNodeContext;
import io.codiga.parser.python.gen.PythonParser;
import io.codiga.parser.python.gen.PythonParserBaseVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import static io.codiga.analyzer.ast.languages.python.transformations.ClassOrFuncDefTransformation.isClassDefinition;
import static io.codiga.analyzer.ast.languages.python.transformations.ClassOrFuncDefTransformation.transformClassOrFuncDefToClassDefinition;
import static io.codiga.analyzer.ast.languages.python.transformations.ExprStmtTransformation.isAssignment;
import static io.codiga.analyzer.ast.languages.python.transformations.ExprStmtTransformation.transformExprStmtToAssignment;
import static io.codiga.analyzer.ast.languages.python.transformations.ExprToFunctionCall.transformExprToFunctionCall;
import static io.codiga.analyzer.ast.languages.python.transformations.ForStmtToForStatement.transformForStatement;
import static io.codiga.analyzer.ast.languages.python.transformations.FuncDefToFunctionDefinition.isFunctionDefinition;
import static io.codiga.analyzer.ast.languages.python.transformations.FuncDefToFunctionDefinition.transformFuncDefToFunctionDefinition;
import static io.codiga.analyzer.ast.languages.python.transformations.IfStmtToIfStatement.transformIfStatement;
import static io.codiga.analyzer.ast.languages.python.transformations.ImportFromToFromStatement.transformFromStmtToFromStatement;
import static io.codiga.analyzer.ast.languages.python.transformations.ImportStmtToImportStatement.transformImportStmtToImportStatement;
import static io.codiga.analyzer.ast.languages.python.transformations.TryStmtToTryStatement.transformStmtToTryStatement;


/**
 * Main visitor. Visits the build tree, and build nodes for the AST.
 */
public class CodigaVisitor extends PythonParserBaseVisitor<Object> {

    private final String code;
    private final Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);
    // To build the context
    Stack<PythonFunctionDefinition> visitedFunctionDefinitions;
    Stack<PythonClassDefinition> visitedClassDefinitions;
    Stack<PythonIfStatement> visitedIfStatements;
    Stack<TryStatement> visitedTryStatements;
    List<AstElement> visitedImportStatements;
    // List of all AST elements
    List<Assignment> assignments;
    List<FromStatement> fromStatements;
    List<ImportStatement> importStatements;
    List<PythonIfStatement> ifStatements;
    List<TryStatement> tryStatements;
    List<PythonForStatement> forStatements;
    List<PythonFunctionDefinition> functionDefinitions;
    List<FunctionCall> functionCalls;
    List<PythonClassDefinition> classDefinitions;
    private PythonParser.RootContext root;

    public CodigaVisitor(String code) {
        this.code = code;

        // Initialize the list of all elements being visited
        assignments = new ArrayList<>();
        fromStatements = new ArrayList<>();
        importStatements = new ArrayList<>();
        ifStatements = new ArrayList<>();
        tryStatements = new ArrayList<>();
        forStatements = new ArrayList<>();
        functionDefinitions = new ArrayList<>();
        functionCalls = new ArrayList<>();
        classDefinitions = new ArrayList<>();

        // Initialize the visited elements
        visitedFunctionDefinitions = new Stack();
        visitedIfStatements = new Stack<>();
        visitedTryStatements = new Stack<>();
        visitedImportStatements = new ArrayList<>();
        visitedClassDefinitions = new Stack();
    }

    private PythonNodeContext buildContext() {
        PythonNodeContext res = PythonNodeContext.buildPythonNodeContext()
            .currentFunction(visitedFunctionDefinitions.isEmpty() ? null : visitedFunctionDefinitions.lastElement())
            .currentTryBlock(visitedTryStatements.size() > 0 ? visitedTryStatements.lastElement() : null)
            .currentClass(visitedClassDefinitions.size() > 0 ? visitedClassDefinitions.lastElement() : null)
            .code(this.code)
            .importsList(visitedImportStatements)
            .assignmentsList(assignments)
            .build();
        return res;
    }


    @Trace(operationName = "CodigaVisitor.visitRoot")
    @Override
    public Object visitRoot(PythonParser.RootContext ctx) {
        this.root = ctx;
        return visitChildren(ctx);
    }

    @Override
    public Object visitFrom_stmt(PythonParser.From_stmtContext ctx) {
        Optional<FromStatement> fromStatementOptional = transformFromStmtToFromStatement(ctx, this.root);

        fromStatementOptional.ifPresent(v -> {
            v.setContext(buildContext());
            fromStatements.add(v);
            visitedImportStatements.add(v);
        });

        return visitChildren(ctx);
    }

    @Override
    public Object visitExpr_stmt(PythonParser.Expr_stmtContext ctx) {
        if (isAssignment(ctx)) {
            transformExprStmtToAssignment(ctx, this.root).ifPresent(v -> {
                // Set the context of the AST Element
                v.setContext(buildContext());
                assignments.add(v);

                Optional<PythonFunctionDefinition> functionDefinition = visitedFunctionDefinitions.empty() ? Optional.empty() : Optional.of(visitedFunctionDefinitions.lastElement());
                if (functionDefinition.isPresent()) {

                    functionDefinition.get().addAssignment(v);
                }
            });
        }
        return visitChildren(ctx);
    }

    @Override
    public Object visitImport_stmt(PythonParser.Import_stmtContext ctx) {
        Optional<ImportStatement> importStatementOptional = transformImportStmtToImportStatement(ctx, root);
        importStatementOptional.ifPresent(v -> {

            v.setContext(buildContext());
            importStatements.add(v);
            visitedImportStatements.add(v);

        });
        return visitChildren(ctx);
    }

    @Override
    public Object visitIf_stmt(PythonParser.If_stmtContext ctx) {
        Optional<PythonIfStatement> ifStatementOptional = transformIfStatement(ctx, root);
        if (ifStatementOptional.isPresent()) {
            PythonIfStatement ifStatement = ifStatementOptional.get();
            ifStatement.setContext(buildContext());
            ifStatements.add(ifStatement);
            visitedIfStatements.push(ifStatement);
            Object res = visitChildren(ctx);
            visitedIfStatements.pop();
            return res;
        } else {
            return visitChildren(ctx);
        }
    }


    @Override
    public Object visitTry_stmt(PythonParser.Try_stmtContext ctx) {
        Optional<TryStatement> tryStatementOptional = transformStmtToTryStatement(ctx, root);
        if (tryStatementOptional.isPresent()) {
            TryStatement tryStatement = tryStatementOptional.get();
            tryStatement.setContext(buildContext());
            tryStatements.add(tryStatement);
            visitedTryStatements.push(tryStatement);
            Object res = visitChildren(ctx);
            visitedTryStatements.pop();
            return res;
        } else {
            return visitChildren(ctx);
        }
    }


    @Override
    public Object visitFor_stmt(PythonParser.For_stmtContext ctx) {
        Optional<PythonForStatement> forStatementOptional = transformForStatement(ctx, root);
        forStatementOptional.ifPresent(v -> {
            v.setContext(buildContext());
            forStatements.add(v);
        });
        return visitChildren(ctx);
    }


    @Override
    public Object visitClass_or_func_def_stmt(PythonParser.Class_or_func_def_stmtContext ctx) {
        if (isFunctionDefinition(ctx)) {

            Optional<PythonFunctionDefinition> functionDefinitionOptional = transformFuncDefToFunctionDefinition(ctx, this.root);

            if (functionDefinitionOptional.isPresent()) {
                PythonFunctionDefinition functionDefinition = functionDefinitionOptional.get();
                functionDefinition.setContext(buildContext());
                functionDefinitions.add(functionDefinition);
                visitedFunctionDefinitions.push(functionDefinition);
                Object res = visitChildren(ctx);
                visitedFunctionDefinitions.pop();
                return res;
            }
        }

        if (isClassDefinition(ctx)) {
            Optional<PythonClassDefinition> classDefinitionOptional = transformClassOrFuncDefToClassDefinition(ctx, this.root);

            if (classDefinitionOptional.isPresent()) {
                PythonClassDefinition classDefinition = classDefinitionOptional.get();
                classDefinition.setContext(buildContext());
                classDefinitions.add(classDefinition);
                visitedClassDefinitions.push(classDefinition);
                Object res = visitChildren(ctx);
                visitedClassDefinitions.pop();
                return res;
            }
        }

        return visitChildren(ctx);
    }


    @Override
    public Object visitExpr(PythonParser.ExprContext ctx) {
        Optional<PythonFunctionCall> functionCallOptional = transformExprToFunctionCall(ctx, this.root);
        functionCallOptional.ifPresent(v -> {
            v.setContext(buildContext());
            functionCalls.add(v);
        });
        return visitChildren(ctx);
    }


}
