package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstString;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.ParserRuleContext;

public class PythonString extends AstString {

    public PythonString(String value,
                        ParserRuleContext ruleContext,
                        ParserRuleContext root) {

        super(value, ruleContext, root);
    }

    private static String getStringFromDottedName(PythonParser.Dotted_nameContext context) {
        if (context.DOT() != null) {
            return String.format("%s.%s", getStringFromDottedName(context.dotted_name()), context.name().getText());
        }
        return context.name().getText();
    }

    public static PythonString fromDottedName(PythonParser.Dotted_nameContext nameContext, ParserRuleContext root) {
        return new PythonString(getStringFromDottedName(nameContext), nameContext, root);
    }
}
