package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.common.Position;
import org.antlr.v4.runtime.ParserRuleContext;

public class JavaScriptHtmlTag extends AstElement {

    public AstString tag;


    public JavaScriptHtmlTag(AstString tag, Position start, Position end, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AstElementTypes.HTML_TAG, start, end, parserRuleContext, root);
        this.tag = tag;
    }

}
