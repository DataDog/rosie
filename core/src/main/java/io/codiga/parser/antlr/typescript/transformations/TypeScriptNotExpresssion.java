package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstExpression;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;


public class TypeScriptNotExpresssion {


    public static Optional<AstElement> transformNotExpressionToAstElement(TypeScriptParser.NotExpressionContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        Optional<AstElement> astElementOptional = transformSingleExpressionToAstElement(ctx.singleExpression(), root);
        AstString operatorString = new AstString(ctx.Not().getText(), ctx.Not().getSymbol(), root);
        return Optional.of(new AstExpression(null, operatorString, astElementOptional.orElse(null), ctx, root));
    }
}
