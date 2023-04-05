package io.codiga.model.ast.common;

import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;


public class FunctionDefinitionParameters extends AstElement {

    public FunctionDefinitionParameter[] values;


    public FunctionDefinitionParameters(List<FunctionDefinitionParameter> argumentsList,
                                        ParserRuleContext ruleContext,
                                        ParserRuleContext root) {
        super(AstElementTypes.FUNCTION_DEFINITION_PARAMETERS, ruleContext, root);
        this.values = new FunctionDefinitionParameter[argumentsList.size()];
        this.values = argumentsList.toArray(this.values);
    }

    public FunctionDefinitionParameters(List<FunctionDefinitionParameter> argumentsList,
                                        ParserContext context) {
        super(AstElementTypes.FUNCTION_DEFINITION_PARAMETERS, context);
        this.values = new FunctionDefinitionParameter[argumentsList.size()];
        this.values = argumentsList.toArray(this.values);
    }
}
