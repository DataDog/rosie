package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.python.PythonComparison;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.ComparisontoPythonComparison.transformComparisonToPythonComparison;

public class PythonAstUtils {

    public static boolean isFunctionCall(ParseTree parseTree) {
        if (parseTree instanceof PythonParser.ExprContext) {
            PythonParser.ExprContext expr = (PythonParser.ExprContext) parseTree;
            if (expr.atom() != null && expr.trailer() != null) {
                if (expr.trailer().size() == 1) {
                    return (expr.trailer().get(0).arguments() != null);
                }
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
}
