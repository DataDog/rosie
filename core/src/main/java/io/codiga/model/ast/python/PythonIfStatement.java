package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class PythonIfStatement extends AstElement {
    public PythonComparison condition;
    public AstElement statements;
    public PythonElifStatement[] elifStatements;
    public PythonElseStatement elseStatements;

    public PythonIfStatement(PythonComparison condition,
                             AstElement statements,
                             List<PythonElifStatement> elifStatements,
                             PythonElseStatement elseStatements,
                             PythonParser.If_stmtContext parserRuleContext,
                             ParserRuleContext root) {
        super(AST_ELEMENT_IF_STATEMENT, parserRuleContext, root);
        this.condition = condition;
        this.statements = statements;
        this.elseStatements = elseStatements;
        this.elifStatements = elifStatements.stream().toArray(PythonElifStatement[]::new);
    }
}
