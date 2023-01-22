package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.analyzer.ast.languages.python.transformations.ClassOrFuncDefTransformation;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.typescript.TypeScriptType;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptTypeNameToAstString.typenameToAstString;
import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;

public class TypeScriptTypeReferenceToTypeScriptType {
    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefTransformation.class);

    public static Optional<TypeScriptType> typeReferenceToType(TypeScriptParser.TypeReferenceContext ctx, ParserRuleContext root) {
        Optional<AstElement> typename = Optional.empty();


        if (ctx.typeName() != null) {
            typename = convertToAstElement(typenameToAstString(ctx.typeName(), root));
        }

        if (typename.isPresent()) {
            return Optional.of(new TypeScriptType(typename.get(), ctx, root));
        }
        return Optional.empty();
    }
}
