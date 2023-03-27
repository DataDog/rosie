package io.codiga.model.ast.common;

import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class VariableIndex extends AstElement {
    public AstElement variable;
    public AstElement index;


    public VariableIndex(AstElement variable, AstElement index, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_VARIABLE_INDEX, parserRuleContext, root);
        this.variable = variable;
        this.index = index;
    }

    public VariableIndex(AstElement variable, AstElement index, ParserContext context) {
        super(AST_ELEMENT_TYPE_VARIABLE_INDEX, context);
        this.variable = variable;
        this.index = index;
    }
}
