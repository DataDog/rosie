package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;

public class SwitchCase extends AstElement {
    public AstElement condition;
    public AstElement content;

    public SwitchCase(AstElement condition, AstElement content, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_SWITCH_CASE, parserRuleContext, root);
        this.condition = condition;
        this.content = content;
    }
}
