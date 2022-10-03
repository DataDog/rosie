package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;


public class FunctionCall extends AstElement {

    public AstString moduleOrObject;
    public AstString functionName;

    public FunctionCallArguments arguments;


    public FunctionCall(AstString moduleOrObject,
                        AstString functionName,
                        FunctionCallArguments arguments,
                        ParserRuleContext parserRuleContext,
                        ParserRuleContext root
    ) {
        super(AST_ELEMENT_TYPE_FUNCTION_CALL, parserRuleContext, root);
        this.arguments = arguments;
        this.moduleOrObject = moduleOrObject;
        this.functionName = functionName;
    }
}