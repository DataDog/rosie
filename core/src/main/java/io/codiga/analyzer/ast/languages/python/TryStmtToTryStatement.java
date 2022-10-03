package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.python.ExceptClause;
import io.codiga.model.ast.python.FinallyClause;
import io.codiga.model.ast.python.TryStatement;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TryStmtToTryStatement {

    private static final Logger LOGGER = LoggerFactory.getLogger(TryStmtToTryStatement.class);


    public static List<AstString> getAstStringFromTest(ParserRuleContext parserRuleContext, PythonParser.RootContext root) {
        if (parserRuleContext instanceof PythonParser.Logical_testContext) {
            PythonParser.Logical_testContext value = (PythonParser.Logical_testContext) parserRuleContext;
            return getAstStringFromTest(value.comparison(), root);
        }
        if (parserRuleContext instanceof PythonParser.ComparisonContext) {
            PythonParser.ComparisonContext value = (PythonParser.ComparisonContext) parserRuleContext;
            return getAstStringFromTest(value.expr(), root);
        }
        if (parserRuleContext instanceof PythonParser.ExprContext) {
            PythonParser.ExprContext value = (PythonParser.ExprContext) parserRuleContext;
            return getAstStringFromTest(value.atom(), root);
        }
        if (parserRuleContext instanceof PythonParser.AtomContext) {
            PythonParser.AtomContext value = (PythonParser.AtomContext) parserRuleContext;
            if (value.testlist_comp() != null) {
                return getAstStringFromTest(value.testlist_comp(), root);
            }
            if (value.name() != null) {
                return List.of(new AstString(value.getText(), value, root));
            }
        }
        if (parserRuleContext instanceof PythonParser.Testlist_compContext) {
            PythonParser.Testlist_compContext value = (PythonParser.Testlist_compContext) parserRuleContext;
            if (value.test() != null) {
                List<AstString> result = new ArrayList<>();
                for (PythonParser.TestContext testContext : value.test()) {
                    result.addAll(getAstStringFromTest(testContext, root));
                }
                return result;
            }
        }
        if (parserRuleContext instanceof PythonParser.TestContext) {
            PythonParser.TestContext value = (PythonParser.TestContext) parserRuleContext;
            List<AstString> result = new ArrayList<>();
            for (PythonParser.Logical_testContext testContext : value.logical_test()) {
                result.addAll(getAstStringFromTest(testContext, root));
            }
            return result;
        }
        return List.of();
    }

    public static Optional<TryStatement> transformStmtToTryStatement(PythonParser.Try_stmtContext ctx, PythonParser.RootContext root) {
        FinallyClause finallyClause = null;
        List<ExceptClause> exceptClauses = List.of();
        if (ctx.except_clause() != null && ctx.except_clause().size() > 0) {

            exceptClauses = ctx.except_clause().stream().map(exceptClause -> {
                List<AstString> exceptionNames = getAstStringFromTest(exceptClause.test(), root);
                AstString astString = null;
                if (ctx.except_clause().get(0).name() != null) {
                    astString = new AstString(ctx.except_clause().get(0).name().getText(), ctx.except_clause().get(0).name(), root);
                }
                return new ExceptClause(exceptionNames, astString, exceptClause, root);
            }).collect(Collectors.toList());


        }
        if (ctx.finally_clause() != null) {
            finallyClause = new FinallyClause(ctx.finally_clause(), root);
        }

        TryStatement tryStatement = new TryStatement(exceptClauses, finallyClause, ctx, root);
        return Optional.of(tryStatement);
    }
}
