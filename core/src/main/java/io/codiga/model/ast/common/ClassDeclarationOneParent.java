package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;


public class ClassDeclarationOneParent extends ClassDeclaration {
    public AstString parentClass;


    public ClassDeclarationOneParent(AstString name,
                                     AstString parentClass,
                                     ParserRuleContext ruleContext,
                                     ParserRuleContext root) {
        super(name, ruleContext, root);
        this.parentClass = parentClass;

    }
}
