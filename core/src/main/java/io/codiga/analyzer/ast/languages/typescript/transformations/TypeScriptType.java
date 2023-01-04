package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.analyzer.ast.languages.python.transformations.ClassOrFuncDefToClassDefinition;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptTypeNameToAstString.typenameToAstString;
import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;

public class TypeScriptType {
    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefToClassDefinition.class);

    public static Optional<AstElement> unionInterSectionPrimaryType(TypeScriptParser.UnionOrIntersectionOrPrimaryTypeContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    public static Optional<AstElement> typeReferenceToAstElement(TypeScriptParser.TypeReferenceContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.typeName() != null) {
            return convertToAstElement(typenameToAstString(ctx.typeName(), root));
        }

        return Optional.empty();
    }

    public static Optional<AstElement> primaryTypeToAstElement(TypeScriptParser.PrimaryTypeContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx instanceof TypeScriptParser.PredefinedPrimTypeContext predefinedPrimTypeContext) {
            if (predefinedPrimTypeContext.predefinedType() != null) {
                return Optional.of(new AstString(predefinedPrimTypeContext.predefinedType().getText(),
                    predefinedPrimTypeContext.predefinedType(),
                    root));
            }
        }

        if (ctx instanceof TypeScriptParser.ReferencePrimTypeContext referencePrimTypeContext) {
            if (referencePrimTypeContext.typeReference() != null) {
                return typeReferenceToAstElement(referencePrimTypeContext.typeReference(), root);
            }
        }
        return Optional.empty();
    }

    public static Optional<AstElement> typeToAstElement(TypeScriptParser.Type_Context ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.unionOrIntersectionOrPrimaryType() != null) {
            TypeScriptParser.UnionOrIntersectionOrPrimaryTypeContext unionOrIntersectionOrPrimaryTypeContext = ctx.unionOrIntersectionOrPrimaryType();
            if (unionOrIntersectionOrPrimaryTypeContext instanceof TypeScriptParser.PrimaryContext primaryContext) {
                if (primaryContext.primaryType() != null) {
                    return primaryTypeToAstElement(primaryContext.primaryType(), root);
                }
            }
        }
        return Optional.empty();
    }
}
