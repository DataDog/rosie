package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.antlr.v4.runtime.ParserRuleContext;

public class PythonComparison extends AstElement {
    public PythonComparison leftSide;
    public String operator;
    public PythonComparison rightSide;
    public PythonExpression expression;

    public PythonComparison(PythonComparison leftSide,
                            String operator,
                            PythonComparison rightSide,
                            PythonExpression expression,
                            PythonParser.ComparisonContext parserRuleContext,
                            ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_COMPARISON, parserRuleContext, root);
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.operator = operator;
        this.expression = expression;
    }
}
