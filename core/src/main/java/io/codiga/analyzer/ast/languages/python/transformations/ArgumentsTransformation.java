package io.codiga.analyzer.ast.languages.python.transformations;

import io.codiga.model.ast.common.*;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.transformations.PythonTestTransformation.transformTestToAstElement;

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


    public static Optional<Sequence> transformArgumentsToSequence(PythonParser.ArgumentsContext ctx, PythonParser.RootContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        List<AstElement> elementList = new ArrayList<>();

        if (ctx.subscriptlist() != null) {
            for (PythonParser.SubscriptContext subscriptlistContext : ctx.subscriptlist().subscript()) {
                if (subscriptlistContext.test() != null) {
                    for (PythonParser.TestContext testContext : subscriptlistContext.test()) {
                        var testOptional = transformTestToAstElement(testContext, root);
                        if (testOptional.isPresent()) {
                            elementList.add(testOptional.get());
                        }
                    }
                }
            }

            if (elementList.size() > 0) {
                return Optional.of(new Sequence(elementList, ctx, root));
            }
        }
        return Optional.empty();
    }
}
