package io.codiga.model.ast.typescript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TypeScriptInterfaceIndexSignature extends AstElement {
    private static final Logger logger = LoggerFactory.getLogger(TypeScriptInterfaceIndexSignature.class);
    public AstElement keyName;
    public AstElement keyType;
    public AstElement type;

    public TypeScriptInterfaceIndexSignature(
        AstElement keyName,
        AstElement keyType,
        AstElement type,
        ParserRuleContext parserRuleContext,
        ParserRuleContext root
    ) {
        super(AstElementTypes.INTERFACE_INDEX_SIGNATURE, parserRuleContext, root);
        this.keyName = keyName;
        this.keyType = keyType;
        this.type = type;
    }

}
