package io.codiga.model.ast.common;

import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;


public class FunctionCall extends AstElement {

    public AstElement functionName;

    public FunctionCallArguments arguments;


    public FunctionCall(AstElement functionName,
                        FunctionCallArguments arguments,
                        ParserRuleContext parserRuleContext,
                        ParserRuleContext root
    ) {
        super(AstElementTypes.FUNCTION_CALL, parserRuleContext, root);
        this.arguments = arguments;
        this.functionName = functionName;
    }

    public FunctionCall(AstElement functionName,
                        FunctionCallArguments arguments,
                        ParserContext context) {
        super(AstElementTypes.FUNCTION_CALL, context);
        this.arguments = arguments;
        this.functionName = functionName;
    }

}
