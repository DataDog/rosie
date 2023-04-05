package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class JavaScriptObject extends AstElement {

    public JavaScriptObjectElement[] elements;

    public JavaScriptObject(List<? extends JavaScriptObjectElement> listElements,
                            ParserRuleContext ruleContext,
                            ParserRuleContext root) {

        super(AstElementTypes.OBJECT, ruleContext, root);
        this.elements = new JavaScriptObjectElement[listElements.size()];
        this.elements = listElements.toArray(elements);
    }


}
