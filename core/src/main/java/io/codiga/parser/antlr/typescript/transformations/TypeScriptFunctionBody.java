package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;


public class TypeScriptFunctionBody {


    public static Optional<AstElement> transformFunctionBody(TypeScriptParser.FunctionBodyContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        if (ctx.sourceElements() != null) {
            return TypeScriptSourceElements.transformSourceElements(ctx.sourceElements(), root);
        }
        return Optional.empty();
    }
}
