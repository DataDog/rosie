package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.python.PythonElseStatement;
import io.codiga.model.ast.python.PythonExpression;
import io.codiga.model.ast.python.PythonForStatement;
import io.codiga.model.ast.python.PythonString;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.codiga.analyzer.ast.languages.python.ExprToPythonExpression.transformExprToPythonExpression;

public class ForStmtToForStatement {

    private static final Logger logger = LoggerFactory.getLogger(ForStmtToForStatement.class);


    public static Optional<PythonForStatement> transformForStatement(PythonParser.For_stmtContext for_stmtContext,
                                                                     PythonParser.RootContext root) {


        List<PythonExpression> variables = List.of();
        Optional<PythonString> list = Optional.empty();
        PythonString statements = null;
        PythonElseStatement elseStatement = null;

        if (for_stmtContext == null) {
            return Optional.empty();
        }


        //
        if (for_stmtContext.exprlist() != null && for_stmtContext.exprlist().expr() != null) {
            variables = for_stmtContext.exprlist().expr().stream()
                .map(expr -> transformExprToPythonExpression(expr, root))
                .filter(Optional::isPresent).map(Optional::get)
                .collect(Collectors.toList());
        }
        if (for_stmtContext.testlist() != null && for_stmtContext.testlist().test() != null && for_stmtContext.testlist().test().size() > 0) {
            list = Optional.of(new PythonString(for_stmtContext.testlist().getText(), for_stmtContext.testlist(), root));
        }

        if (for_stmtContext.suite() != null) {
            statements = new PythonString(for_stmtContext.suite().getText(), for_stmtContext.suite(), root);
        }
        if (for_stmtContext.else_clause() != null) {

            elseStatement = new PythonElseStatement(
                new PythonString(for_stmtContext.else_clause().getText(), for_stmtContext.else_clause(), root),
                for_stmtContext.else_clause(),
                root);
        }

        return Optional.of(new PythonForStatement(variables, list.orElse(null), statements, elseStatement, for_stmtContext, root));
    }
}
