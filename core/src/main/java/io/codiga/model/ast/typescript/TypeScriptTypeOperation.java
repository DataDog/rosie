package io.codiga.model.ast.typescript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.model.ast.common.AstString;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TypeScriptTypeOperation extends AstElement {
    private static final Logger logger = LoggerFactory.getLogger(TypeScriptTypeOperation.class);
    public AstElement left;
    public AstElement right;
    public AstString operand;


    public TypeScriptTypeOperation(
        AstElement left,
        AstString operand,
        AstElement right,
        ParserRuleContext parserRuleContext,
        ParserRuleContext root
    ) {
        super(AstElementTypes.TYPE_OPERATION, parserRuleContext, root);
        this.left = left;
        this.right = right;
        this.operand = operand;
    }

}
