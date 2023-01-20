package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;


public class FunctionDefinition extends AstElement {

    public AstString name;
    public FunctionDefinitionParameters parameters;
    public AstElement content;


    public FunctionDefinition(AstString name,
                              FunctionDefinitionParameters functionDefinitionParameters,
                              AstElement content,
                              ParserRuleContext ruleContext,
                              ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_FUNCTION_DEFINITION, ruleContext, root);
        this.parameters = functionDefinitionParameters;
        this.name = name;
        this.content = null;
    }

    public FunctionDefinition(String alternativeType,
                              AstString name,
                              FunctionDefinitionParameters functionDefinitionParameters,
                              AstElement content,
                              ParserRuleContext ruleContext,
                              ParserRuleContext root) {
        super(alternativeType, ruleContext, root);
        this.parameters = functionDefinitionParameters;
        this.name = name;
        this.content = content;
    }

}
