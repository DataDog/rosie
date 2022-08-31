package io.codiga.ast.python;

import io.codiga.model.ast.FunctionCall;
import io.codiga.model.ast.FunctionCallArgument;
import io.codiga.model.common.Position;
import io.codiga.parser.python.gen.PythonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.ast.AstUtils.isFunctionCall;

public class ExprToFunctionCall {


    public static Optional<FunctionCall> transformExprToFunctionCall(PythonParser.ExprContext ctx) {
        String objectOrModule = null;
        String functionName = null;
        List<FunctionCallArgument> functionArguments = new ArrayList<>();

        if (!isFunctionCall(ctx)) {
            return Optional.empty();
        }

        PythonParser.AtomContext atom = ctx.atom();
        PythonParser.TrailerContext trailerContext = ctx.trailer().get(0);

        if (trailerContext.name() != null) {
            objectOrModule = atom.getText();
            functionName = trailerContext.name().getText();
        } else {
            functionName = atom.getText();
        }

        for (PythonParser.ArgumentContext argumentContext : trailerContext.arguments().arglist().argument()) {
            String argumentName = null;
            String argumentValue = null;
            if (argumentContext.ASSIGN() != null) {
                argumentName = argumentContext.test(0).getText();
                argumentValue = argumentContext.test(1).getText();
            } else {
                argumentValue = argumentContext.test(0).getText();
            }
            Position argumentStart = new Position(argumentContext.start.getLine(), argumentContext.start.getCharPositionInLine());
            Position argumentEnd = new Position(argumentContext.stop.getLine(), argumentContext.stop.getCharPositionInLine());
            functionArguments.add(new FunctionCallArgument(argumentName, argumentValue, argumentStart, argumentEnd, argumentContext));
        }
        Position start = new Position(ctx.start.getLine(), ctx.start.getCharPositionInLine());
        Position end = new Position(ctx.stop.getLine(), ctx.stop.getCharPositionInLine());
        return Optional.of(new FunctionCall(objectOrModule, functionName, functionArguments, start, end, ctx));
    }
}
