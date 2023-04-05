package io.codiga.model.ast.common;

import io.codiga.parser.common.context.ParserContext;

/**
 * Generic class to represent a while condition in any language.
 */
public class WhileStatement extends AstElement {

    public AstElement condition;
    public AstElement statements;

    public WhileStatement(AstElement condition,
                          AstElement statements,
                          ParserContext parserContext
    ) {
        super(AstElementTypes.WHILE_STATEMENT, parserContext);
        this.condition = condition;
        this.statements = statements;
    }
}
