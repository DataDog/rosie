package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Sequence extends AstElement {
    public AstElement[] elements;


    public Sequence(List<AstElement> elementsList, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_SEQUENCE, parserRuleContext, root);
        this.elements = new Assignment[elementsList.size()];
        this.elements = elementsList.toArray(elements);
    }
}
