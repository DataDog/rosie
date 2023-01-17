package io.codiga.model.ast.common;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Switch extends AstElement {
    public AstElement expression;
    public SwitchCase defaultCase;
    public SwitchCase[] cases;


    public Switch(AstElement expression, List<SwitchCase> switchCaseList, SwitchCase defaultCase, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_SWITCH, parserRuleContext, root);
        this.expression = expression;
        this.defaultCase = defaultCase;
        this.cases = new SwitchCase[switchCaseList.size()];
        this.cases = switchCaseList.toArray(cases);
    }
}
