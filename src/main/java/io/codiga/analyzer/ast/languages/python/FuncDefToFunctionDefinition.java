package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.*;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.AstUtils.getEndPosition;
import static io.codiga.analyzer.ast.AstUtils.getStartPosition;

public class FuncDefToFunctionDefinition {

    private static final Logger logger = LoggerFactory.getLogger(FuncDefToFunctionDefinition.class);


    public static Optional<FunctionDefinition> transformFuncDefToFunctionDefinition(PythonParser.FuncdefContext ctx, PythonParser.RootContext root) {
        AstString name = new AstStringBuilder().setValue(ctx.name().getText()).setStart(getStartPosition(ctx)).setEnd(getEndPosition(ctx)).setRuleContext(ctx).setRoot(root).createAstString();
        AstString returnType = null;
        FunctionDefinitionParameters parameters = null;
        List<FunctionDefinitionParameter> parameterList = new ArrayList<>();

        for (PythonParser.Def_parametersContext parameter : ctx.typedargslist().def_parameters()) {

            logger.info("parameter: " + parameter.getText());
            if (parameter.def_parameter() != null) {

                List<PythonParser.Def_parameterContext> parametersContext = parameter.def_parameter();
                logger.info("parametersContext size: " + parametersContext.size());
                for (PythonParser.Def_parameterContext parameterContext : parametersContext) {
                    AstString parameterName = null;
                    AstString parameterType = null;
                    AstString parameterDefaultValue = null;
                    if (parameterContext.named_parameter() != null) {
                        PythonParser.Named_parameterContext parameterNameAndType = parameterContext.named_parameter();
                        if (parameterNameAndType.name() != null) {
                            logger.info("found name: " + parameterContext.named_parameter().name().getText());
                            parameterName = new AstStringBuilder()
                                .setEnd(getEndPosition(parameterContext.named_parameter().name()))
                                .setStart(getStartPosition(parameterContext.named_parameter().name()))
                                .setValue(parameterContext.named_parameter().name().getText())
                                .setRuleContext(parameterContext.named_parameter().name())
                                .setRoot(root)
                                .createAstString();
                        }

                        if (parameterNameAndType.test() != null) {
                            logger.info("found test: " + parameterContext.named_parameter().test().getText());

                            PythonParser.TestContext test = parameterNameAndType.test();

                            parameterType = new AstStringBuilder()
                                .setValue(test.getText())
                                .setEnd(getEndPosition(test))
                                .setStart(getStartPosition(test))
                                .setRuleContext(test)
                                .setRoot(root)
                                .createAstString();
                        }
                    }

                    /**
                     * There is a default value
                     */
                    if (parameterContext.ASSIGN() != null) {
                        logger.info("got default value");
                        PythonParser.TestContext testDefaultValue = parameterContext.test();
                        if (testDefaultValue != null) {
                            parameterDefaultValue = new AstStringBuilder()
                                .setValue(testDefaultValue.getText())
                                .setEnd(getEndPosition(testDefaultValue))
                                .setStart(getStartPosition(testDefaultValue))
                                .setRuleContext(testDefaultValue)
                                .setRoot(root)
                                .createAstString();
                        }
                    }
                    if (parameterName != null) {
                        FunctionDefinitionParameter functionDefinitionParameter = new FunctionDefinitionParameter(
                            parameterName,
                            parameterType,
                            parameterDefaultValue,
                            getStartPosition(parameter),
                            getEndPosition(parameter),
                            parameter,
                            root);
                        parameterList.add(functionDefinitionParameter);
                    }
                }


            }

        }

        if (ctx.typedargslist() != null) {
            parameters = new FunctionDefinitionParameters(
                parameterList,
                getStartPosition(ctx.typedargslist()),
                getEndPosition(ctx.typedargslist()),
                ctx.typedargslist(),
                root);
        }

        FunctionDefinition functionDefinition = new FunctionDefinition(name,
            parameters,
            returnType,
            getStartPosition(ctx),
            getEndPosition(ctx),
            ctx,
            root);

        return Optional.of(functionDefinition);
    }
}
