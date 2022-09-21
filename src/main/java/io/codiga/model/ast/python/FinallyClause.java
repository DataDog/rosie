package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import org.antlr.v4.runtime.ParserRuleContext;

public class FinallyClause extends AstElement {
    public AstString[] exceptions;
    public AstString as;

    public FinallyClause(ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_FUNCTION_FINALLY_CLAUSE, parserRuleContext, root);
    }

}
