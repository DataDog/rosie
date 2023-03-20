package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.typescript.TypeScriptInterfaceProperty;
import io.codiga.parser.antlr.typescript.CodigaVisitor;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptPropertyNameToAstElement.transformPropertyNameToAstElement;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptType.typeToAstElement;

public class TypeScriptPropertySignature {
    private final Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);


    public static Optional<TypeScriptInterfaceProperty> transformTypeScriptPropertySignature(TypeScriptParser.PropertySignaturContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        Optional<AstElement> propertyName = Optional.empty();
        Optional<AstElement> typeValue = Optional.empty();


        propertyName = transformPropertyNameToAstElement(ctx.propertyName(), root);

        if (ctx.typeAnnotation() != null && ctx.typeAnnotation().type_() != null) {
            typeValue = typeToAstElement(ctx.typeAnnotation().type_(), root);
        }

        if (propertyName.isPresent()) {
            return Optional.of(new TypeScriptInterfaceProperty(ctx.ReadOnly() != null, propertyName.orElse(null), typeValue.orElse(null), ctx, root));
        }

        return Optional.empty();

    }

}
