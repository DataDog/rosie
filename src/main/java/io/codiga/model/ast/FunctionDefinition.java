package io.codiga.model.ast;

import io.codiga.model.common.Position;
import org.antlr.v4.runtime.ParserRuleContext;


public class FunctionDefinition extends AstElement {

    public AstString name;
    public AstString returnType;
    public FunctionDefinitionParameters parameters;


    public FunctionDefinition(AstString name,
                              FunctionDefinitionParameters functionDefinitionParameters,
                              AstString returnType,
                              Position start,
                              Position end,
                              ParserRuleContext ruleContext,
                              ParserRuleContext root) {
        super(start, end, ruleContext, root);
        this.parameters = functionDefinitionParameters;
        this.name = name;
        this.returnType = returnType;
    }
}
