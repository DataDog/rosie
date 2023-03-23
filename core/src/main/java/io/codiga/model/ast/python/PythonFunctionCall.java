package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionCall;
import io.codiga.model.ast.common.FunctionCallArguments;
import io.codiga.model.common.Position;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PythonFunctionCall extends FunctionCall {

    private static final Logger logger = LoggerFactory.getLogger(PythonFunctionCall.class);

    public AstString moduleOrObject;

    public PythonFunctionCall(AstString moduleOrObject,
                              AstString functionName,
                              FunctionCallArguments arguments,
                              Position start,
                              Position end,
                              ParserRuleContext parserRuleContext,
                              ParserRuleContext root) {
        super(functionName, arguments, parserRuleContext, root);
        this.moduleOrObject = moduleOrObject;

    }

    public PythonFunctionCall(AstString moduleOrObject,
                              AstString functionName,
                              FunctionCallArguments arguments,
                              ParserContext parserContext) {
        super(functionName, arguments, parserContext);
        this.moduleOrObject = moduleOrObject;

    }

}
