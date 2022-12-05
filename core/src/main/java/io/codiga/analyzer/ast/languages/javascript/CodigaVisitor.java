package io.codiga.analyzer.ast.languages.javascript;

import datadog.trace.api.Trace;
import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.FunctionCall;
import io.codiga.model.ast.javascript.JavaScriptImport;
import io.codiga.model.context.JavaScriptNodeContext;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import io.codiga.parser.javascript.gen.JavaScriptParserBaseVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptFunctionCallTransformation.isFunctionCall;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptFunctionCallTransformation.transformArgumentsExpressionToFunctionCall;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptImportStatementToImport.transformImportStatementToImport;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptSingleExpressionTransformation.transformJavaScriptAssignmentExpressionToAssignment;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptVariableDeclarationToAssignment.transformVariableDeclartionToAssignment;


/**
 * Main visitor. Visits the build tree, and build nodes for the AST.
 */
public class CodigaVisitor extends JavaScriptParserBaseVisitor<Object> {

    private final String code;
    private final Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);
    // To build the context
//    Stack<PythonFunctionDefinition> visitedFunctionDefinitions;
//    Stack<PythonClassDefinition> visitedClassDefinitions;
//    Stack<PythonIfStatement> visitedIfStatements;
//    Stack<TryStatement> visitedTryStatements;
    List<AstElement> visitedImportStatements;


    // List of all AST elements
    List<Assignment> assignments;
    //    List<FromStatement> fromStatements;
    List<JavaScriptImport> importStatements;
    //    List<PythonIfStatement> ifStatements;
//    List<TryStatement> tryStatements;
//    List<PythonForStatement> forStatements;
//    List<PythonFunctionDefinition> functionDefinitions;
    List<FunctionCall> functionCalls;
    //    List<PythonClassDefinition> classDefinitions;
    private JavaScriptParser.ProgramContext root;

    public CodigaVisitor(String code) {
        this.code = code;

        // Initialize the list of all elements being visited
        functionCalls = new ArrayList<>();
        importStatements = new ArrayList<>();

        // Initialize the visited elements
        visitedImportStatements = new ArrayList<>();
    }

    @Override
    public Object visitProgram(JavaScriptParser.ProgramContext ctx) {
        this.root = ctx;
        return visitChildren(ctx);
    }

    @Override
    public Object visitArgumentsExpression(JavaScriptParser.ArgumentsExpressionContext ctx) {
        if (isFunctionCall(ctx)) {
            Optional<FunctionCall> functionCallOptional = transformArgumentsExpressionToFunctionCall(ctx, root);
            if (functionCallOptional.isPresent()) {
                this.functionCalls.add(functionCallOptional.get());
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public Object visitImportStatement(JavaScriptParser.ImportStatementContext ctx) {
        Optional<JavaScriptImport> javaScriptImport = transformImportStatementToImport(ctx, root);
        if (javaScriptImport.isPresent()) {
            this.visitedImportStatements.add(javaScriptImport.get());
            this.importStatements.add(javaScriptImport.get());
        }
        return visitChildren(ctx);
    }

    @Override
    public Object visitVariableDeclaration(JavaScriptParser.VariableDeclarationContext ctx) {
        Optional<Assignment> assignmentOptional = transformVariableDeclartionToAssignment(ctx, root);
        if (assignmentOptional.isPresent()) {
            this.assignments.add(assignmentOptional.get());
        }
        return visitChildren(ctx);
    }


    @Override
    public Object visitAssignmentExpression(JavaScriptParser.AssignmentExpressionContext ctx) {
        Optional<Assignment> assignmentOptional = transformJavaScriptAssignmentExpressionToAssignment(ctx, root);
        if (assignmentOptional.isPresent()) {
            this.assignments.add(assignmentOptional.get());
        }
        return visitChildren(ctx);
    }


    @Trace(operationName = "CodigaJavaScriptVisitor.buildContext")
    private JavaScriptNodeContext buildContext() {
        JavaScriptNodeContext res = JavaScriptNodeContext.buildJavaScriptNodeContext()
//            .currentFunction(visitedFunctionDefinitions.isEmpty() ? null : visitedFunctionDefinitions.lastElement())
//            .currentTryBlock(visitedTryStatements.size() > 0 ? visitedTryStatements.lastElement() : null)
//            .currentClass(visitedClassDefinitions.size() > 0 ? visitedClassDefinitions.lastElement() : null)
            .code(this.code)
            .importsList(visitedImportStatements)
            .build();
        return res;
    }


}
