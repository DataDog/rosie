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


    private static AstString getModuleOrObject(PythonParser.ExprContext ctx, PythonParser.RootContext root) {
        /**
         * There is only one trailer and this is the function call.
         */
        if (ctx.trailer().size() == 1) {
            return new AstString(ctx.atom().getText(), ctx.atom(), root);
        } else {
            List<String> elements = new ArrayList<>();
            if (ctx.atom() != null) {
                elements.add(ctx.atom().getText());
            }
            for (int i = 0; i < ctx.trailer().size() - 1; i++) {
                PythonParser.TrailerContext trailerContext = ctx.trailer().get(i);
                if (trailerContext.name() != null) {
                    elements.add(trailerContext.name().getText());
                }
            }
            return new AstString(String.join(".", elements), ctx, root);
        }
    }

    public static Optional<FunctionCall> transformExprToFunctionCall(PythonParser.ExprContext ctx, PythonParser.RootContext root) {
        AstString objectOrModule = null;
        AstString functionName = null;
        List<FunctionCallArgument> functionArguments = new ArrayList<>();

        if (!isFunctionCall(ctx)) {
            return Optional.empty();
        }

        PythonParser.AtomContext atom = ctx.atom();
        // get the latest trailer to get the function name
        PythonParser.TrailerContext trailerContext = ctx.trailer().get(ctx.trailer().size() - 1);

        if (trailerContext.name() != null) {
            objectOrModule = getModuleOrObject(ctx, root);
            functionName = new AstString(trailerContext.name().getText(), trailerContext.name(), root);
        } else {
            functionName = new AstString(atom.getText(), atom, root);
        }

        if (trailerContext.arguments() != null && trailerContext.arguments().arglist() != null && trailerContext.arguments().arglist().argument() != null) {
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
        }

        Position start = new Position(ctx.start.getLine(), ctx.start.getCharPositionInLine());
        Position end = new Position(ctx.stop.getLine(), ctx.stop.getCharPositionInLine());
        FunctionCallArguments functionCallArguments = null;

        if (trailerContext.arguments() != null && trailerContext.arguments().arglist() != null) {
            functionCallArguments = new FunctionCallArguments(functionArguments, trailerContext.arguments().arglist(), root);
        }

        return Optional.of(new PythonFunctionCall(objectOrModule, functionName, functionCallArguments, start, end, ctx, root));
    }
}
