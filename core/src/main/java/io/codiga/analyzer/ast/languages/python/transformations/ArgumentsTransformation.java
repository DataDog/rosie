package io.codiga.analyzer.ast.languages.python.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionCallArgument;
import io.codiga.model.ast.common.FunctionCallArguments;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArgumentsTransformation {

    private static final Logger logger = LoggerFactory.getLogger(ArgumentsTransformation.class);

    public static Optional<FunctionCallArguments> transformArgumentsToFunctionCallArguments(PythonParser.ArgumentsContext ctx, PythonParser.RootContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        // function arguments
        if (ctx.OPEN_PAREN() != null && ctx.CLOSE_PAREN() != null) {

            List<FunctionCallArgument> functionArguments = new ArrayList<>();

            if (ctx.arglist() != null) {
                for (PythonParser.ArgumentContext argumentContext : ctx.arglist().argument()) {
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
                        argumentValue,
                        argumentContext,
                        root));
                }
            }

            return Optional.of(new FunctionCallArguments(functionArguments, ctx.arglist() != null ? ctx.arglist() : ctx, root));
        }

        return Optional.empty();
    }
}
