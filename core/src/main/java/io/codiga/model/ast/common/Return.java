package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;

public class Return extends AstElement {
    public AstElement value;


    public Return(AstElement value, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_RETURN, parserRuleContext, root);
        this.value = value;
    }
}
