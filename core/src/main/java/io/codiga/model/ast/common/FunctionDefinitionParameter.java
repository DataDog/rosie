package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;


public class FunctionDefinitionParameter extends AstElement {

    public AstString name;
    public AstElement type;
    public AstString defaultValue;


    public FunctionDefinitionParameter(AstString name,
                                       AstElement type,
                                       AstString defaultValue,
                                       ParserRuleContext ruleContext,
                                       ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_FUNCTION_DEFINITION_PARAMETER, ruleContext, root);
        this.name = name;
        this.defaultValue = defaultValue;
        this.type = type;
    }
}
