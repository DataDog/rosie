package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstExpression;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;

public class JavaScriptNotExpression {


    public static Optional<AstElement> transformNotExpressionToAstElement(JavaScriptParser.NotExpressionContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        Optional<AstElement> astElementOptional = transformSingleExpressionToAstElement(ctx.singleExpression(), root);
        AstString operatorString = new AstString(ctx.Not().getText(), ctx.Not().getSymbol(), root);
        return Optional.of(new AstExpression(null, operatorString, astElementOptional.orElse(null), ctx, root));
    }
}
