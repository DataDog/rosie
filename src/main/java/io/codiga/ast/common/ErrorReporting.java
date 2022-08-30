package io.codiga.ast.common;

import io.codiga.model.error.Category;
import io.codiga.model.error.Position;
import io.codiga.model.error.Severity;
import io.codiga.model.error.Violation;
import org.graalvm.polyglot.HostAccess;

import java.util.ArrayList;
import java.util.List;

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
                Severity.CRITICAL,
                Category.BEST_PRACTICE,
                List.of()));
    }
}
