package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class FromStatement extends AstElement {
    public AstString pkg;
    public FromElement[] elements;


    public FromStatement(AstString pkg, List<FromElement> fromElementList, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AstElementTypes.FROM_STATEMENT, parserRuleContext, root);
        this.pkg = pkg;
        this.elements = new FromElement[fromElementList.size()];
        this.elements = fromElementList.toArray(elements);
    }

    public FromStatement(AstString pkg, List<FromElement> fromElementList, ParserContext parserContext) {
        super(AstElementTypes.FROM_STATEMENT, parserContext);
        this.pkg = pkg;
        this.elements = new FromElement[fromElementList.size()];
        this.elements = fromElementList.toArray(elements);
    }
}
