package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.python.gen.PythonParser;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class PythonComparison extends AstElement {
    public AstElement leftSide;
    public String operator;
    public AstElement rightSide;
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

    public PythonComparison(AstElement leftSide,
                            String operator,
                            AstElement rightSide,
                            PythonExpression expression,
                            ParserContext parserContext) {
        super(AST_ELEMENT_TYPE_COMPARISON, parserContext);
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.operator = operator;
        this.expression = expression;
    }
}
