package io.codiga.model.ast.common;


import org.antlr.v4.runtime.ParserRuleContext;

public class FunctionCallArgument extends AstElement {
    public AstString name;
    public AstString value;

    public FunctionCallArgument(AstString n, AstString v, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_ARGUMENT, parserRuleContext, root);
        this.name = n;
        this.value = v;
    }
}