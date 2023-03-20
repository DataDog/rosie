package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.VariableDeclaration;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

public class JavaScriptVariableDeclarationToAssignment {


    public static Optional<Assignment> transformVariableDeclarationToAssignment(JavaScriptParser.VariableDeclarationContext ctx, ParserRuleContext root) {
        if (ctx.assignable() == null || ctx.singleExpression() == null) {
            return Optional.empty();
        }

        Optional<AstElement> leftSide = JavaScriptAssignableToAstElement.transformAssignableToAstElement(ctx.assignable(), root);
        Optional<AstElement> rightSide = JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement(ctx.singleExpression(), root);

        if (leftSide.isPresent() && rightSide.isPresent()) {
            return Optional.of(new Assignment(leftSide.get(), rightSide.get(), ctx, root));
        }

        return Optional.empty();

    }


    public static Optional<VariableDeclaration> transformVariableDeclarationToVariableDeclaration(AstString modifier, JavaScriptParser.VariableDeclarationContext ctx, ParserRuleContext root) {
        if (ctx.assignable() == null || ctx.singleExpression() == null) {
            return Optional.empty();
        }

        Optional<AstElement> leftSide = JavaScriptAssignableToAstElement.transformAssignableToAstElement(ctx.assignable(), root);
        Optional<AstElement> rightSide = JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement(ctx.singleExpression(), root);

        return Optional.of(new VariableDeclaration(modifier, leftSide.orElse(null), null, rightSide.orElse(null), ctx, root));

    }
}
