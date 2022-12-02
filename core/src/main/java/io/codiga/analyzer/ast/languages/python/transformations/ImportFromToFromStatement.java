package io.codiga.analyzer.ast.languages.python.transformations;

import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.python.FromElement;
import io.codiga.model.ast.python.FromStatement;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class ImportFromToFromStatement {

    private static final Logger logger = LoggerFactory.getLogger(ImportFromToFromStatement.class);


    public static Optional<FromStatement> transformFromStmtToFromStatement(PythonParser.From_stmtContext from_stmtContext,
                                                                           PythonParser.RootContext root) {
        if (from_stmtContext.dotted_name() == null) {
            return Optional.empty();
        }

        AstString packageName = new AstString(from_stmtContext.dotted_name().getText(), from_stmtContext.dotted_name(), root);
        List<FromElement> elements = List.of();

        if (from_stmtContext.import_as_names() != null && from_stmtContext.import_as_names().import_as_name() != null) {


            elements = from_stmtContext.import_as_names().import_as_name().stream().map(i -> {
                AstString as = null;
                AstString name = null;
                if (i.AS() == null) {
                    name = new AstString(i.name(0).getText(), i.name(0), root);
                } else {
                    name = new AstString(i.name(0).getText(), i.name(0), root);
                    as = new AstString(i.name(1).getText(), i.name(1), root);
                }
                return new FromElement(name, as, i, root);
            }).toList();
        }

        return Optional.of(new FromStatement(packageName, elements, from_stmtContext, root));
    }
}
