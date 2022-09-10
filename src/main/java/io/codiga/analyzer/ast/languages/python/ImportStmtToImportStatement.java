package io.codiga.analyzer.ast.languages.python;

import io.codiga.model.ast.AstString;
import io.codiga.model.ast.python.ImportStatement;
import io.codiga.model.ast.python.ImportStatementPackage;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class ImportStmtToImportStatement {

    private static final Logger logger = LoggerFactory.getLogger(ImportStmtToImportStatement.class);


    public static Optional<ImportStatement> transformImportStmtToImportStatement(PythonParser.Import_stmtContext import_stmtContext,
                                                                                 PythonParser.RootContext root) {
        List<ImportStatementPackage> packages = import_stmtContext
            .dotted_as_names()
            .dotted_as_name()
            .stream().map(name -> {
                AstString pkgName = new AstString(name.dotted_name().getText(), name.dotted_name(), root);
                AstString asName = null;
                if (name.AS() != null) {
                    asName = new AstString(name.name().getText(), name.name(), root);
                }
                return new ImportStatementPackage(pkgName, asName, name, root);
            })
            .toList();
        return Optional.of(new ImportStatement(packages, import_stmtContext, root));
    }
}
