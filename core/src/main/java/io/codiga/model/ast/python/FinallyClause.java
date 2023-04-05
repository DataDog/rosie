package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class FinallyClause extends AstElement {

    public AstElement content;

    public FinallyClause(AstElement content, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AstElementTypes.FUNCTION_FINALLY_CLAUSE, parserRuleContext, root);
        this.content = content;
    }

    public FinallyClause(AstElement content, ParserContext context) {
        super(AstElementTypes.FUNCTION_FINALLY_CLAUSE, context);
        this.content = content;
    }
}
