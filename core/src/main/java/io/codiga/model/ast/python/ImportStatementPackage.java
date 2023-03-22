package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

public class ImportStatementPackage extends AstElement {
    public AstString name;
    public AstString as;


    public ImportStatementPackage(AstString name, AstString as, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_IMPORT_PACKAGE, parserRuleContext, root);
        this.name = name;
        this.as = as;
    }

    public ImportStatementPackage(AstString name, AstString as, ParserContext parserContext) {
        super(AST_ELEMENT_TYPE_IMPORT_PACKAGE, parserContext);
        this.name = name;
        this.as = as;
    }
}
