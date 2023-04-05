package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.parser.antlr.python.gen.PythonParser;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class PythonElifStatement extends AstElement {
    public AstElement condition;
    public AstElement statements;

    public PythonElifStatement(AstElement pythonComparison,
                               AstElement statements,
                               PythonParser.Elif_clauseContext parserRuleContext,
                               ParserRuleContext root) {
        super(AstElementTypes.ELIF_STATEMENT, parserRuleContext, root);
        this.condition = pythonComparison;
        this.statements = statements;
    }

    public PythonElifStatement(AstElement pythonComparison,
                               AstElement statements,
                               ParserContext parserContext) {
        super(AstElementTypes.ELIF_STATEMENT, parserContext);
        this.condition = pythonComparison;
        this.statements = statements;
    }
}
