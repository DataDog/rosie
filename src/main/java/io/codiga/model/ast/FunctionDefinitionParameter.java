package io.codiga.model.ast;

import io.codiga.model.common.Position;
import org.antlr.v4.runtime.ParserRuleContext;


public class FunctionDefinitionParameter extends AstElement {

    public AstString name;
    public AstString type;
    public AstString defaultValue;


    public FunctionDefinitionParameter(AstString name,
                                       AstString type,
                                       AstString defaultValue,
                                       Position start,
                                       Position end,
                                       ParserRuleContext ruleContext,
                                       ParserRuleContext root) {
        super(start, end, ruleContext, root);
        this.name = name;
        this.defaultValue = defaultValue;
        this.type = type;
    }
}
