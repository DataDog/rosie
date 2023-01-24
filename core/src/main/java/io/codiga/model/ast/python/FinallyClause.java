package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import org.antlr.v4.runtime.ParserRuleContext;

public class FinallyClause extends AstElement {

    public AstElement content;

    public FinallyClause(AstElement content, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_FUNCTION_FINALLY_CLAUSE, parserRuleContext, root);
        this.content = content;
    }

}
