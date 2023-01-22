package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.analyzer.ast.languages.python.transformations.ClassOrFuncDefTransformation;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class TypeScriptTypeNameToAstString {
    private static final Logger logger = LoggerFactory.getLogger(ClassOrFuncDefTransformation.class);

    public static Optional<AstString> typenameToAstString(TypeScriptParser.TypeNameContext ctx, ParserRuleContext root) {
        if (ctx.Identifier() != null) {
            return Optional.of(new AstString(ctx.Identifier().getText(), ctx.Identifier().getSymbol(), root));
        }
        return Optional.empty();
    }
}
