package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.ParserRuleContext;

public class PythonElseStatement extends AstElement {

    public AstElement statements;

    public PythonElseStatement(AstElement statements,
                               PythonParser.Else_clauseContext parserRuleContext,
                               ParserRuleContext root) {
        super(AST_ELEMENT_ELSE_STATEMENT, parserRuleContext, root);
        this.statements = statements;
    }
}
