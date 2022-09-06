package io.codiga.analyzer.ast.common;

import io.codiga.model.common.Position;
import io.codiga.model.error.Edit;
import io.codiga.model.error.Fix;
import io.codiga.model.error.Violation;
import org.graalvm.polyglot.HostAccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.codiga.model.error.CategoryUtils.categoryFromString;
import static io.codiga.model.error.SeverityUtils.severityFromString;
import static io.codiga.model.utils.ModelUtils.editTypeFromString;

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
                categoryFromString(category)));
    }

    @HostAccess.Export
    public Violation buildViolation(int startLine, int startCol, int endLine, int endCol, String message, String severity, String category) {
        return new Violation(
            new Position(startLine, startCol),
            new Position(endLine, endCol),
            message,
            severityFromString(severity),
            categoryFromString(category));
    }

    @HostAccess.Export
    public void addViolation(Violation violation) {
        this.errors.add(violation);
    }

    @HostAccess.Export
    public Fix buildFix(String description, Edit[] edits) {
        return new Fix(description, Arrays.asList(edits));
    }

    @HostAccess.Export
    public Edit buildEdit(int startLine, int startCol, int endLine, int endCol, String editType, String content) {
        return new Edit(new Position(startLine, startCol), new Position(endLine, endCol), editTypeFromString(editType), content);
    }
}
