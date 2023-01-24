package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;

public class Continue extends AstElement {


    public Continue(ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_CONTINUE, parserRuleContext, root);
    }
}
