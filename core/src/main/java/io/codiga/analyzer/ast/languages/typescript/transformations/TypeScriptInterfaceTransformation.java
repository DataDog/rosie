package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.analyzer.ast.languages.typescript.CodigaVisitor;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.typescript.TypeScriptInterface;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptTypeMember.transformTypeMemberToAstElement;

public class TypeScriptInterfaceTransformation {
    private final Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);


    public static Optional<TypeScriptInterface> transformInterfaceDeclaration(TypeScriptParser.InterfaceDeclarationContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        List<AstElement> members = new ArrayList<>();
        Optional<AstElement> typename = Optional.empty();

        if (ctx.Identifier() != null) {
            typename = Optional.of(new AstString(ctx.Identifier().getText(), ctx.Identifier().getSymbol(), root));
        }

        if (ctx.objectType() != null && ctx.objectType().typeBody() != null) {
            TypeScriptParser.TypeBodyContext typeBodyContext = ctx.objectType().typeBody();
            if (typeBodyContext != null && typeBodyContext.typeMemberList() != null && typeBodyContext.typeMemberList().typeMember() != null) {
                for (TypeScriptParser.TypeMemberContext typeMemberContext : typeBodyContext.typeMemberList().typeMember()) {
                    Optional<AstElement> elementOptional = transformTypeMemberToAstElement(typeMemberContext, root);
                    if (elementOptional.isPresent()) {
                        members.add(elementOptional.get());
                    }
                }
            }
        }

        if (typename.isPresent()) {
            return Optional.of(new TypeScriptInterface(typename.get(), members, ctx, root));
        }
        return Optional.empty();

    }

}
