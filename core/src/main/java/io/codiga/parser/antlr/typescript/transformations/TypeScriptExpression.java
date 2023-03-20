package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstExpression;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.Optional;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;

public class TypeScriptExpression {


    public static Optional<AstExpression> transformExpression(TypeScriptParser.SingleExpressionContext left,
                                                              Token symbol,
                                                              TypeScriptParser.SingleExpressionContext right,
                                                              ParserRuleContext ctx,
                                                              ParserRuleContext root) {
        Optional<AstElement> leftOptional = transformSingleExpressionToAstElement(left, root);
        Optional<AstElement> rightOptional = transformSingleExpressionToAstElement(right, root);
        AstString s = new AstString(symbol.getText(), symbol, root);

        if (leftOptional.isPresent() && rightOptional.isPresent()) {
            return Optional.of(new AstExpression(leftOptional.get(), s, rightOptional.get(), ctx, root));
        }
        return Optional.empty();
    }

    public static Optional<AstExpression> transformExpressionRightOnly(Token symbol,
                                                                       TypeScriptParser.SingleExpressionContext right,
                                                                       ParserRuleContext ctx,
                                                                       ParserRuleContext root) {
        Optional<AstElement> rightOptional = transformSingleExpressionToAstElement(right, root);
        AstString s = new AstString(symbol.getText(), symbol, root);

        if (rightOptional.isPresent()) {
            return Optional.of(new AstExpression(null, s, rightOptional.get(), ctx, root));
        }
        return Optional.empty();
    }
}
