package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.model.ast.common.AstString;
import org.antlr.v4.runtime.ParserRuleContext;

public class JavaScriptHtmlData extends AstElement {

    public AstString value;


    public JavaScriptHtmlData(AstString value, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AstElementTypes.HTML_DATA, parserRuleContext, root);
        this.value = value;
    }
}
