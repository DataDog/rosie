package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstElement;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class JavaScriptObject extends AstElement {

    public JavaScriptObjectElement[] elements;

    public JavaScriptObject(List<? extends JavaScriptObjectElement> listElements,
                            ParserRuleContext ruleContext,
                            ParserRuleContext root) {

        super(AST_ELEMENT_TYPE_OBJECT, ruleContext, root);
        this.elements = new JavaScriptObjectElement[listElements.size()];
        this.elements = listElements.toArray(elements);
    }


}
