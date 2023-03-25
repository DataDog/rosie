package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.python.gen.PythonParser;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class PythonIfStatement extends AstElement {
    public AstElement condition;
    public AstElement statements;
    public PythonElifStatement[] elifStatements;
    public PythonElseStatement elseStatements;

    public PythonIfStatement(AstElement condition,
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

    public PythonIfStatement(AstElement condition,
                             AstElement statements,
                             List<PythonElifStatement> elifStatements,
                             PythonElseStatement elseStatements,
                             ParserContext context) {
        super(AST_ELEMENT_IF_STATEMENT, context);
        this.condition = condition;
        this.statements = statements;
        this.elseStatements = elseStatements;
        this.elifStatements = elifStatements.stream().toArray(PythonElifStatement[]::new);
    }
}
