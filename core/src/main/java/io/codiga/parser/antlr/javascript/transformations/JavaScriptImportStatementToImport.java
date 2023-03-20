package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.javascript.JavaScriptImport;
import io.codiga.model.ast.javascript.JavaScriptImportedName;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JavaScriptImportStatementToImport {


    public static Optional<JavaScriptImport> transformImportStatementToImport(JavaScriptParser.ImportStatementContext importStatementContext, ParserRuleContext root) {
        if (importStatementContext.importFromBlock() != null) {
            JavaScriptParser.ImportFromBlockContext importFromBlockContext = importStatementContext.importFromBlock();

            if (importFromBlockContext.StringLiteral() != null) {
                AstString name = new AstString(importFromBlockContext.StringLiteral().getText(), importStatementContext, root);

                return Optional.of(new JavaScriptImport(List.of(), name, importStatementContext, root));
            }
            if (importFromBlockContext.importFrom() != null && importFromBlockContext.importFrom().StringLiteral() != null) {
                AstString pkg = new AstString(importFromBlockContext.importFrom().StringLiteral().getText(), importFromBlockContext.importFrom(), root);
                List<JavaScriptImportedName> importedNames = new ArrayList<>();

                if (importFromBlockContext.importNamespace() != null) {
                    JavaScriptParser.ImportNamespaceContext importNamespace = importFromBlockContext.importNamespace();
                    AstString importedName = null;
                    AstString as = null;
                    if (importNamespace.Multiply() != null) {
                        importedName = new AstString("*", importNamespace, root);
                    } else {
                        if (importNamespace.identifierName() != null && importNamespace.identifierName().size() > 0) {
                            importedName = new AstString(importNamespace.identifierName().get(0).getText(), importNamespace.identifierName().get(0), root);
                        }
                    }

                    if (importNamespace.As() != null && importNamespace.identifierName().size() > 0) {
                        JavaScriptParser.IdentifierNameContext identifierName = importNamespace.identifierName().get(importNamespace.identifierName().size() - 1);
                        as = new AstString(identifierName.getText(), identifierName, root);
                    }
                    importedNames.add(new JavaScriptImportedName(importedName, as, importStatementContext, root));
                }

                if (importFromBlockContext.moduleItems() != null) {
                    for (JavaScriptParser.AliasNameContext aliasNameContext : importFromBlockContext.moduleItems().aliasName()) {
                        AstString importedName = new AstString(aliasNameContext.identifierName(0).getText(), aliasNameContext.identifierName(0), root);
                        AstString as = null;
                        if (aliasNameContext.As() != null && aliasNameContext.identifierName().size() > 1) {
                            as = new AstString(aliasNameContext.identifierName(1).getText(), aliasNameContext.identifierName(1), root);
                        }
                        importedNames.add(new JavaScriptImportedName(importedName, as, aliasNameContext, root));
                    }
                }

                return Optional.of(new JavaScriptImport(importedNames, pkg, importFromBlockContext, root));

            }
        }
        return Optional.empty();
    }
}
