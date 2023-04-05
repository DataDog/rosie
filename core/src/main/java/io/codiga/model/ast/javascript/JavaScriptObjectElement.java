package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import org.antlr.v4.runtime.ParserRuleContext;

public class JavaScriptObjectElement extends AstElement {

    public AstElement name;
    public AstElement value;

    public JavaScriptObjectElement(AstElement name,
                                   AstElement value,
                                   ParserRuleContext ruleContext,
                                   ParserRuleContext root) {

        super(AstElementTypes.OBJECT_ELEMENT, ruleContext, root);
        this.name = name;
        this.value = value;
    }


}
