package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class TryStatement extends AstElement {

    public ExceptClause[] exceptClauses;
    public FinallyClause finallyClause;
    public AstElement content;

    public TryStatement(AstElement content,
                        List<ExceptClause> exceptClauses,
                        FinallyClause finallyClause,
                        ParserRuleContext parserRuleContext,
                        ParserRuleContext root) {
        super(AstElementTypes.TRY_STATEMENT, parserRuleContext, root);
        this.content = content;
        this.finallyClause = finallyClause;
        this.exceptClauses = exceptClauses.stream().toArray(ExceptClause[]::new);
    }


    public TryStatement(AstElement content,
                        List<ExceptClause> exceptClauses,
                        FinallyClause finallyClause,
                        ParserContext context) {
        super(AstElementTypes.TRY_STATEMENT, context);
        this.content = content;
        this.finallyClause = finallyClause;
        this.exceptClauses = exceptClauses.stream().toArray(ExceptClause[]::new);
    }
}
