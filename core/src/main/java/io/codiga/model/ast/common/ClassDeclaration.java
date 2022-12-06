package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;


public class ClassDeclaration extends AstElement {
    public AstString name;


    public ClassDeclaration(AstString name,
                            ParserRuleContext ruleContext,
                            ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_CLASS_DEFINITION, ruleContext, root);
        this.name = name;

    }
}
