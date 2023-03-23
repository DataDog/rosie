package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.ClassDeclarationOneParent;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.utils.Conversions.convertToAstElement;


public class TypeScriptClassDeclarationToClass {


    public static Optional<ClassDeclarationOneParent> transformClassDeclaration(TypeScriptParser.ClassDeclarationContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        Optional<AstElement> ext = Optional.empty();
        Optional<AstString> identifier = Optional.of(new AstString(ctx.Identifier().getText(), ctx.Identifier().getSymbol(), root));

        if (ctx.classHeritage() != null) {
            if (ctx.classHeritage().classExtendsClause() != null && ctx.classHeritage().classExtendsClause().typeReference() != null) {
                ext = convertToAstElement(TypeScriptTypeReferenceToTypeScriptType.typeReferenceToType(ctx.classHeritage().classExtendsClause().typeReference(), root));
            }
        }

        return Optional.of(new ClassDeclarationOneParent(identifier.orElse(null), ext.orElse(null), ctx, root));
    }
}
