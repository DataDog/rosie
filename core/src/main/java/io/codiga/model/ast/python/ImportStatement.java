package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.python.gen.PythonParser;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class ImportStatement extends AstElement {
    public ImportStatementPackage[] packages;

    public ImportStatement(List<ImportStatementPackage> packageList,
                           PythonParser.Import_stmtContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_IMPORT_STATEMENT, parserRuleContext, root);
        this.packages = new ImportStatementPackage[packageList.size()];
        this.packages = packageList.toArray(packages);
    }

    public ImportStatement(List<ImportStatementPackage> packageList,
                           ParserContext parserContext) {
        super(AST_ELEMENT_TYPE_IMPORT_STATEMENT, parserContext);
        this.packages = new ImportStatementPackage[packageList.size()];
        this.packages = packageList.toArray(packages);
    }
}
