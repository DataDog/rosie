package io.codiga.model.ast.python;

import io.codiga.model.ast.AstElement;
import io.codiga.model.ast.AstString;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class FromStatement extends AstElement {
    public AstString pkg;
    public FromElement[] elements;


    public FromStatement(AstString pkg, List<FromElement> fromElementList, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_FROM_STATEMENT, parserRuleContext, root);
        this.pkg = pkg;
        this.elements = new FromElement[fromElementList.size()];
        this.elements = fromElementList.toArray(elements);
    }
}
