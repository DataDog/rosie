package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class TryStatement extends AstElement {

    public ExceptClause[] exceptClauses;
    public FinallyClause finallyClause;

    public TryStatement(AstElement content,
                        List<ExceptClause> exceptClauses,
                        FinallyClause finallyClause,
                        ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_TRY_STATEMENT, parserRuleContext, root);
        this.finallyClause = finallyClause;
        this.exceptClauses = exceptClauses.stream().toArray(ExceptClause[]::new);

    }
}
