package io.codiga.model.ast.python;

import io.codiga.model.ast.AstElement;
import io.codiga.model.ast.AstString;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class ExceptClause extends AstElement {
    public AstString[] exceptions;
    public AstString as;

    public ExceptClause(List<AstString> exceptions, AstString as, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(parserRuleContext, root);
        this.exceptions = exceptions.stream().toArray(AstString[]::new);
        this.as = as;
    }
}
