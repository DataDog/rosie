package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.parser.antlr.javascript.transformations.JavaScriptFunctionBody.transformFunctionBody;
import static io.codiga.parser.antlr.javascript.transformations.JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;

public class JavaScriptArrowFunctionBody {


    public static Optional<AstElement> transformArrowFunctionBody(JavaScriptParser.ArrowFunctionBodyContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.singleExpression() != null) {
            return transformSingleExpressionToAstElement(ctx.singleExpression(), root);
        }

        if (ctx.functionBody() != null) {
            return transformFunctionBody(ctx.functionBody(), root);
        }

        return Optional.empty();
    }
}
