package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class JavaScriptCatchStatement extends AstElement {

    public AstElement[] statements;
    public AstElement exception;

    public JavaScriptCatchStatement(List<AstElement> statementsList, AstElement exceptionName, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AstElementTypes.CATCH_STATEMENT, parserRuleContext, root);
        this.exception = exceptionName;
        this.statements = statementsList.stream().toArray(AstElement[]::new);

    }
}
