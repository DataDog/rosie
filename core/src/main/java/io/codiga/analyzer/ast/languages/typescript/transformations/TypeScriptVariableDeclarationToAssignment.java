package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIdentifierOrKeywordTransformation.transformIdentifierNameToAstString;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;
import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;

public class TypeScriptVariableDeclarationToAssignment {


    public static Optional<Assignment> transformVariableDeclarationToAssignment(TypeScriptParser.VariableDeclarationContext ctx, ParserRuleContext root) {
        if (ctx.identifierOrKeyWord() == null || ctx.singleExpression() == null) {
            return Optional.empty();
        }

        if (ctx.singleExpression().size() != 1) {
            return Optional.empty();
        }

        Optional<AstElement> leftSide = convertToAstElement(transformIdentifierNameToAstString(ctx.identifierOrKeyWord(), root));
        Optional<AstElement> rightSide = transformSingleExpressionToAstElement(ctx.singleExpression().get(0), root);

        if (leftSide.isPresent() && rightSide.isPresent()) {
            return Optional.of(new Assignment(leftSide.get(), rightSide.get(), ctx, root));
        }

        return Optional.empty();

    }


}
