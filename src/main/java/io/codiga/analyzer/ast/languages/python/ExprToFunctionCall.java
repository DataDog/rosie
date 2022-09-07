package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.FunctionCall;
import io.codiga.model.ast.FunctionCallArgument;
import io.codiga.model.ast.python.PythonFunctionCall;
import io.codiga.model.common.Position;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.PythonAstUtils.isFunctionCall;

public class ExprToFunctionCall {

    private static final Logger logger = LoggerFactory.getLogger(ExprToFunctionCall.class);


    public static Optional<FunctionCall> transformExprToFunctionCall(PythonParser.ExprContext ctx, PythonParser.RootContext root) {
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
            Position argumentStart;
            Position argumentEnd;

            if (argumentContext.ASSIGN() != null) {
                argumentName = argumentContext.test(0).getText();
                argumentValue = argumentContext.test(1).getText();
                argumentStart = new Position(argumentContext.start.getLine(), argumentContext.start.getCharPositionInLine());
                argumentEnd = new Position(argumentContext.test(1).stop.getLine(), argumentContext.test(1).stop.getCharPositionInLine() + argumentValue.length());
            } else {
                argumentStart = new Position(argumentContext.start.getLine(), argumentContext.start.getCharPositionInLine());
                argumentEnd = new Position(argumentContext.stop.getLine(), argumentContext.stop.getCharPositionInLine());

                argumentValue = argumentContext.test(0).getText();
            }

            logger.info("argument start: " + argumentStart);
            logger.info("argument end: " + argumentEnd);
            functionArguments.add(new FunctionCallArgument(argumentName, argumentValue, argumentStart, argumentEnd, argumentContext, root));
        }
        Position start = new Position(ctx.start.getLine(), ctx.start.getCharPositionInLine());
        Position end = new Position(ctx.stop.getLine(), ctx.stop.getCharPositionInLine());
        return Optional.of(new PythonFunctionCall(objectOrModule, functionName, functionArguments, start, end, ctx, root));
    }
}
