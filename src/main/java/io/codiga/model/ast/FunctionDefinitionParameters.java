package io.codiga.model.ast;

import io.codiga.model.common.Position;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;


public class FunctionDefinitionParameters extends AstElement {

    public FunctionDefinitionParameter[] values;


    public FunctionDefinitionParameters(List<FunctionDefinitionParameter> argumentsList,
                                        Position start,
                                        Position end,
                                        ParserRuleContext ruleContext,
                                        ParserRuleContext root) {
        super(start, end, ruleContext, root);
        this.values = new FunctionDefinitionParameter[argumentsList.size()];
        this.values = argumentsList.toArray(this.values);
    }
}
