package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Switch;
import io.codiga.model.ast.common.SwitchCase;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptCaseClause.transformCaseClauseToSwitchCase;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptExpressionSequence.transformExpressionSequenceToAstElement;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptStatementList.transformStatementListToSequence;
import static io.codiga.utils.Conversions.flattenAstElement;

public class TypeScriptSwitchStatement {


    public static Optional<Switch> transformSwitchStatement(TypeScriptParser.SwitchStatementContext ctx, ParserRuleContext root) {

        List<SwitchCase> switchCasesList = new ArrayList<>();
        Optional<SwitchCase> defaultCase = Optional.empty();
        if (ctx == null) {
            return Optional.empty();
        }

        Optional<AstElement> expressionOptional = transformExpressionSequenceToAstElement(ctx.expressionSequence(), root);
        if (ctx.caseBlock() != null) {
            if (ctx.caseBlock().defaultClause() != null) {
                defaultCase = Optional.of(new SwitchCase(null, flattenAstElement(transformStatementListToSequence(ctx.caseBlock().defaultClause().statementList(), root)).orElse(null), ctx.caseBlock().defaultClause(), root));
            }

            if (ctx.caseBlock().caseClauses() != null) {
                for (TypeScriptParser.CaseClausesContext caseClausesContext : ctx.caseBlock().caseClauses()) {
                    if (caseClausesContext.caseClause() != null) {
                        for (TypeScriptParser.CaseClauseContext caseClauseContext : caseClausesContext.caseClause()) {
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
