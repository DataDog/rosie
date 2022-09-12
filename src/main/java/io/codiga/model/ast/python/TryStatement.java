package io.codiga.model.ast.python;

import io.codiga.model.ast.AstElement;
import org.antlr.v4.runtime.ParserRuleContext;

public class TryStatement extends AstElement {

    public ExceptClause exceptClause;
    public FinallyClause finallyClause;

    public TryStatement(ExceptClause exceptClause, FinallyClause finallyClause, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(parserRuleContext, root);
        this.exceptClause = exceptClause;
        this.finallyClause = finallyClause;
    }
}
