package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.antlr.v4.runtime.ParserRuleContext;

public class PythonElifStatement extends AstElement {
    public PythonComparison condition;
    public AstElement statements;

    public PythonElifStatement(PythonComparison pythonComparison,
                               AstElement statements,
                               PythonParser.Elif_clauseContext parserRuleContext,
                               ParserRuleContext root) {
        super(AST_ELEMENT_ELIF_STATEMENT, parserRuleContext, root);
        this.condition = pythonComparison;
        this.statements = statements;
    }
}
