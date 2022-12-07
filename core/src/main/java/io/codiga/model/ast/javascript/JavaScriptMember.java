package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import org.antlr.v4.runtime.ParserRuleContext;

public class JavaScriptMember extends AstElement {

    public AstElement parent;
    public AstString name;

    public JavaScriptMember(AstString name, AstElement parent, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_MEMBER, parserRuleContext, root);
        this.name = name;
        this.parent = parent;
    }
}
