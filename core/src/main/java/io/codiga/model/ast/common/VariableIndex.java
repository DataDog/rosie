package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;

public class VariableIndex extends AstElement {
    public AstElement variable;
    public AstElement index;


    public VariableIndex(AstElement variable, AstElement index, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_VARIABLE_INDEX, parserRuleContext, root);
        this.variable = variable;
        this.index = index;
    }
}
