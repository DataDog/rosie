package io.codiga.model.ast.common;

import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class Return extends AstElement {
    public AstElement value;


    public Return(AstElement value, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_RETURN, parserRuleContext, root);
        this.value = value;
    }

    public Return(AstElement value, ParserContext context) {
        super(AST_ELEMENT_TYPE_RETURN, context);
        this.value = value;
    }
}
