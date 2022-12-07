package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Block extends AstElement {
    public AstElement[] statements;


    public Block(List<AstElement> statementsList, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_CONTAINER, parserRuleContext, root);
        this.statements = new Assignment[statementsList.size()];
        this.statements = statementsList.toArray(statements);
    }
}
