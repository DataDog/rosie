package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.VariableDeclaration;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptAssignableToAstElement.transformAssignableToAstElement;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;

public class JavaScriptVariableDeclarationToAssignment {


    public static Optional<Assignment> transformVariableDeclarationToAssignment(JavaScriptParser.VariableDeclarationContext ctx, ParserRuleContext root) {
        if (ctx.assignable() == null || ctx.singleExpression() == null) {
            return Optional.empty();
        }

        Optional<AstElement> leftSide = transformAssignableToAstElement(ctx.assignable(), root);
        Optional<AstElement> rightSide = transformSingleExpressionToAstElement(ctx.singleExpression(), root);

        if (leftSide.isPresent() && rightSide.isPresent()) {
            return Optional.of(new Assignment(leftSide.get(), rightSide.get(), ctx, root));
        }

        return Optional.empty();

    }


    public static Optional<VariableDeclaration> transformVariableDeclarationToVariableDeclaration(AstString modifier, JavaScriptParser.VariableDeclarationContext ctx, ParserRuleContext root) {
        if (ctx.assignable() == null || ctx.singleExpression() == null) {
            return Optional.empty();
        }

        Optional<AstElement> leftSide = transformAssignableToAstElement(ctx.assignable(), root);
        Optional<AstElement> rightSide = transformSingleExpressionToAstElement(ctx.singleExpression(), root);

        return Optional.of(new VariableDeclaration(modifier, leftSide.orElse(null), null, rightSide.orElse(null), ctx, root));
        
    }
}
