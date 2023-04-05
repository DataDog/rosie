package io.codiga.model.ast.common;

import io.codiga.parser.common.context.ParserContext;
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
        super(AstElementTypes.FUNCTION_DEFINITION_PARAMETER, ruleContext, root);
        this.name = name;
        this.defaultValue = defaultValue;
        this.type = type;
    }

    public FunctionDefinitionParameter(AstString name,
                                       AstElement type,
                                       AstString defaultValue,
                                       ParserContext context) {
        super(AstElementTypes.FUNCTION_DEFINITION_PARAMETER, context);
        this.name = name;
        this.defaultValue = defaultValue;
        this.type = type;
    }
}
