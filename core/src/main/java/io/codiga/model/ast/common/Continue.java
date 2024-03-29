package io.codiga.model.ast.common;

import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class Continue extends AstElement {


    public Continue(ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AstElementTypes.CONTINUE, parserRuleContext, root);
    }

    public Continue(ParserContext parserContext) {
        super(AstElementTypes.CONTINUE, parserContext);
    }
}
