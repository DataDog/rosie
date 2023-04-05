package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.*;
import org.antlr.v4.runtime.ParserRuleContext;


public class JavaScriptFunctionExpression extends FunctionDefinition {

    public boolean isAsync;

    public JavaScriptFunctionExpression(boolean isAsync,
                                        AstString name,
                                        FunctionDefinitionParameters functionDefinitionParameters,
                                        AstElement content,
                                        ParserRuleContext ruleContext,
                                        ParserRuleContext root) {
        super(AstElementTypes.FUNCTION_EXPRESSION, name, functionDefinitionParameters, content, ruleContext, root);
        this.isAsync = isAsync;
    }
}
