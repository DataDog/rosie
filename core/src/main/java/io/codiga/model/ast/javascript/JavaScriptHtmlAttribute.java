package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import org.antlr.v4.runtime.ParserRuleContext;

public class JavaScriptHtmlAttribute extends AstElement {

    public AstString name;
    public AstElement value;


    public JavaScriptHtmlAttribute(AstString name, AstElement value, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_HTML_ATTRIBUTE, parserRuleContext, root);
        this.name = name;
        this.value = value;
    }
}
