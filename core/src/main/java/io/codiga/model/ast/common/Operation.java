package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;

public class Operation extends AstElement {
    public AstElement left;
    public AstElement right;
    public AstString operator;


    public Operation(AstElement left, AstString operator, AstElement right, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_OPERATION, parserRuleContext, root);
        this.left = left;
        this.right = right;
        this.operator = operator;
    }
}
