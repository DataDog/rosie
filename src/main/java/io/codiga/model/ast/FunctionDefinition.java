package io.codiga.model.ast;

import org.antlr.v4.runtime.ParserRuleContext;


public class FunctionDefinition extends AstElement {

    public AstString name;
    public AstString returnType;
    public FunctionDefinitionParameters parameters;


    public FunctionDefinition(AstString name,
                              FunctionDefinitionParameters functionDefinitionParameters,
                              AstString returnType,
                              ParserRuleContext ruleContext,
                              ParserRuleContext root) {
        super(ruleContext, root);
        this.parameters = functionDefinitionParameters;
        this.name = name;
        this.returnType = returnType;
    }
}
