package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.javascript.JavaScriptImport;
import io.codiga.model.ast.javascript.JavaScriptImportedName;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIdentifierNameTransformation.transformIdentifierNameToAstString;

public class TypeScriptImportStatementToImport {


    public static Optional<JavaScriptImport> transformImportStatementToImport(TypeScriptParser.ImportStatementContext importStatementContext, ParserRuleContext root) {
        List<JavaScriptImportedName> importedNameList = new ArrayList<>();
        Optional<AstString> packageName = Optional.empty();

        if (importStatementContext.fromBlock() != null) {
            TypeScriptParser.FromBlockContext fromBlockContext = importStatementContext.fromBlock();
            if (fromBlockContext.multipleImportStatement() != null) {
                if (fromBlockContext.multipleImportStatement().identifierName() != null && fromBlockContext.multipleImportStatement().identifierName().size() > 0) {
                    for (TypeScriptParser.IdentifierNameContext identifierNameContext : fromBlockContext.multipleImportStatement().identifierName()) {
                        Optional<AstString> n = transformIdentifierNameToAstString(identifierNameContext, root);
                        if (n.isPresent()) {
                            importedNameList.add(new JavaScriptImportedName(n.get(), null, identifierNameContext, root));
                        }
                    }

                }
            }

            if (fromBlockContext.StringLiteral() != null) {
                packageName = Optional.of(new AstString(fromBlockContext.StringLiteral().getText(), fromBlockContext.StringLiteral().getSymbol(), root));
            }

            return Optional.of(new JavaScriptImport(importedNameList, packageName.orElse(null), fromBlockContext, root));
        }
        return Optional.empty();
    }
}
