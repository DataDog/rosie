package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.AstStringBuilder;
import io.codiga.model.ast.common.FunctionDefinitionParameter;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import io.codiga.model.ast.python.PythonDecorator;
import io.codiga.model.ast.python.PythonFunctionDefinition;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FuncDefToFunctionDefinition {

    private static final Logger logger = LoggerFactory.getLogger(FuncDefToFunctionDefinition.class);


    public static Optional<PythonFunctionDefinition> transformFuncDefToFunctionDefinition(PythonParser.Class_or_func_def_stmtContext ctx, PythonParser.RootContext root) {
        if (ctx.funcdef() == null) {
            return Optional.empty();
        }

        PythonParser.FuncdefContext functionDefinition = ctx.funcdef();

        boolean isAsync = functionDefinition.ASYNC() != null;

        AstString name = new AstStringBuilder().setValue(functionDefinition.name().getText()).setRuleContext(ctx).setRoot(root).createAstString();
        AstString returnType = null;
        FunctionDefinitionParameters parameters = null;
        List<FunctionDefinitionParameter> parameterList = new ArrayList<>();

        if (functionDefinition.typedargslist() != null && functionDefinition.typedargslist().def_parameters() != null) {
            for (PythonParser.Def_parametersContext parameter : functionDefinition.typedargslist().def_parameters()) {

                if (parameter.def_parameter() != null) {

                    List<PythonParser.Def_parameterContext> parametersContext = parameter.def_parameter();
                    for (PythonParser.Def_parameterContext parameterContext : parametersContext) {
                        AstString parameterName = null;
                        AstString parameterType = null;
                        AstString parameterDefaultValue = null;
                        if (parameterContext.named_parameter() != null) {
                            PythonParser.Named_parameterContext parameterNameAndType = parameterContext.named_parameter();
                            if (parameterNameAndType.name() != null) {
                                parameterName = new AstStringBuilder()
                                    .setValue(parameterContext.named_parameter().name().getText())
                                    .setRuleContext(parameterContext.named_parameter().name())
                                    .setRoot(root)
                                    .createAstString();
                            }

                            if (parameterNameAndType.test() != null) {

                                PythonParser.TestContext test = parameterNameAndType.test();

                                parameterType = new AstStringBuilder()
                                    .setValue(test.getText())
                                    .setRuleContext(test)
                                    .setRoot(root)
                                    .createAstString();
                            }
                        }

                        /**
                         * There is a default value
                         */
                        if (parameterContext.ASSIGN() != null) {
                            PythonParser.TestContext testDefaultValue = parameterContext.test();

                            if (testDefaultValue != null) {
                                parameterDefaultValue = new AstStringBuilder()
                                    .setValue(testDefaultValue.getText())
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
                                parameterContext,
                                root);
                            parameterList.add(functionDefinitionParameter);
                        }
                    }
                }
            }
            parameters = new FunctionDefinitionParameters(
                parameterList,
                functionDefinition.typedargslist(),
                root);
        }


        List<PythonDecorator> decorators = ctx.decorator()
            .stream().map(decoratorContext -> PythonDecorator.fromArgumentContext(decoratorContext, root).orElse(null)).toList();

        return Optional.of(new PythonFunctionDefinition(
            isAsync,
            decorators,
            name,
            parameters,
            returnType,
            ctx,
            root));
    }
}
