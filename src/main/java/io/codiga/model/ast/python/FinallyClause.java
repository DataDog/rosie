package io.codiga.model.ast.python;

import io.codiga.model.ast.AstElement;
import io.codiga.model.ast.AstString;
import org.antlr.v4.runtime.ParserRuleContext;

public class FinallyClause extends AstElement {
    public AstString[] exceptions;
    public AstString as;

    public FinallyClause(ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(parserRuleContext, root);
    }

}
