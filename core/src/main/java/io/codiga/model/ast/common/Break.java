package io.codiga.model.ast.common;

import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class Break extends AstElement {


    public Break(ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_BREAK, parserRuleContext, root);
    }

    public Break(ParserContext context) {
        super(AST_ELEMENT_TYPE_BREAK, context);
    }
}
