package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.Assignments;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptVariableDeclarationToAssignment.transformVariableDeclarationToAssignment;

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
}
