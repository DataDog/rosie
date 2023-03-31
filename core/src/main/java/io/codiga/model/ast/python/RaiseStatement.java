package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.common.context.ParserContext;

public class RaiseStatement extends AstElement {


    public AstElement exception;
    public AstElement as;


    public RaiseStatement(AstElement exception,
                          AstElement as,
                          ParserContext context) {
        super(AST_ELEMENT_TYPE_RAISE_STATEMENT, context);
        this.exception = exception;
        this.as = as;
    }
}
