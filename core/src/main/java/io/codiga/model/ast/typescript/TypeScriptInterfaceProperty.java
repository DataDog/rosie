package io.codiga.model.ast.typescript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TypeScriptInterfaceProperty extends AstElement {
    private static final Logger logger = LoggerFactory.getLogger(TypeScriptInterfaceProperty.class);
    public AstElement name;
    public AstElement value;
    public boolean readOnly;

    public TypeScriptInterfaceProperty(
        boolean readOnly,
        AstElement name,
        AstElement value,
        ParserRuleContext parserRuleContext,
        ParserRuleContext root
    ) {
        super(AstElementTypes.INTERFACE_PROPERTY, parserRuleContext, root);
        this.name = name;
        this.value = value;
        this.readOnly = readOnly;
    }

}
