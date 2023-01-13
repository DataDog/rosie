package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Sequence;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptStatementToAstElement.transformStatement;

public class JavaScriptStatementListToAstElement {


    public static Optional<AstElement> transformStatementList(JavaScriptParser.StatementListContext ctx, ParserRuleContext root) {

        if (ctx == null || ctx.statement() == null) {
            return Optional.empty();
        }

        List<AstElement> astElementList = new ArrayList<>();

        for (JavaScriptParser.StatementContext statementContext : ctx.statement()) {
            Optional<AstElement> statementOptional = transformStatement(statementContext, root);
            if (statementOptional.isPresent()) {
                astElementList.add(statementOptional.get());
            }
        }

        return Optional.of(new Sequence(astElementList, ctx, root));
    }

}
