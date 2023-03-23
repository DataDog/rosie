package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.SwitchCase;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.parser.antlr.javascript.transformations.JavaScriptStatementListToAstElement.transformStatementList;
import static io.codiga.utils.Conversions.flattenAstElement;

public class JavaScriptCaseClause {


    public static Optional<SwitchCase> transformCaseClauseToSwitchCase(JavaScriptParser.CaseClauseContext ctx, ParserRuleContext root) {

        if (ctx == null) {
            return Optional.empty();
        }

        Optional<AstElement> expression = JavaScriptExpressionSequence.transformExpressionSequenceToAstElement(ctx.expressionSequence(), root);
        Optional<AstElement> statements = flattenAstElement(transformStatementList(ctx.statementList(), root));


        if (expression.isPresent()) {
            return Optional.of(new SwitchCase(expression.get(), statements.orElse(null), ctx, root));
        }

        return Optional.empty();
    }

}
