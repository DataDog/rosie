package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Sequence;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptStatement.transformStatementToAstElement;


public class TypeScriptSourceElements {


    public static Optional<AstElement> transformSourceElements(TypeScriptParser.SourceElementsContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        List<AstElement> astElementList = new ArrayList<>();

        for (var el : ctx.sourceElement()) {
            if (el.statement() != null) {
                var elementTransformedOptional = transformStatementToAstElement(el.statement(), root);
                if (elementTransformedOptional.isPresent()) {
                    astElementList.add(elementTransformedOptional.get());
                }
            }
        }

        return Optional.of(new Sequence(astElementList, ctx, root).flatten());
    }
}
