package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.python.PythonComparison;
import io.codiga.model.ast.python.PythonDecorator;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;
import java.util.Optional;

import static io.codiga.parser.antlr.python.transformations.ComparisontoPythonComparison.transformComparisonToPythonComparison;

public class PythonAstUtils {

    public static boolean isFunctionCall(ParseTree parseTree) {
        if (parseTree instanceof PythonParser.ExprContext expr) {
            if (expr.atom() != null && expr.trailer() != null) {
                if (expr.trailer().size() > 0) {
                    PythonParser.TrailerContext lastTrailer = expr.trailer().get(expr.trailer().size() - 1);
                    return (lastTrailer.arguments() != null && lastTrailer.arguments().OPEN_PAREN() != null && lastTrailer.arguments().CLOSE_PAREN() != null);
                }
            }
        }
        return false;
    }


    public static boolean isArrayOrDictReference(PythonParser.ExprContext exprContext) {
        if (exprContext.atom() != null && exprContext.trailer() != null) {
            if (exprContext.trailer().size() > 0) {
                PythonParser.TrailerContext lastTrailer = exprContext.trailer().get(exprContext.trailer().size() - 1);
                return (lastTrailer.arguments() != null && lastTrailer.arguments().OPEN_BRACKET() != null && lastTrailer.arguments().CLOSE_BRACKET() != null);
            }
        }

        return false;
    }

    public static Optional<PythonComparison> getPythonComparisonFromTestContext(PythonParser.TestContext testContext, PythonParser.RootContext root) {
        if (testContext != null && testContext.logical_test() != null && testContext.logical_test().size() == 1) {
            PythonParser.ComparisonContext comparisonContext = testContext.logical_test().get(0).comparison();
            return transformComparisonToPythonComparison(comparisonContext, root);
        }
        return Optional.empty();
    }

    public static List<PythonDecorator> getDecoratorsForClassOrFunctionDefinition(PythonParser.Class_or_func_def_stmtContext ctx, PythonParser.RootContext root) {
        if (ctx.decorator() != null) {
            return ctx.decorator()
                .stream().map(decoratorContext -> PythonDecorator.fromArgumentContext(decoratorContext, root).orElse(null)).toList();
        }
        return List.of();
    }
}
