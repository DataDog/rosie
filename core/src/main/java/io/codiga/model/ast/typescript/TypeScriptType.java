package io.codiga.model.ast.typescript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TypeScriptType extends AstElement {
    private static final Logger logger = LoggerFactory.getLogger(TypeScriptType.class);
    public AstElement name;


    public TypeScriptType(
        AstElement name,
        ParserRuleContext parserRuleContext,
        ParserRuleContext root
    ) {
        super(AstElementTypes.TYPE, parserRuleContext, root);
        this.name = name;
    }

}
