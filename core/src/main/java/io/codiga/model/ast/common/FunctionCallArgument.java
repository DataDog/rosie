package io.codiga.model.ast.common;


import org.antlr.v4.runtime.ParserRuleContext;

public class FunctionCallArgument extends AstElement {
    public AstString name;
    public AstElement value;

    public FunctionCallArgument(AstString name, AstElement value, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_ARGUMENT, parserRuleContext, root);
        this.name = name;
        this.value = value;
    }
}