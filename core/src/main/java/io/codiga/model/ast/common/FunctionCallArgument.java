package io.codiga.model.ast.common;


import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class FunctionCallArgument extends AstElement {
    public AstString name;
    public AstElement value;

    public FunctionCallArgument(AstString name, AstElement value, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_ARGUMENT, parserRuleContext, root);
        this.name = name;
        this.value = value;
    }

    public FunctionCallArgument(AstString name, AstElement value, ParserContext parserContext) {
        super(AST_ELEMENT_TYPE_ARGUMENT, parserContext);
        this.name = name;
        this.value = value;
    }
}