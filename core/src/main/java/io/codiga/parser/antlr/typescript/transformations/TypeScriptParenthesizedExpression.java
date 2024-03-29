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

public class TypeScriptParenthesizedExpression {
    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefTransformation.class);


    public static Optional<FunctionCallArguments> typescriptParenthesizedExpresssionToFunctionCallArguments(TypeScriptParser.ParenthesizedExpressionContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        List<FunctionCallArgument> functionCallArgumentList = new ArrayList<>();

        if (ctx.expressionSequence() != null) {
            for (var el : ctx.expressionSequence().singleExpression()) {
                Optional<AstElement> elementOptional = TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement(el, root);
                if (elementOptional.isPresent()) {
                    functionCallArgumentList.add(new FunctionCallArgument(null, elementOptional.get(), el, root));
                }
            }
        }


        return Optional.of(new FunctionCallArguments(functionCallArgumentList, ctx, root));
    }
}
