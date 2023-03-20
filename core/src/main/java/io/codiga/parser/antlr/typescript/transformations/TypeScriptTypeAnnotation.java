package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.python.transformations.ClassOrFuncDefTransformation;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptType.typeToAstElement;

public class TypeScriptTypeAnnotation {
    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefTransformation.class);

    public static Optional<AstElement> typeAnnotationToAstElement(TypeScriptParser.TypeAnnotationContext ctx, ParserRuleContext root) {
        if (ctx != null && ctx.type_() != null) {
            return typeToAstElement(ctx.type_(), root);
        }
        return Optional.empty();
    }
}
