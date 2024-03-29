package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import org.antlr.v4.runtime.ParserRuleContext;

public class PythonExpression extends AstElement {
    public static final String PYTHON_EXPRESSION_TYPE_ATOM = "atom";
    public PythonString atom;
    public String expressionType;

    public PythonExpression(PythonString atom,
                            String expressionType,
                            ParserRuleContext parserRuleContext,
                            ParserRuleContext root) {
        super(AstElementTypes.EXPRESSION, parserRuleContext, root);
        this.expressionType = expressionType;
        this.atom = atom;
    }


}
