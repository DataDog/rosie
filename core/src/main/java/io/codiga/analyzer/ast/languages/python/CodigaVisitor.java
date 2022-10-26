package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.common.FunctionCall;
import io.codiga.model.ast.python.*;
import io.codiga.parser.python.gen.PythonParser;
import io.codiga.parser.python.gen.PythonParserBaseVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.ExprToFunctionCall.transformExprToFunctionCall;
import static io.codiga.analyzer.ast.languages.python.ForStmtToForStatement.transformForStatement;
import static io.codiga.analyzer.ast.languages.python.FuncDefToFunctionDefinition.transformFuncDefToFunctionDefinition;
import static io.codiga.analyzer.ast.languages.python.IfStmtToIfStatement.transformIfStatement;
import static io.codiga.analyzer.ast.languages.python.ImportFromToFromStatement.transformFromStmtToFromStatement;
import static io.codiga.analyzer.ast.languages.python.ImportStmtToImportStatement.transformImportStmtToImportStatement;
import static io.codiga.analyzer.ast.languages.python.SimpleStmtToAssignment.isAssignment;
import static io.codiga.analyzer.ast.languages.python.SimpleStmtToAssignment.transformSimpleStmtToPythonAssignment;
import static io.codiga.analyzer.ast.languages.python.TryStmtToTryStatement.transformStmtToTryStatement;


/**
 * Main visitor. Visits the build tree, and build nodes for the AST.
 */
public class CodigaVisitor extends PythonParserBaseVisitor<Object> {

    List<Assignment> assignments;
    List<FromStatement> fromStatements;
    List<ImportStatement> importStatements;
    List<PythonIfStatement> ifStatements;
    List<TryStatement> tryStatements;
    List<PythonForStatement> forStatements;
    List<PythonFunctionDefinition> functionDefinitions;
    List<FunctionCall> functionCalls;
    private PythonParser.RootContext root;
    private Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);

    public CodigaVisitor() {
        assignments = new ArrayList<>();
        fromStatements = new ArrayList<>();
        importStatements = new ArrayList<>();
        ifStatements = new ArrayList<>();
        tryStatements = new ArrayList<>();
        forStatements = new ArrayList<>();
        functionDefinitions = new ArrayList<>();
        functionCalls = new ArrayList<>();
    }


    @Override
    public Object visitRoot(PythonParser.RootContext ctx) {
        this.root = ctx;
        return visitChildren(ctx);
    }

    @Override
    public Object visitFrom_stmt(PythonParser.From_stmtContext ctx) {
        Optional<FromStatement> fromStatementOptional = transformFromStmtToFromStatement(ctx, this.root);
        fromStatementOptional.ifPresent(v -> fromStatements.add(v));
        return visitChildren(ctx);
    }

    @Override
    public Object visitSimple_stmt(PythonParser.Simple_stmtContext ctx) {
        if (isAssignment(ctx)) {
            transformSimpleStmtToPythonAssignment(ctx, this.root).ifPresent(v -> assignments.add(v));
        }
        return visitChildren(ctx);
    }

    @Override
    public Object visitImport_stmt(PythonParser.Import_stmtContext ctx) {
        Optional<ImportStatement> importStatementOptional = transformImportStmtToImportStatement(ctx, root);
        importStatementOptional.ifPresent(v -> importStatements.add(v));
        return visitChildren(ctx);
    }

    @Override
    public Object visitIf_stmt(PythonParser.If_stmtContext ctx) {
        Optional<PythonIfStatement> ifStatementOptional = transformIfStatement(ctx, root);
        ifStatementOptional.ifPresent(v -> ifStatements.add(v));
        return visitChildren(ctx);
    }


    @Override
    public Object visitTry_stmt(PythonParser.Try_stmtContext ctx) {
        Optional<TryStatement> tryStatementOptional = transformStmtToTryStatement(ctx, root);
        tryStatementOptional.ifPresent(v -> tryStatements.add(v));
        return visitChildren(ctx);
    }


    @Override
    public Object visitFor_stmt(PythonParser.For_stmtContext ctx) {
        Optional<PythonForStatement> forStatementOptional = transformForStatement(ctx, root);
        forStatementOptional.ifPresent(v -> forStatements.add(v));
        return visitChildren(ctx);
    }


    @Override
    public Object visitClass_or_func_def_stmt(PythonParser.Class_or_func_def_stmtContext ctx) {
        Optional<PythonFunctionDefinition> functionDefinitionOptional = transformFuncDefToFunctionDefinition(ctx, this.root);
        functionDefinitionOptional.ifPresent(v -> functionDefinitions.add(v));
        return visitChildren(ctx);
    }


    @Override
    public Object visitExpr(PythonParser.ExprContext ctx) {
        Optional<FunctionCall> functionCallOptional = transformExprToFunctionCall(ctx, this.root);
        functionCallOptional.ifPresent(v -> functionCalls.add(v));
        return visitChildren(ctx);
    }


}
