package io.codiga.analyzer.ast.languages.python.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.python.PythonClassDefinition;
import io.codiga.model.ast.python.PythonDecorator;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.PythonAstUtils.getDecoratorsForClassOrFunctionDefinition;

public class ClassOrFuncDefToClassDefinition {

    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefToClassDefinition.class);


    public static boolean isClassDefinition(PythonParser.Class_or_func_def_stmtContext ctx) {
        return ctx.classdef() != null;
    }

    public static List<AstString> getInheritedClasses(PythonParser.Class_or_func_def_stmtContext ctx, PythonParser.RootContext root) {
        List<AstString> astStrings = new ArrayList<>();

        if (ctx.classdef().arglist() != null && ctx.classdef().arglist().argument().size() > 0) {
            for (PythonParser.ArgumentContext argument : ctx.classdef().arglist().argument()) {
                if (argument.test().size() > 0) {
                    if (argument.test(0).logical_test().size() > 0) {
                        PythonParser.Logical_testContext logical_testContext = argument.test(0).logical_test(0);
                        if (logical_testContext.comparison() != null && logical_testContext.comparison().expr() != null && logical_testContext.comparison().expr().atom() != null) {
                            PythonParser.NameContext name = argument.test(0).logical_test(0).comparison().expr().atom().name();
                            if (name != null) {
                                astStrings.add(new AstString(name.getText(), name, root));
                            }
                        }
                    }
                }
            }
        }
        return astStrings.stream().toList();
    }

    public static Optional<PythonClassDefinition> transformClassOrFuncDefToClassDefinition(PythonParser.Class_or_func_def_stmtContext ctx, PythonParser.RootContext root) {
        if (!isClassDefinition(ctx)) {
            return Optional.empty();
        }

        AstString className = new AstString(ctx.classdef().name().getText(), ctx.classdef().name(), root);
        List<PythonDecorator> decorators = getDecoratorsForClassOrFunctionDefinition(ctx, root);

        return Optional.of(new PythonClassDefinition(decorators, className, getInheritedClasses(ctx, root), ctx, root));

    }
}
