package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class PythonForStatement extends AstElement {

    public PythonExpression[] variables;
    public PythonString list;
    public PythonString statements;
    public PythonElseStatement elseStatement;

    public PythonForStatement(List<PythonExpression> variables,
                              PythonString list,
                              PythonString statements,
                              PythonElseStatement elseStatement,
                              PythonParser.For_stmtContext parserRuleContext,
                              ParserRuleContext root) {
        super(AST_ELEMENT_FOR_STATEMENT, parserRuleContext, root);
        this.variables = variables.stream().toArray(PythonExpression[]::new);
        this.list = list;
        this.statements = statements;
        this.elseStatement = elseStatement;

    }
}
