package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptSourceElements.transformSourceElements;


public class TypeScriptFunctionBody {


    public static Optional<AstElement> transformFunctionBody(TypeScriptParser.FunctionBodyContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        if (ctx.sourceElements() != null) {
            return transformSourceElements(ctx.sourceElements(), root);
        }
        return Optional.empty();
    }
}
