package io.codiga.model.ast;

import org.antlr.v4.runtime.ParserRuleContext;


public class FunctionDefinitionParameter extends AstElement {

    public AstString name;
    public AstString type;
    public AstString defaultValue;


    public FunctionDefinitionParameter(AstString name,
                                       AstString type,
                                       AstString defaultValue,
                                       ParserRuleContext ruleContext,
                                       ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_FUNCTION_DEFINITION_PARAMETER, ruleContext, root);
        this.name = name;
        this.defaultValue = defaultValue;
        this.type = type;
    }
}
