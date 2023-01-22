package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.analyzer.ast.languages.python.transformations.ClassOrFuncDefTransformation;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.FunctionCallArgument;
import io.codiga.model.ast.common.FunctionCallArguments;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;


public class TypeScriptIdentifierExpressionSequence {

    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefTransformation.class);


    public static Optional<FunctionCallArguments> transformExpressionSequenceToFunctionArguments(TypeScriptParser.ExpressionSequenceContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        List<FunctionCallArgument> arguments = new ArrayList<>();


        if (ctx.singleExpression() != null) {
            for (TypeScriptParser.SingleExpressionContext singleExpressionContext : ctx.singleExpression()) {
                Optional<AstElement> argumentValue = transformSingleExpressionToAstElement(singleExpressionContext, root);
                if (argumentValue.isPresent()) {
                    arguments.add(new FunctionCallArgument(null, argumentValue.get(), singleExpressionContext, root));
                }
            }
        }
        return Optional.of(new FunctionCallArguments(arguments, ctx, root));
    }

}
