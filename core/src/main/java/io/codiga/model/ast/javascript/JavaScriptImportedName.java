package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import org.antlr.v4.runtime.ParserRuleContext;

public class JavaScriptImportedName extends AstElement {

    public AstString name;
    public AstString as;

    public JavaScriptImportedName(AstString name,
                                  AstString as,
                                  ParserRuleContext ruleContext,
                                  ParserRuleContext root) {

        super(AST_ELEMENT_TYPE_IMPORTED_NAME, ruleContext, root);
        this.name = name;
        this.as = as;
    }
}
