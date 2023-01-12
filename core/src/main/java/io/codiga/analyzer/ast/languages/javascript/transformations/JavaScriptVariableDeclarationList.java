package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.*;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptVariableDeclarationToAssignment.transformVariableDeclarationToAssignment;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptVariableDeclarationToAssignment.transformVariableDeclarationToVariableDeclaration;

public class JavaScriptVariableDeclarationList {


    public static Optional<Assignments> transformVariableDeclarationListToAssignmentList(JavaScriptParser.VariableDeclarationListContext ctx, ParserRuleContext root) {
        List<Assignment> result = new ArrayList<>();

        if (ctx.variableDeclaration() != null) {
            ctx.variableDeclaration().forEach(v -> {
                Optional<Assignment> r = transformVariableDeclarationToAssignment(v, root);
                if (r.isPresent()) {
                    result.add(r.get());
                }
            });
        }

        return Optional.of(new Assignments(result, ctx, root));
    }

    public static Optional<AstElement> transformVariableDeclarationToAstElement(JavaScriptParser.VariableDeclarationListContext ctx, ParserRuleContext root) {
        List<AstElement> result = new ArrayList<>();
        Optional<AstString> modifier = Optional.empty();

        if (ctx.varModifier() != null) {
            modifier = Optional.of(new AstString(ctx.varModifier().getText(), ctx.varModifier(), root));
        }


        if (ctx.variableDeclaration() != null) {
            for (var v : ctx.variableDeclaration()) {
                Optional<VariableDeclaration> r = transformVariableDeclarationToVariableDeclaration(modifier.orElse(null), v, root);
                if (r.isPresent()) {
                    result.add(r.get());
                }
            }
        }


        if (result.isEmpty()) {
            return null;
        }
        if (result.size() == 1) {
            return Optional.of(result.get(0));
        }
        return Optional.of(new Sequence(result, ctx, root));
    }
}
