package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstExpression;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.Optional;


public class JavaScriptExpression {


    public static Optional<AstExpression> transformExpression(JavaScriptParser.SingleExpressionContext left,
                                                              Token symbol,
                                                              JavaScriptParser.SingleExpressionContext right,
                                                              ParserRuleContext ctx,
                                                              ParserRuleContext root) {
        Optional<AstElement> leftOptional = JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement(left, root);
        Optional<AstElement> rightOptional = JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement(right, root);
        AstString s = new AstString(symbol.getText(), symbol, root);

        if (leftOptional.isPresent() && rightOptional.isPresent()) {
            return Optional.of(new AstExpression(leftOptional.get(), s, rightOptional.get(), ctx, root));
        }
        return Optional.empty();
    }

    public static Optional<AstExpression> transformExpressionRightOnly(Token symbol,
                                                                       JavaScriptParser.SingleExpressionContext right,
                                                                       ParserRuleContext ctx,
                                                                       ParserRuleContext root) {
        Optional<AstElement> rightOptional = JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement(right, root);
        AstString s = new AstString(symbol.getText(), symbol, root);

        if (rightOptional.isPresent()) {
            return Optional.of(new AstExpression(null, s, rightOptional.get(), ctx, root));
        }
        return Optional.empty();
    }
}
