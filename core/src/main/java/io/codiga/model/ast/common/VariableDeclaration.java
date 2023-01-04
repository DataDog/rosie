package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;

public class VariableDeclaration extends AstElement {
    public AstString modifier;
    public AstElement name;
    public AstElement type;
    public AstElement value;


    public VariableDeclaration(AstString modifier, AstElement name, AstElement type, AstElement value,
                               ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_VARIABLE_DECLARATION, parserRuleContext, root);
        this.modifier = modifier;
        this.name = name;
        this.type = type;
        this.value = value;
    }
}
