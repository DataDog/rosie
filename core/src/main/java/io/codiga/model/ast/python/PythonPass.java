package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class PythonPass extends AstElement {


    public PythonPass(ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AstElementTypes.PASS, parserRuleContext, root);
    }

    public PythonPass(ParserContext context) {
        super(AstElementTypes.PASS, context);
    }
}
