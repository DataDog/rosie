package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.antlr.python.gen.PythonParser;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class ExceptClause extends AstElement {

    public AstElement content;
    public AstString[] exceptions;
    public AstString as;
    private PythonParser.Except_clauseContext except_clauseContext;

    public ExceptClause(AstElement content, List<AstString> exceptions, AstString as, PythonParser.Except_clauseContext except_clauseContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_FUNCTION_EXCEPT_CLAUSE, except_clauseContext, root);
        this.content = content;
        this.exceptions = exceptions.stream().toArray(AstString[]::new);
        this.except_clauseContext = except_clauseContext;
        this.as = as;
    }

    public ExceptClause(AstElement content, List<AstString> exceptions, AstString as, ParserContext context) {
        super(AST_ELEMENT_TYPE_FUNCTION_EXCEPT_CLAUSE, context);
        this.content = content;
        this.exceptions = exceptions.stream().toArray(AstString[]::new);
        this.as = as;
    }


    public String getCodeBlock() {
        return this.except_clauseContext.suite().getText();
    }
}
