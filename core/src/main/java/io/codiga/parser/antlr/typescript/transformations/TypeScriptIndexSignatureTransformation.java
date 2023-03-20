package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.typescript.TypeScriptInterfaceIndexSignature;
import io.codiga.parser.antlr.typescript.CodigaVisitor;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptType.typeToAstElement;

public class TypeScriptIndexSignatureTransformation {
    private final Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);


    public static Optional<TypeScriptInterfaceIndexSignature> transformIndexSignature(TypeScriptParser.IndexSignatureContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        Optional<AstElement> propertyName = Optional.empty();
        Optional<AstElement> propertyType = Optional.empty();
        Optional<AstElement> typeValue = Optional.empty();


        if (ctx.Identifier() != null) {
            propertyName = Optional.of(new AstString(ctx.Identifier().getText(), ctx.Identifier().getSymbol(), root));
        }

        if (ctx.String() != null) {
            propertyType = Optional.of(new AstString(ctx.String().getText(), ctx.String().getSymbol(), root));
        }

        if (ctx.Number() != null) {
            propertyType = Optional.of(new AstString(ctx.Number().getText(), ctx.Number().getSymbol(), root));
        }

        if (ctx.typeAnnotation() != null && ctx.typeAnnotation().type_() != null) {
            typeValue = typeToAstElement(ctx.typeAnnotation().type_(), root);
        }

        if (propertyName.isPresent()) {
            return Optional.of(new TypeScriptInterfaceIndexSignature(propertyName.orElse(null), propertyType.orElse(null), typeValue.orElse(null), ctx, root));
        }

        return Optional.empty();

    }

}
