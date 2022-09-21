package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;


public class FunctionDefinition extends AstElement {

    public AstString name;
    public AstString returnType;
    public FunctionDefinitionParameters parameters;

    public boolean isAsync;


    public FunctionDefinition(boolean isAsync,
                              AstString name,
                              FunctionDefinitionParameters functionDefinitionParameters,
                              AstString returnType,
                              ParserRuleContext ruleContext,
                              ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_FUNCTION_DEFINITION, ruleContext, root);
        this.isAsync = isAsync;
        this.parameters = functionDefinitionParameters;
        this.name = name;
        this.returnType = returnType;
    }
}
