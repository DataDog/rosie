package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;


public class ClassDeclarationOneParent extends ClassDeclaration {
    public AstElement parentClass;


    public ClassDeclarationOneParent(AstString name,
                                     AstElement parentClass,
                                     ParserRuleContext ruleContext,
                                     ParserRuleContext root) {
        super(name, ruleContext, root);
        this.parentClass = parentClass;

    }
}
