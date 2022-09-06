package io.codiga.analyzer.ast.languages.python;

import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.tree.ParseTree;

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

}
