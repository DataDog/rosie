package io.codiga.parser.antlr.python.transformations;

import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.python.PythonList;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.codiga.parser.antlr.python.transformations.PythonTestListStarExprTransformation.transformTestListStarExprToAstElement;
import static io.codiga.parser.antlr.python.transformations.PythonTestTransformation.transformTestToAstElement;
import static io.codiga.utils.Conversions.convertToAstElement;

public class ExprStmtTransformation {

    private static final Logger logger = LoggerFactory.getLogger(ExprStmtTransformation.class);


    public static boolean isAssignment(PythonParser.Expr_stmtContext ctx) {
        if (ctx == null || ctx.assign_part() == null) {
            return false;
        }

        return ctx.assign_part().ASSIGN() != null;
    }


    public static Optional<Assignment> transformExprStmtToAssignment(PythonParser.Expr_stmtContext ctx, PythonParser.RootContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.testlist_star_expr() == null || ctx.assign_part() == null) {
            return Optional.empty();
        }

        PythonParser.Testlist_star_exprContext leftSideStarList = ctx.testlist_star_expr();
        PythonParser.Assign_partContext assignPart = ctx.assign_part();

        if (assignPart.testlist_star_expr() == null || assignPart.testlist_star_expr().size() == 0) {
            return Optional.empty();
        }

        PythonParser.Testlist_star_exprContext rightSideStarList = assignPart.testlist_star_expr().get(0);

        List<PythonParser.TestContext> leftTests = leftSideStarList.testlist() != null ? leftSideStarList.testlist().test() : leftSideStarList.test();
        List<PythonParser.TestContext> rightTests = rightSideStarList.testlist() != null ? rightSideStarList.testlist().test() : rightSideStarList.test();

        if (leftTests == null || rightTests == null) {
            return Optional.empty();
        }

        List<AstElement> leftElements = leftTests.stream().map(e -> transformTestToAstElement(e, root)).filter(v -> v.isPresent()).map(v -> v.get()).collect(Collectors.toList());
        List<AstElement> rightElements = rightTests.stream().map(e -> transformTestToAstElement(e, root)).filter(v -> v.isPresent()).map(v -> v.get()).collect(Collectors.toList());

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


    public static Optional<? extends AstElement> transformExprStmtToAstElement(PythonParser.Expr_stmtContext ctx, PythonParser.RootContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (isAssignment(ctx)) {
            return convertToAstElement(transformExprStmtToAssignment(ctx, root));
        }

        if (ctx.assign_part() == null) {
            return transformTestListStarExprToAstElement(ctx.testlist_star_expr(), root);
        }

        return Optional.empty();
    }
}
