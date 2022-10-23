package io.codiga.analyzer.ast.languages.python;

import io.codiga.analyzer.ast.vm.ExecutionEnvironmentBuilder;
import io.codiga.analyzer.ast.vm.ExecutionResult;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.EntityChecked;
import io.codiga.model.RuleType;
import io.codiga.model.ast.common.FunctionCall;
import io.codiga.model.ast.python.*;
import io.codiga.model.error.Violation;
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
import static io.codiga.analyzer.ast.languages.python.PythonAstUtils.isFunctionCall;
import static io.codiga.analyzer.ast.languages.python.SimpleStmtToAssignment.isAssignment;
import static io.codiga.analyzer.ast.languages.python.SimpleStmtToAssignment.transformSimpleStmtToPythonAssignment;
import static io.codiga.analyzer.ast.languages.python.TryStmtToTryStatement.transformStmtToTryStatement;

public class CodigaVisitor extends PythonParserBaseVisitor<List<Violation>> {

    List<Violation> violations;
    private AnalyzerRule analyzerRule;
    private PythonParser.RootContext root;
    private StringBuffer output;
    private String filename;
    private boolean logOutput;
    private String code;
    private Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);

    public CodigaVisitor(AnalyzerRule rule, String code, String filename, boolean logOutput) {
        this.analyzerRule = rule;
        this.root = null;
        this.logOutput = logOutput;
        this.code = code;
        this.violations = new ArrayList<>();
        this.output = new StringBuffer();
        this.filename = filename;
    }

    public String getOutput() {
        String finalString = this.output.toString();
        if (finalString.isEmpty()) {
            return null;
        }
        return finalString;
    }

    public List<Violation> getViolations() {
        return this.violations;
    }

    @Override
    public List<Violation> visitRoot(PythonParser.RootContext ctx) {
        this.root = ctx;
        return visitChildren(ctx);
    }

    @Override
    public List<Violation> visitFrom_stmt(PythonParser.From_stmtContext ctx) {
        if (analyzerRule.ruleType() == RuleType.AST_CHECK && analyzerRule.entityChecked() == EntityChecked.IMPORT_STATEMENT) {
            Optional<FromStatement> fromStatementOptional = transformFromStmtToFromStatement(ctx, this.root);
            fromStatementOptional.ifPresent(this::executeRule);
        }

        return visitChildren(ctx);
    }

    @Override
    public List<Violation> visitSimple_stmt(PythonParser.Simple_stmtContext ctx) {

        if (analyzerRule.ruleType() == RuleType.AST_CHECK && analyzerRule.entityChecked() == EntityChecked.ASSIGNMENT) {
            if (isAssignment(ctx)) {
                transformSimpleStmtToPythonAssignment(ctx, this.root).ifPresent(this::executeRule);
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public List<Violation> visitImport_stmt(PythonParser.Import_stmtContext ctx) {
        if (analyzerRule.ruleType() == RuleType.AST_CHECK && analyzerRule.entityChecked() == EntityChecked.IMPORT_STATEMENT) {
            Optional<ImportStatement> importStatementOptional = transformImportStmtToImportStatement(ctx, root);
            importStatementOptional.ifPresent(this::executeRule);
        }

        return visitChildren(ctx);
    }

    @Override
    public List<Violation> visitIf_stmt(PythonParser.If_stmtContext ctx) {
        if (analyzerRule.ruleType() == RuleType.AST_CHECK && analyzerRule.entityChecked() == EntityChecked.IF_STATEMENT) {
            Optional<PythonIfStatement> ifStatementOptional = transformIfStatement(ctx, root);
            ifStatementOptional.ifPresent(this::executeRule);
        }

        return visitChildren(ctx);
    }


    private void executeRule(Object rootObject) {
        ExecutionResult executionResult = new ExecutionEnvironmentBuilder()
            .setCode(code)
            .setRootObject(rootObject)
            .setLogOutput(logOutput)
            .setFilename(filename)
            .setRuleCode(analyzerRule.code())
            .createExecutionEnvironment()
            .execute();

        if (executionResult.getOutput() != null) {
            this.output.append(executionResult.getOutput());
        }
        this.violations.addAll(executionResult.getViolations());
    }


    @Override
    public List<Violation> visitTry_stmt(PythonParser.Try_stmtContext ctx) {
        if (analyzerRule.ruleType() == RuleType.AST_CHECK && analyzerRule.entityChecked() == EntityChecked.TRY_BLOCK) {
            Optional<TryStatement> tryStatementOptional = transformStmtToTryStatement(ctx, root);
            tryStatementOptional.ifPresent(this::executeRule);
        }

        return visitChildren(ctx);
    }


    @Override
    public List<Violation> visitFor_stmt(PythonParser.For_stmtContext ctx) {
        if (analyzerRule.ruleType() == RuleType.AST_CHECK && analyzerRule.entityChecked() == EntityChecked.FOR_LOOP) {
            Optional<PythonForStatement> forStatementOptional = transformForStatement(ctx, root);
            forStatementOptional.ifPresent(this::executeRule);
        }

        return visitChildren(ctx);
    }


    @Override
    public List<Violation> visitClass_or_func_def_stmt(PythonParser.Class_or_func_def_stmtContext ctx) {
        if (ctx.funcdef() != null && analyzerRule.ruleType() == RuleType.AST_CHECK && analyzerRule.entityChecked() == EntityChecked.FUNCTION_DEFINITION) {
            Optional<PythonFunctionDefinition> functionDefinitionOptional = transformFuncDefToFunctionDefinition(ctx, this.root);
            functionDefinitionOptional.ifPresent(this::executeRule);
        }
        return visitChildren(ctx);
    }


    @Override
    public List<Violation> visitExpr(PythonParser.ExprContext ctx) {
        if (analyzerRule.ruleType() == RuleType.AST_CHECK && analyzerRule.entityChecked() == EntityChecked.FUNCTION_CALL && isFunctionCall(ctx)) {
            Optional<FunctionCall> functionCallOptional = transformExprToFunctionCall(ctx, this.root);
            functionCallOptional.ifPresent(this::executeRule);
        }

        return visitChildren(ctx);
    }


}
