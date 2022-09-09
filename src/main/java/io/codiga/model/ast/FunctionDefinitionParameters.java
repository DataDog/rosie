package io.codiga.model.ast;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;


public class FunctionDefinitionParameters extends AstElement {

    public FunctionDefinitionParameter[] values;


    public FunctionDefinitionParameters(List<FunctionDefinitionParameter> argumentsList,
                                        ParserRuleContext ruleContext,
                                        ParserRuleContext root) {
        super(ruleContext, root);
        this.values = new FunctionDefinitionParameter[argumentsList.size()];
        this.values = argumentsList.toArray(this.values);
    }
}
