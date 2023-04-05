package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.antlr.python.gen.PythonParser;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class PythonForStatement extends AstElement {

    public AstElement left;
    public AstElement right;


    public AstElement[] variables;
    public AstString list;
    public AstElement statements;
    public PythonElseStatement elseStatement;

    public PythonForStatement(List<PythonExpression> variables,
                              AstString list,
                              AstString statements,
                              PythonElseStatement elseStatement,
                              PythonParser.For_stmtContext parserRuleContext,
                              ParserRuleContext root) {
        super(AstElementTypes.FOR_STATEMENT, parserRuleContext, root);
        this.variables = variables.stream().toArray(PythonExpression[]::new);
        this.list = list;
        this.statements = statements;
        this.elseStatement = elseStatement;

    }

    public PythonForStatement(AstElement left,
                              AstElement right,
                              AstElement statements,
                              ParserContext context) {
        super(AstElementTypes.FOR_STATEMENT, context);
        this.left = left;
        this.right = right;
        this.statements = statements;
        this.elseStatement = null;

    }
}
