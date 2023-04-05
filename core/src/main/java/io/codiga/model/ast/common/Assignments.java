package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Assignments extends AstElement {
    public Assignment[] values;


    public Assignments(List<Assignment> assignmentList, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AstElementTypes.ASSIGNMENTS, parserRuleContext, root);
        this.values = new Assignment[assignmentList.size()];
        this.values = assignmentList.toArray(values);
    }
}
