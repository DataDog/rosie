package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import org.antlr.v4.runtime.ParserRuleContext;

public class Assignment extends AstElement {
    public AstElement left;
    public AstElement right;


    public Assignment(AstElement left, AstElement right, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_ASSIGNMENT, parserRuleContext, root);
        this.left = left;
        this.right = right;
    }
}
