package io.codiga.ast.common;

import io.codiga.model.common.Position;
import io.codiga.model.error.Violation;
import org.graalvm.polyglot.HostAccess;

import java.util.ArrayList;
import java.util.List;

import static io.codiga.model.error.CategoryUtils.categoryFromString;
import static io.codiga.model.error.SeverityUtils.severityFromString;

public class ErrorReporting {


    public List<Violation> errors;


    public ErrorReporting() {
        this.errors = new ArrayList<>();
    }


    public List<Violation> getErrors() {
        return this.errors;
    }

    @HostAccess.Export
    public void addError(int startLine, int startCol, int endLine, int endCol, String message, String severity, String category) {
        errors.add(
            new Violation(
                new Position(startLine, startCol),
                new Position(endLine, endCol),
                message,
                severityFromString(severity),
                categoryFromString(category),
                List.of()));
    }
}
