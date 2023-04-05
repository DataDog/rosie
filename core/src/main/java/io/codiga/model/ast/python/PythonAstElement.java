package io.codiga.model.ast.python;


import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import org.antlr.v4.runtime.ParserRuleContext;

public class PythonAstElement extends AstElement {


    public PythonAstElement(AstElementTypes astType,
                            ParserRuleContext parserRuleContext,
                            ParserRuleContext root) {
        super(astType, parserRuleContext, root);
    }


}
