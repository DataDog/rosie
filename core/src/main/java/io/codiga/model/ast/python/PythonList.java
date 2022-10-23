package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class PythonList extends AstElement {

    public AstElement[] elements;

    public PythonList(List<? extends AstElement> listElements,
                      ParserRuleContext ruleContext,
                      ParserRuleContext root) {

        super(AST_ELEMENT_TYPE_LIST, ruleContext, root);
        this.elements = new AstElement[listElements.size()];
        this.elements = listElements.toArray(elements);
    }


}