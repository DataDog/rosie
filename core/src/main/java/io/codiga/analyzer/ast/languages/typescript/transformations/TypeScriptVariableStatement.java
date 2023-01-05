package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.analyzer.ast.languages.typescript.CodigaVisitor;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.VariableDeclaration;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptVariableDeclarationToAssignment.transformVariableDeclarationToVariableDeclaration;

public class TypeScriptVariableStatement {
    private final Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);


    public static List<VariableDeclaration> transformVariableStatementToVariableDeclaration(TypeScriptParser.VariableStatementContext ctx, ParserRuleContext root) {
        List<VariableDeclaration> result = new ArrayList<>();
        Optional<AstString> modifierOptional = Optional.empty();


        if (ctx.varModifier() != null) {
            modifierOptional = Optional.of(new AstString(ctx.varModifier().getText(), ctx.varModifier(), root));
        }

        if (ctx.variableDeclarationList() != null) {
            TypeScriptParser.VariableDeclarationListContext variableDeclarationListContext = ctx.variableDeclarationList();
            if (variableDeclarationListContext.variableDeclaration() != null) {
                for (TypeScriptParser.VariableDeclarationContext variableDeclarationContext : variableDeclarationListContext.variableDeclaration()) {
                    Optional<VariableDeclaration> variableDeclarationOptional = transformVariableDeclarationToVariableDeclaration(modifierOptional.orElse(null), variableDeclarationContext, root);
                    if (variableDeclarationOptional.isPresent()) {
                        result.add(variableDeclarationOptional.get());
                    }
                }
            }
        }
        return result;
    }
}
