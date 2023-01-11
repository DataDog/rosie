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
    public AstElement[] content;
    public JavaScriptHtmlElement parentHtmlElement;
    public JavaScriptHtmlTag openingTag;
    public JavaScriptHtmlTag closingTag;

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
    public JavaScriptHtmlElement(AstElement tag,
                                 JavaScriptHtmlTag openingTag,
                                 JavaScriptHtmlTag closingTag,
                                 List<JavaScriptHtmlAttribute> attributeList,
                                 List<AstElement> contentList,
                                 ParserRuleContext parserRuleContext,
                                 ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_HTML_ELEMENT, parserRuleContext, root);
        this.tag = tag;
        this.attributes = attributeList.stream().toArray(JavaScriptHtmlAttribute[]::new);
        this.content = contentList.stream().toArray(AstElement[]::new);
        this.htmlChildren = new AstElement[0];
        this.openingTag = openingTag;
        this.closingTag = closingTag;
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

    public void setParentHtmlElement(JavaScriptHtmlElement parent) {
        this.parentHtmlElement = parent;
    }
}
