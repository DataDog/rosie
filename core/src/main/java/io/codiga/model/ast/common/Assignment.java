package io.codiga.model.ast.common;

import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class Assignment extends AstElement {
    public AstElement left;
    public AstElement right;


    public Assignment(AstElement left, AstElement right, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AstElementTypes.ASSIGNMENT, parserRuleContext, root);
        this.left = left;
        this.right = right;
    }

    public Assignment(AstElement left, AstElement right, ParserContext context) {
        super(AstElementTypes.ASSIGNMENT, context);
        this.left = left;
        this.right = right;
    }
}
