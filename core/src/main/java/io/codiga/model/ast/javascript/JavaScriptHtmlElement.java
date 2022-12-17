package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstElement;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class JavaScriptHtmlElement extends AstElement {

    public AstElement tag;
    public JavaScriptHtmlAttribute[] attributes;


    public JavaScriptHtmlElement(AstElement tag, List<JavaScriptHtmlAttribute> attributeList, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_HTML_ELEMENT, parserRuleContext, root);
        this.tag = tag;
        this.attributes = attributeList.stream().toArray(JavaScriptHtmlAttribute[]::new);
    }
}
