package io.codiga.parser.antlr.python.transformations;

import io.codiga.model.ast.python.PythonComparison;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.parser.antlr.python.transformations.ExprToPythonExpression.transformExprToPythonExpression;

public class ComparisontoPythonComparison {

    private static final Logger logger = LoggerFactory.getLogger(ComparisontoPythonComparison.class);


    private static String getComparisonOperatorString(PythonParser.ComparisonContext comparisonContext) {
        if (comparisonContext.EQUALS() != null) {
            return "==";
        }
        if (comparisonContext.GREATER_THAN() != null) {
            return ">";
        }
        if (comparisonContext.GT_EQ() != null) {
            return ">=";
        }
        if (comparisonContext.LESS_THAN() != null) {
            return "<";
        }
        if (comparisonContext.LT_EQ() != null) {
            return "<=";
        }
        if (comparisonContext.IN() != null) {
            if (comparisonContext.NOT() != null) {
                return "not in";
            }
            return "in";
        }
        if (comparisonContext.IS() != null) {
            if (comparisonContext.NOT() != null) {
                return "is not";
            }
            return "is";
        }

        if (comparisonContext.NOT_EQ_1() != null) {
            return "<>";
        }
        if (comparisonContext.NOT_EQ_2() != null) {
            return "!=";
        }
        return null;
    }

    public static Optional<PythonComparison> transformComparisonToPythonComparison(PythonParser.ComparisonContext comparisonContext, PythonParser.RootContext root) {
        if (comparisonContext == null) {
            return Optional.empty();
        }

        if (comparisonContext.expr() != null) {
            return Optional.of(
                new PythonComparison(null, null, null, transformExprToPythonExpression(comparisonContext.expr(), root).orElse(null), comparisonContext, root)
            );
        }
        if (comparisonContext.comparison() != null && comparisonContext.comparison().size() == 2) {
            PythonParser.ComparisonContext leftSide = comparisonContext.comparison(0);
            PythonParser.ComparisonContext rightSide = comparisonContext.comparison(1);


            Optional<PythonComparison> leftComparisonOptional = transformComparisonToPythonComparison(leftSide, root);
            Optional<PythonComparison> rightComparisonOptional = transformComparisonToPythonComparison(rightSide, root);

            return Optional.of(new PythonComparison(
                leftComparisonOptional.orElse(null),
                getComparisonOperatorString(comparisonContext),
                rightComparisonOptional.orElse(null), null,
                comparisonContext, root));
        }


        return Optional.empty();
    }

}
