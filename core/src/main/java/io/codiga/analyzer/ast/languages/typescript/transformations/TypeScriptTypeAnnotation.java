package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.analyzer.ast.languages.python.transformations.ClassOrFuncDefToClassDefinition;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptType.typeToAstElement;

public class TypeScriptTypeAnnotation {
    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefToClassDefinition.class);

    public static Optional<AstElement> typeAnnotationToAstElement(TypeScriptParser.TypeAnnotationContext ctx, ParserRuleContext root) {
        if (ctx != null && ctx.type_() != null) {
            return typeToAstElement(ctx.type_(), root);
        }
        return Optional.empty();
    }
}
