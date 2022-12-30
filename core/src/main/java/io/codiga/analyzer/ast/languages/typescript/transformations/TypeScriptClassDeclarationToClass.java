package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.ClassDeclarationOneParent;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;


public class TypeScriptClassDeclarationToClass {


    public static Optional<ClassDeclarationOneParent> transformClassDeclaration(TypeScriptParser.ClassDeclarationContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        Optional<AstString> ext = Optional.empty();
        Optional<AstString> identifier = Optional.of(new AstString(ctx.Identifier().getText(), ctx.Identifier().getSymbol(), root));

        // TODO: julien - parse the class extension
        return Optional.of(new ClassDeclarationOneParent(identifier.orElse(null), null, ctx, root));
    }
}
