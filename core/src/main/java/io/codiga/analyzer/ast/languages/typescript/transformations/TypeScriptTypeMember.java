package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.analyzer.ast.languages.typescript.CodigaVisitor;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptPropertySignature.transformTypeScriptPropertySignature;
import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;

public class TypeScriptTypeMember {
    private final Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);


    public static Optional<AstElement> transformTypeMemberToAstElement(TypeScriptParser.TypeMemberContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.propertySignatur() != null) {
            return convertToAstElement(transformTypeScriptPropertySignature(ctx.propertySignatur(), root));
        }
        return Optional.empty();

    }

}
