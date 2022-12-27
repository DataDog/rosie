package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstElement;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class JavaScriptHtmlElement extends AstElement {

    private final List<AstElement> childrenList = new ArrayList<>();
    public AstElement tag;
    public JavaScriptHtmlAttribute[] attributes;
    public AstElement[] htmlChildren;


    /**
     * Represents an HTML element.
     * This captures something like
     * <tag attribute=value />
     * or
     * <tag attribute=value>....</tag>
     *
     * @param tag
     * @param attributeList
     * @param parserRuleContext
     * @param root
     */
    public JavaScriptHtmlElement(AstElement tag, List<JavaScriptHtmlAttribute> attributeList, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_HTML_ELEMENT, parserRuleContext, root);
        this.tag = tag;
        this.attributes = attributeList.stream().toArray(JavaScriptHtmlAttribute[]::new);
        this.htmlChildren = new AstElement[0];
    }

    /**
     * Update the children from the list into an array.
     * This is used to avoid rebuilding the array constantly when adding a child
     */
    public void updateChildren() {
        this.htmlChildren = childrenList.stream().toArray(AstElement[]::new);
    }

    public void addChild(AstElement element) {
        this.childrenList.add(element);
    }
}
