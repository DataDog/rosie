package io.codiga.model.ast;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;


public class FunctionCallArguments extends AstElement {


    public FunctionCallArgument[] values;


    public FunctionCallArguments(List<FunctionCallArgument> argumentsList,
                                 ParserRuleContext parserRuleContext,
                                 ParserRuleContext root
    ) {
        super(parserRuleContext, root);
        this.values = new FunctionCallArgument[argumentsList.size()];
        this.values = argumentsList.toArray(values);

    }
}
