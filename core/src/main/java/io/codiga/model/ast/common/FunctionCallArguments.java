package io.codiga.model.ast.common;

import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;


public class FunctionCallArguments extends AstElement {


    public FunctionCallArgument[] values;


    public FunctionCallArguments(List<FunctionCallArgument> argumentsList,
                                 ParserRuleContext parserRuleContext,
                                 ParserRuleContext root
    ) {
        super(AstElementTypes.ARGUMENTS, parserRuleContext, root);

        this.values = new FunctionCallArgument[argumentsList.size()];
        this.values = argumentsList.toArray(values);

    }

    public FunctionCallArguments(List<FunctionCallArgument> argumentsList,
                                 ParserContext context
    ) {
        super(AstElementTypes.ARGUMENTS, context);

        this.values = new FunctionCallArgument[argumentsList.size()];
        this.values = argumentsList.toArray(values);

    }
}
