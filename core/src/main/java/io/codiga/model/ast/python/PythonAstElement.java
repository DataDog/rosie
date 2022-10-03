package io.codiga.model.ast.python;


import io.codiga.model.ast.common.AstElement;
import org.antlr.v4.runtime.ParserRuleContext;

public class PythonAstElement extends AstElement {


    public PythonAstElement(String astType,
                            ParserRuleContext parserRuleContext,
                            ParserRuleContext root) {
        super(astType, parserRuleContext, root);
    }


}
