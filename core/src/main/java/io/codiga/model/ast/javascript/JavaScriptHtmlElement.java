package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class JavaScriptHtmlElement extends AstElement {

    public AstString tag;
    public JavaScriptHtmlAttribute[] attributes;


    public JavaScriptHtmlElement(AstString tag, List<JavaScriptHtmlAttribute> attributeList, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_HTML_ELEMENT, parserRuleContext, root);
        this.tag = tag;
        this.attributes = attributeList.stream().toArray(JavaScriptHtmlAttribute[]::new);
    }
}
