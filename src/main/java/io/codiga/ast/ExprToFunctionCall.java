package io.codiga.ast;

import io.codiga.model.ast.FunctionCall;
import io.codiga.model.ast.FunctionCallArgument;
import io.codiga.parser.python.gen.PythonParser;
import org.graalvm.polyglot.Context;

import javax.swing.text.html.Option;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.ast.AstUtils.isFunctionCall;

public class ExprToFunctionCall {




    public static Optional<FunctionCall> transformExprToFunctionCall(PythonParser.ExprContext ctx) {
        Optional<String> objectOrModule = Optional.empty();
        String functionName = null;
        List<FunctionCallArgument> functionArguments = new ArrayList<>();
        int line = 0;

        if (!isFunctionCall(ctx)) {
            return Optional.empty();
        }

        PythonParser.AtomContext atom = ctx.atom();
        PythonParser.TrailerContext trailerContext = ctx.trailer().get(0);

        if (trailerContext.name() != null) {
            objectOrModule = Optional.ofNullable(atom.getText());
            functionName = trailerContext.name().getText();
            line = trailerContext.name().getStart().getLine();
        } else {
            functionName = atom.getText();
            line = atom.getStart().getLine();
        }

        for (PythonParser.ArgumentContext argumentContext: trailerContext.arguments().arglist().argument()){
            Optional<String> argumentName = Optional.empty();
            String argumentValue = null;
            if (argumentContext.ASSIGN() != null) {
                argumentName = Optional.of(argumentContext.test(0).getText());
                argumentValue = argumentContext.test(1).getText();
            } else {
                argumentValue = argumentContext.test(0).getText();
            }
            functionArguments.add(new FunctionCallArgument(argumentName, argumentValue));
        }

        return Optional.of(new FunctionCall(objectOrModule, functionName, functionArguments, line));

    }
}
