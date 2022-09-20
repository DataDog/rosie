package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.python.*;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.ComparisontoPythonComparison.transformComparisonToPythonComparison;

public class IfStmtToIfStatement {

    private static final Logger logger = LoggerFactory.getLogger(IfStmtToIfStatement.class);


    private static Optional<PythonComparison> getPythonConditionFromTestContext(PythonParser.TestContext testContext, PythonParser.RootContext root) {
        if (testContext != null && testContext.logical_test() != null && testContext.logical_test().size() == 1) {
            PythonParser.ComparisonContext comparisonContext = testContext.logical_test().get(0).comparison();
            return transformComparisonToPythonComparison(comparisonContext, root);
        }
        return Optional.empty();
    }


    public static Optional<PythonIfStatement> transformIfStatement(PythonParser.If_stmtContext if_stmtContext,
                                                                   PythonParser.RootContext root) {
        List<PythonElifStatement> elifStatements = new ArrayList<>();
        PythonElseStatement elseStatement = null;
        PythonComparison ifComparison = null;
        Optional<PythonComparison> ifComparisonOptional = getPythonConditionFromTestContext(if_stmtContext.test(), root);

        if (ifComparisonOptional.isPresent()) {
            ifComparison = ifComparisonOptional.get();
        }

        for (PythonParser.Elif_clauseContext elif_clauseContext : if_stmtContext.elif_clause()) {
            Optional<PythonComparison> comparison = getPythonConditionFromTestContext(elif_clauseContext.test(), root);
            if (comparison.isPresent()) {
                PythonElifStatement pythonElifStatement = new PythonElifStatement(
                    comparison.get(),
                    new PythonString(elif_clauseContext.suite().getText(), elif_clauseContext, root),
                    elif_clauseContext,
                    root);
                elifStatements.add(pythonElifStatement);
            }
        }
        if (if_stmtContext.else_clause() != null) {
            elseStatement = new PythonElseStatement(
                new PythonString(if_stmtContext.else_clause().suite().getText(), if_stmtContext.else_clause().suite(), root),
                if_stmtContext.else_clause(),
                root
            );
        }

        return Optional.of(new PythonIfStatement(
            ifComparison,
            new PythonString(if_stmtContext.suite().getText(), if_stmtContext.suite(), root),
            elifStatements,
            elseStatement,
            if_stmtContext,
            root
        ));
    }
}
