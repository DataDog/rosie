package io.codiga.parser.antlr.python.transformations;

import io.codiga.model.ast.python.PythonComparison;
import io.codiga.model.ast.python.PythonElifStatement;
import io.codiga.model.ast.python.PythonElseStatement;
import io.codiga.model.ast.python.PythonIfStatement;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.PythonAstUtils.getPythonComparisonFromTestContext;
import static io.codiga.parser.antlr.python.transformations.SuiteTransformation.transformSuiteToAstElement;

public class IfStmtToIfStatement {

    private static final Logger logger = LoggerFactory.getLogger(IfStmtToIfStatement.class);


    public static Optional<PythonIfStatement> transformIfStatement(PythonParser.If_stmtContext if_stmtContext,
                                                                   PythonParser.RootContext root) {
        List<PythonElifStatement> elifStatements = new ArrayList<>();
        PythonElseStatement elseStatement = null;
        PythonComparison ifComparison = null;
        Optional<PythonComparison> ifComparisonOptional = getPythonComparisonFromTestContext(if_stmtContext.test(), root);

        if (ifComparisonOptional.isPresent()) {
            ifComparison = ifComparisonOptional.get();
        }

        for (PythonParser.Elif_clauseContext elif_clauseContext : if_stmtContext.elif_clause()) {
            Optional<PythonComparison> comparison = getPythonComparisonFromTestContext(elif_clauseContext.test(), root);
            if (comparison.isPresent() && elif_clauseContext.suite() != null) {
                PythonElifStatement pythonElifStatement = new PythonElifStatement(
                    comparison.get(),
                    transformSuiteToAstElement(elif_clauseContext.suite(), root).orElse(null),
                    elif_clauseContext,
                    root);
                elifStatements.add(pythonElifStatement);
            }
        }
        if (if_stmtContext.else_clause() != null && if_stmtContext.else_clause().suite() != null) {
            elseStatement = new PythonElseStatement(
                transformSuiteToAstElement(if_stmtContext.else_clause().suite(), root).orElse(null),
                if_stmtContext.else_clause(),
                root
            );
        }

        if (if_stmtContext.suite() == null) {
            return Optional.empty();
        }

        var statementsOptional = transformSuiteToAstElement(if_stmtContext.suite(), root);
        return Optional.of(new PythonIfStatement(
            ifComparison,
            statementsOptional.orElse(null),
            elifStatements,
            elseStatement,
            if_stmtContext,
            root
        ));
    }
}
