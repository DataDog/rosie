package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.SwitchCase;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptExpressionSequence.transformExpressionSequenceToAstElement;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptStatementList.transformStatementListToSequence;
import static io.codiga.analyzer.ast.languages.utils.Conversions.flattenAstElement;

public class TypeScriptCaseClause {


    public static Optional<SwitchCase> transformCaseClauseToSwitchCase(TypeScriptParser.CaseClauseContext ctx, ParserRuleContext root) {

        if (ctx == null) {
            return Optional.empty();
        }

        Optional<AstElement> expression = transformExpressionSequenceToAstElement(ctx.expressionSequence(), root);
        Optional<AstElement> statements = flattenAstElement(transformStatementListToSequence(ctx.statementList(), root));


        if (expression.isPresent()) {
            return Optional.of(new SwitchCase(expression.get(), statements.orElse(null), ctx, root));
        }

        return Optional.empty();
    }

}
