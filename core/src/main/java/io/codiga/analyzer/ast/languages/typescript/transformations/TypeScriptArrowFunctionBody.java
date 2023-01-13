package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptFunctionBody.transformFunctionBody;


public class TypeScriptArrowFunctionBody {


    public static Optional<AstElement> transformArrowFunctionBody(TypeScriptParser.ArrowFunctionBodyContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        if (ctx.functionBody() != null) {
            return transformFunctionBody(ctx.functionBody(), root);
        }
        return Optional.empty();
    }
}
