package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.python.gen.PythonParser;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class PythonElseStatement extends AstElement {

    public AstElement statements;

    public PythonElseStatement(AstElement statements,
                               PythonParser.Else_clauseContext parserRuleContext,
                               ParserRuleContext root) {
        super(AST_ELEMENT_ELSE_STATEMENT, parserRuleContext, root);
        this.statements = statements;
    }

    public PythonElseStatement(AstElement statements,
                               ParserContext context) {
        super(AST_ELEMENT_ELSE_STATEMENT, context);
        this.statements = statements;
    }
}
