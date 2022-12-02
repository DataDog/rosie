package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionCall;
import io.codiga.model.ast.common.FunctionCallArguments;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaScriptFunctionCall extends FunctionCall {
    private static final Logger logger = LoggerFactory.getLogger(JavaScriptFunctionCall.class);

    public JavaScriptFunctionCall(
        AstString functionName,
        FunctionCallArguments arguments,
        ParserRuleContext parserRuleContext,
        ParserRuleContext root) {
        super(functionName, arguments, parserRuleContext, root);
    }

}
