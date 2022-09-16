package io.codiga.model.ast.python;

import io.codiga.model.ast.AstElement;
import io.codiga.model.ast.AstString;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class ExceptClause extends AstElement {
    public AstString[] exceptions;
    public AstString as;
    private PythonParser.Except_clauseContext except_clauseContext;

    public ExceptClause(List<AstString> exceptions, AstString as, PythonParser.Except_clauseContext except_clauseContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_FUNCTION_EXCEPT_CLAUSE, except_clauseContext, root);
        this.exceptions = exceptions.stream().toArray(AstString[]::new);
        this.except_clauseContext = except_clauseContext;
        this.as = as;
    }

    public String getCodeBlock() {
        return this.except_clauseContext.suite().getText();
    }
}
