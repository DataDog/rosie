package io.codiga.model.ast.python;

import io.codiga.model.ast.AstElement;
import org.antlr.v4.runtime.ParserRuleContext;

public class TryStatement extends AstElement {

    public ExceptClause exceptClause;
    public FinallyClause finallyClause;

    public TryStatement(ExceptClause exceptClause, FinallyClause finallyClause, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_TRY_STATEMENT, parserRuleContext, root);
        this.exceptClause = exceptClause;
        this.finallyClause = finallyClause;
    }
}
