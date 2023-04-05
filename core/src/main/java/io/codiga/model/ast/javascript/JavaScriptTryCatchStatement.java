package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.model.ast.common.Block;
import org.antlr.v4.runtime.ParserRuleContext;

public class JavaScriptTryCatchStatement extends AstElement {

    public Block tryBlock;
    public Block finallyBlock;
    public JavaScriptCatchStatement catchBlock;


    public JavaScriptTryCatchStatement(Block tryBLock, JavaScriptCatchStatement catchStatement, Block finallyBlock, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AstElementTypes.TRY_STATEMENT, parserRuleContext, root);
        this.tryBlock = tryBLock;
        this.finallyBlock = finallyBlock;
        this.catchBlock = catchStatement;
    }
}
