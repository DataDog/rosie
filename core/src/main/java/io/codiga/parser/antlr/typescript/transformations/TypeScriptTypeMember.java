package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.typescript.CodigaVisitor;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptIndexSignatureTransformation.transformIndexSignature;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptPropertySignature.transformTypeScriptPropertySignature;

public class TypeScriptTypeMember {
    private final Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);


    public static Optional<AstElement> transformTypeMemberToAstElement(TypeScriptParser.TypeMemberContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.propertySignatur() != null) {
            return convertToAstElement(transformTypeScriptPropertySignature(ctx.propertySignatur(), root));
        }

        if (ctx.indexSignature() != null) {
            return convertToAstElement(transformIndexSignature(ctx.indexSignature(), root));
        }
        return Optional.empty();

    }

}
