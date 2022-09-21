package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstString;
import org.antlr.v4.runtime.ParserRuleContext;

public class PythonString extends AstString {

    public PythonString(String value,
                        ParserRuleContext ruleContext,
                        ParserRuleContext root) {

        super(value, ruleContext, root);
    }
}
