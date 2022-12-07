package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;


public class FunctionCall extends AstElement {

    public AstElement functionName;

    public FunctionCallArguments arguments;


    public FunctionCall(AstElement functionName,
                        FunctionCallArguments arguments,
                        ParserRuleContext parserRuleContext,
                        ParserRuleContext root
    ) {
        super(AST_ELEMENT_TYPE_FUNCTION_CALL, parserRuleContext, root);
        this.arguments = arguments;
        this.functionName = functionName;
    }
}
