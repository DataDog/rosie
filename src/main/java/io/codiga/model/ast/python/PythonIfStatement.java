package io.codiga.model.ast.python;

import io.codiga.model.ast.AstElement;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class PythonIfStatement extends AstElement {
    public PythonComparison condition;
    public PythonString statements;
    public PythonElifStatement[] elifStatements;
    public PythonElseStatement elseStatement;

    public PythonIfStatement(PythonComparison condition,
                             PythonString statements,
                             List<PythonElifStatement> elifStatements,
                             PythonElseStatement elseStatement,
                             PythonParser.If_stmtContext parserRuleContext,
                             ParserRuleContext root) {
        super(AST_ELEMENT_IF_STATEMENT, parserRuleContext, root);
        this.condition = condition;
        this.statements = statements;
        this.elseStatement = elseStatement;
        this.elifStatements = elifStatements.stream().toArray(PythonElifStatement[]::new);
    }
}
