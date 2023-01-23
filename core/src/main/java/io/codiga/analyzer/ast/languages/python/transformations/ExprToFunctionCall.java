package io.codiga.analyzer.ast.languages.python.transformations;

import io.codiga.model.ast.common.AstString;
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
import static io.codiga.analyzer.ast.languages.python.transformations.ArgumentsTransformation.transformArgumentsToFunctionCallArguments;

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

    public static Optional<PythonFunctionCall> transformExprToFunctionCall(PythonParser.ExprContext ctx, PythonParser.RootContext root) {
        AstString objectOrModule = null;
        AstString functionName = null;
        List<FunctionCallArgument> functionArguments = new ArrayList<>();

        if (!isFunctionCall(ctx)) {
            return Optional.empty();
        }

        PythonParser.AtomContext atom = ctx.atom();
        // get the latest trailer to get the function name
        PythonParser.TrailerContext trailerContext = ctx.trailer().get(ctx.trailer().size() - 1);

        if (trailerContext.arguments() == null) {
            return Optional.empty();
        }

        if (trailerContext.arguments().OPEN_PAREN() == null || trailerContext.arguments().CLOSE_PAREN() == null) {
            return Optional.empty();
        }

        if (trailerContext.name() != null) {
            objectOrModule = getModuleOrObject(ctx, root);
            functionName = new AstString(trailerContext.name().getText(), trailerContext.name(), root);
        } else {
            functionName = new AstString(atom.getText(), atom, root);
        }

        Optional<FunctionCallArguments> arguments = transformArgumentsToFunctionCallArguments(trailerContext.arguments(), root);

        Position start = new Position(ctx.start.getLine(), ctx.start.getCharPositionInLine());
        Position end = new Position(ctx.stop.getLine(), ctx.stop.getCharPositionInLine());
        FunctionCallArguments functionCallArguments = null;


        return Optional.of(new PythonFunctionCall(objectOrModule, functionName, arguments.orElse(null), start, end, ctx, root));
    }


}
