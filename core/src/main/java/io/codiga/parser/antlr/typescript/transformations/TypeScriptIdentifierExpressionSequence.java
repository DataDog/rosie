package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.FunctionCallArgument;
import io.codiga.model.ast.common.FunctionCallArguments;
import io.codiga.parser.antlr.python.transformations.ClassOrFuncDefTransformation;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TypeScriptIdentifierExpressionSequence {

    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefTransformation.class);


    public static Optional<FunctionCallArguments> transformExpressionSequenceToFunctionArguments(TypeScriptParser.ExpressionSequenceContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        List<FunctionCallArgument> arguments = new ArrayList<>();


        if (ctx.singleExpression() != null) {
            for (TypeScriptParser.SingleExpressionContext singleExpressionContext : ctx.singleExpression()) {
                Optional<AstElement> argumentValue = TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement(singleExpressionContext, root);
                if (argumentValue.isPresent()) {
                    arguments.add(new FunctionCallArgument(null, argumentValue.get(), singleExpressionContext, root));
                }
            }
        }
        return Optional.of(new FunctionCallArguments(arguments, ctx, root));
    }

}
