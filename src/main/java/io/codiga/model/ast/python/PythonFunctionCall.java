package io.codiga.model.ast.python;

import io.codiga.model.ast.FunctionCall;
import io.codiga.model.ast.FunctionCallArgument;
import io.codiga.model.common.Position;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;


public class PythonFunctionCall extends FunctionCall {

    public PythonFunctionCall(String moduleOrObject,
                              String functionName,
                              List<FunctionCallArgument> argumentsList,
                              Position start,
                              Position end,
                              ParserRuleContext parserRuleContext) {
        super(moduleOrObject, functionName, argumentsList, start, end, parserRuleContext);
    }
}
