package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Switch;
import io.codiga.model.ast.common.SwitchCase;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptCaseClause.transformCaseClauseToSwitchCase;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptExpressionSequence.transformExpressionSequenceToAstElement;
import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptStatementListToAstElement.transformStatementList;
import static io.codiga.analyzer.ast.languages.utils.Conversions.flattenAstElement;

public class JavaScriptSwitchStatement {


    public static Optional<Switch> transformSwitchStatement(JavaScriptParser.SwitchStatementContext ctx, ParserRuleContext root) {

        List<SwitchCase> switchCasesList = new ArrayList<>();
        Optional<SwitchCase> defaultCase = Optional.empty();
        if (ctx == null) {
            return Optional.empty();
        }

        Optional<AstElement> expressionOptional = transformExpressionSequenceToAstElement(ctx.expressionSequence(), root);
        if (ctx.caseBlock() != null) {
            if (ctx.caseBlock().defaultClause() != null) {
                defaultCase = Optional.of(new SwitchCase(null, flattenAstElement(transformStatementList(ctx.caseBlock().defaultClause().statementList(), root)).orElse(null), ctx.caseBlock().defaultClause(), root));
            }

            if (ctx.caseBlock().caseClauses() != null) {
                for (JavaScriptParser.CaseClausesContext caseClausesContext : ctx.caseBlock().caseClauses()) {
                    if (caseClausesContext.caseClause() != null) {
                        for (JavaScriptParser.CaseClauseContext caseClauseContext : caseClausesContext.caseClause()) {
                            Optional<SwitchCase> element = transformCaseClauseToSwitchCase(caseClauseContext, root);
                            if (element.isPresent()) {
                                switchCasesList.add(element.get());
                            }
                        }
                    }

                }
            }
        }

        if (expressionOptional.isPresent()) {
            return Optional.of(new Switch(expressionOptional.get(), switchCasesList, defaultCase.orElse(null), ctx, root));
        }

        return Optional.empty();
    }
}
