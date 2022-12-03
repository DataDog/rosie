package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class JavaScriptImport extends AstElement {

    public JavaScriptImportedName[] importedNames;
    public AstString pkg;

    public JavaScriptImport(List<? extends JavaScriptImportedName> importedNames,
                            AstString pkg,
                            ParserRuleContext ruleContext,
                            ParserRuleContext root) {

        super(AST_ELEMENT_TYPE_IMPORT_STATEMENT, ruleContext, root);
        this.importedNames = new JavaScriptImportedName[importedNames.size()];
        this.importedNames = importedNames.toArray(this.importedNames);
        this.pkg = pkg;
    }


}
