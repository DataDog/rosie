package io.codiga.analyzer.ast.languages.python.transformations;

import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.python.PythonList;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.codiga.analyzer.ast.languages.python.PythonAstUtils.isFunctionCall;
import static io.codiga.analyzer.ast.languages.python.transformations.AtomToPythonString.transformAtomToPythonString;
import static io.codiga.analyzer.ast.languages.python.transformations.ExprToFunctionCall.transformExprToFunctionCall;

public class SimpleStmtToAssignment {

    private static final Logger logger = LoggerFactory.getLogger(SimpleStmtToAssignment.class);

    public static boolean isAssignment(ParseTree originalContext) {

        if (!(originalContext instanceof PythonParser.Simple_stmtContext)) {
            return false;
        }

        PythonParser.Simple_stmtContext ctx = (PythonParser.Simple_stmtContext) originalContext;

        if (ctx.small_stmt() == null) {
            return false;
        }

        if (ctx.small_stmt().isEmpty() || ctx.small_stmt().size() > 1) {
            return false;
        }

        List<ParseTree> children = ctx.small_stmt().get(0).children;

        if (children == null || children.size() != 2) {
            return false;
        }

        return ((children.get(0) instanceof PythonParser.Testlist_star_exprContext) &&
            (children.get(1) instanceof PythonParser.Assign_partContext));
    }

    private static Optional<? extends AstElement> getAstElementFromTest(PythonParser.TestContext test, PythonParser.RootContext root) {
        if (test == null) {
            return Optional.empty();
        }
        if (test.logical_test() == null || test.logical_test().size() != 1) {
            return Optional.empty();
        }
        PythonParser.Logical_testContext logical_testContext = test.logical_test().get(0);
        if (logical_testContext.comparison() == null) {
            return Optional.empty();
        }
        if (logical_testContext.comparison().expr() == null) {
            return Optional.empty();
        }
        if (isFunctionCall(logical_testContext.comparison().expr())) {
            return transformExprToFunctionCall(logical_testContext.comparison().expr(), root);
        }
        if (logical_testContext.comparison().expr().atom() != null) {
            return transformAtomToPythonString(logical_testContext.comparison().expr().atom(), root);
        }

        return Optional.empty();
    }

    public static Optional<Assignment> transformSimpleStmtToPythonAssignment(PythonParser.Simple_stmtContext ctx, PythonParser.RootContext root) {
        if (ctx.small_stmt() == null || ctx.small_stmt().size() != 1) {
            return Optional.empty();
        }
        Optional<PythonParser.Small_stmtContext> small_stmtContextOptional = ctx.small_stmt().stream().findFirst();
        if (small_stmtContextOptional.isEmpty()) {
            return Optional.empty();
        }


        List<ParseTree> children = ctx.small_stmt().get(0).children;
        if (!(children.get(0) instanceof PythonParser.Testlist_star_exprContext) |
            !(children.get(1) instanceof PythonParser.Assign_partContext)) {
            return Optional.empty();
        }

        PythonParser.Testlist_star_exprContext leftSideStarList = (PythonParser.Testlist_star_exprContext) children.get(0);
        PythonParser.Assign_partContext assignPart = (PythonParser.Assign_partContext) children.get(1);
        if (assignPart.testlist_star_expr() == null || assignPart.testlist_star_expr().size() != 1) {
            return Optional.empty();
        }
        PythonParser.Testlist_star_exprContext rightSideStarList = assignPart.testlist_star_expr().get(0);

        List<PythonParser.TestContext> leftTests = leftSideStarList.testlist() != null ? leftSideStarList.testlist().test() : leftSideStarList.test();
        List<PythonParser.TestContext> rightTests = rightSideStarList.testlist() != null ? rightSideStarList.testlist().test() : rightSideStarList.test();

        if (leftTests == null || rightTests == null) {
            return Optional.empty();
        }

        List<AstElement> leftElements = leftTests.stream().map(e -> getAstElementFromTest(e, root)).filter(v -> v.isPresent()).map(v -> v.get()).collect(Collectors.toList());
        List<AstElement> rightElements = rightTests.stream().map(e -> getAstElementFromTest(e, root)).filter(v -> v.isPresent()).map(v -> v.get()).collect(Collectors.toList());

        AstElement leftElement = null;
        AstElement rightElement = null;
        if (leftElements.size() > 1) {
            leftElement = new PythonList(leftElements, leftSideStarList, root);
        }
        if (leftElements.size() == 1) {
            leftElement = leftElements.get(0);
        }
        if (rightElements.size() > 1) {
            rightElement = new PythonList(rightElements, assignPart, root);
        }
        if (rightElements.size() == 1) {
            rightElement = rightElements.get(0);
        }

        return Optional.of(new Assignment(leftElement, rightElement, ctx, root));

    }
}
