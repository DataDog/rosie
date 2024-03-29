package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.javascript.JavaScriptImport;
import io.codiga.model.ast.javascript.JavaScriptImportedName;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TypeScriptImportStatementToImport {


    public static Optional<JavaScriptImport> transformImportStatementToImport(TypeScriptParser.ImportStatementContext importStatementContext, ParserRuleContext root) {
        List<JavaScriptImportedName> importedNameList = new ArrayList<>();
        Optional<AstString> packageName = Optional.empty();

        if (importStatementContext.fromBlock() != null) {
            TypeScriptParser.FromBlockContext fromBlockContext = importStatementContext.fromBlock();

            // match "import {foo1, foo2} from bar
            if (fromBlockContext.multipleImportStatement() != null) {
                if (fromBlockContext.multipleImportStatement().identifierName() != null && fromBlockContext.multipleImportStatement().identifierName().size() > 0) {
                    for (TypeScriptParser.IdentifierNameContext identifierNameContext : fromBlockContext.multipleImportStatement().identifierName()) {
                        Optional<AstString> n = TypeScriptIdentifierNameTransformation.transformIdentifierNameToAstString(identifierNameContext, root);
                        if (n.isPresent()) {
                            importedNameList.add(new JavaScriptImportedName(n.get(), null, identifierNameContext, root));
                        }
                    }

                }
            }

            // match "import foo from bar"
            if (fromBlockContext.Multiply() == null && fromBlockContext.multipleImportStatement() == null && fromBlockContext.identifierName() != null && fromBlockContext.identifierName().size() > 0) {
                TypeScriptParser.IdentifierNameContext identifierNameContext = fromBlockContext.identifierName().get(0);
                Optional<AstString> n = TypeScriptIdentifierNameTransformation.transformIdentifierNameToAstString(identifierNameContext, root);
                if (n.isPresent()) {
                    importedNameList.add(new JavaScriptImportedName(n.get(), null, identifierNameContext, root));
                }

            }

            // match "import * from bar"
            if (fromBlockContext.Multiply() != null) {
                Optional<AstString> asName = Optional.empty();
                if (fromBlockContext.identifierName() != null && fromBlockContext.identifierName().size() == 1) {
                    asName = TypeScriptIdentifierNameTransformation.transformIdentifierNameToAstString(fromBlockContext.identifierName().get(0), root);
                }

                importedNameList.add(
                    new JavaScriptImportedName(
                        new AstString("*", fromBlockContext.Multiply().getSymbol(), root),
                        asName.orElse(null),
                        fromBlockContext,
                        root));
            }

            if (fromBlockContext.StringLiteral() != null) {
                packageName = Optional.of(new AstString(fromBlockContext.StringLiteral().getText(), fromBlockContext.StringLiteral().getSymbol(), root));
            }

            return Optional.of(new JavaScriptImport(importedNameList, packageName.orElse(null), fromBlockContext, root));
        }
        return Optional.empty();
    }
}
