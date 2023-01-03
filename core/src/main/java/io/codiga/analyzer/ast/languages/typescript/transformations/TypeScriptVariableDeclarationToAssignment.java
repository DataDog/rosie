package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.analyzer.ast.languages.typescript.CodigaVisitor;
import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptArrayLiteralToArray.transformArrayLiteralToArray;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIdentifierOrKeywordTransformation.transformIdentifierNameToAstString;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptObjectLiteralToObject.transformTypeScriptObjectLiteralToObject;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;
import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;

public class TypeScriptVariableDeclarationToAssignment {
    private final Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);


    public static Optional<Assignment> transformVariableDeclarationToAssignment(TypeScriptParser.VariableDeclarationContext ctx, ParserRuleContext root) {


        if (ctx.singleExpression().size() != 1) {
            return Optional.empty();
        }

        Optional<AstElement> leftSide = Optional.empty();


        if (ctx.identifierOrKeyWord() != null) {
            leftSide = convertToAstElement(transformIdentifierNameToAstString(ctx.identifierOrKeyWord(), root));
        }

        if (ctx.arrayLiteral() != null) {
            leftSide = convertToAstElement(transformArrayLiteralToArray(ctx.arrayLiteral(), root));
        }

        if (ctx.objectLiteral() != null) {
            leftSide = convertToAstElement(transformTypeScriptObjectLiteralToObject(ctx.objectLiteral(), root));
        }

        Optional<AstElement> rightSide = transformSingleExpressionToAstElement(ctx.singleExpression().get(0), root);

        if (leftSide.isPresent() && rightSide.isPresent()) {
            return Optional.of(new Assignment(leftSide.get(), rightSide.get(), ctx, root));
        }

        return Optional.empty();

    }


}
