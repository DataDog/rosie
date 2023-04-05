package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class FromElement extends AstElement {
    public AstString name;
    public AstString as;

    public FromElement(AstString name, AstString as, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AstElementTypes.FROM_ELEMENT, parserRuleContext, root);
        this.name = name;
        this.as = as;
    }

    public FromElement(AstString name, AstString as, ParserContext parserContext) {
        super(AstElementTypes.FROM_ELEMENT, parserContext);
        this.name = name;
        this.as = as;
    }
}

