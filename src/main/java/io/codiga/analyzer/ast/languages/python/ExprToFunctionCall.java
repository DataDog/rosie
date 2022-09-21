package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionCall;
import io.codiga.model.ast.common.FunctionCallArgument;
import io.codiga.model.ast.common.FunctionCallArguments;
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
        AstString objectOrModule = null;
        AstString functionName = null;
        List<FunctionCallArgument> functionArguments = new ArrayList<>();

        if (!isFunctionCall(ctx)) {
            return Optional.empty();
        }

        PythonParser.AtomContext atom = ctx.atom();
        PythonParser.TrailerContext trailerContext = ctx.trailer().get(0);

        if (trailerContext.name() != null) {
            objectOrModule = new AstString(atom.getText(), atom, root);
            functionName = new AstString(trailerContext.name().getText(), trailerContext.name(), root);
        } else {
            functionName = new AstString(atom.getText(), atom, root);
        }

        for (PythonParser.ArgumentContext argumentContext : trailerContext.arguments().arglist().argument()) {
            AstString argumentName = null;
            AstString argumentValue = null;

            if (argumentContext.ASSIGN() != null) {
                argumentName = new AstString(argumentContext.test(0).getText(), argumentContext.test(0), root);
                argumentValue = new AstString(argumentContext.test(1).getText(), argumentContext.test(1), root);
            } else {
                argumentValue = new AstString(argumentContext.test(0).getText(), argumentContext.test(0), root);
            }

            functionArguments.add(new FunctionCallArgument(
                argumentName,
                argumentValue, argumentContext, root));
        }
        Position start = new Position(ctx.start.getLine(), ctx.start.getCharPositionInLine());
        Position end = new Position(ctx.stop.getLine(), ctx.stop.getCharPositionInLine());
        FunctionCallArguments functionCallArguments = new FunctionCallArguments(functionArguments, trailerContext.arguments().arglist(), root);
        return Optional.of(new PythonFunctionCall(objectOrModule, functionName, functionCallArguments, start, end, ctx, root));
    }
}
