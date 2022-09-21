package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.python.gen.PythonParser;
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
}
