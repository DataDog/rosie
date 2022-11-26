package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionCall;
import io.codiga.model.ast.common.FunctionCallArguments;
import io.codiga.model.common.Position;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PythonFunctionCall extends FunctionCall {

    private static final Logger logger = LoggerFactory.getLogger(PythonFunctionCall.class);

    public PythonFunctionCall(AstString moduleOrObject,
                              AstString functionName,
                              FunctionCallArguments arguments,
                              Position start,
                              Position end,
                              ParserRuleContext parserRuleContext,
                              ParserRuleContext root) {
        super(moduleOrObject, functionName, arguments, parserRuleContext, root);
    }

}
