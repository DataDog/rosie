package io.codiga.model.ast.typescript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TypeScriptInterface extends AstElement {
    private static final Logger logger = LoggerFactory.getLogger(TypeScriptInterface.class);
    public AstElement name;
    public AstElement[] members;

    public TypeScriptInterface(
        AstElement name,
        List<AstElement> membersList,
        ParserRuleContext parserRuleContext,
        ParserRuleContext root
    ) {
        super(AstElementTypes.INTERFACE, parserRuleContext, root);
        this.name = name;
        this.members = new AstElement[membersList.size()];
        this.members = membersList.toArray(members);
    }

}
