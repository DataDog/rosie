package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class AstArray extends AstElement {

    public AstElement[] elements;

    public AstArray(List<? extends AstElement> listElements,
                    ParserRuleContext ruleContext,
                    ParserRuleContext root) {

        super(AST_ELEMENT_TYPE_ARRAY, ruleContext, root);
        this.elements = new AstElement[listElements.size()];
        this.elements = listElements.toArray(elements);
    }


}
