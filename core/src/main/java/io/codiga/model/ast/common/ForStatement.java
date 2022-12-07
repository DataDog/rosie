package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;


public class ForStatement extends AstElement {

    public AstElement init;
    public AstElement test;
    public AstElement update;

    public ForStatement(AstElement init,
                        AstElement test,
                        AstElement update,
                        ParserRuleContext parserRuleContext,
                        ParserRuleContext root
    ) {
        super(AST_ELEMENT_FOR_STATEMENT, parserRuleContext, root);
        this.init = init;
        this.test = test;
        this.update = update;
    }
}
