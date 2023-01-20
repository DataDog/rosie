package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import org.antlr.v4.runtime.ParserRuleContext;

public class PythonPass extends AstElement {


    public PythonPass(ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_PASS, parserRuleContext, root);
    }
}
